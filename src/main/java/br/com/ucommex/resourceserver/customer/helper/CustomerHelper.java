package br.com.ucommex.resourceserver.customer.helper;

import br.com.ucommex.resourceserver.customer.dto.*;
import br.com.ucommex.resourceserver.customer.pojo.ClienteDB;
import br.com.ucommex.resourceserver.customer.pojo.CompraDB;
import br.com.ucommex.resourceserver.customer.pojo.ProdutoDB;
import com.mongodb.client.MongoCollection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static br.com.ucommex.resourceserver.db.MongoConnect.getDatabase;
import static com.mongodb.client.model.Filters.eq;

public class CustomerHelper {

    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "To show an example of a custom message")
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
        }
    }

    public static ClienteDB getClienteByCpf(String cpf) {
        MongoCollection<ClienteDB> clienteCollection = getDatabase().getCollection("cliente", ClienteDB.class);
        return clienteCollection.find(eq("id_cliente", cpf)).first();
    }

    public static CompraDB getById(CompraDB compraDB) {
        MongoCollection<CompraDB> clienteCollection = getDatabase().getCollection("compra", CompraDB.class);
        return clienteCollection.find(eq("_id", compraDB.getId())).first();
    }

    public static ProdutoDB getById(ProdutoDB produtoDB) {
        MongoCollection<ProdutoDB> clienteCollection = getDatabase().getCollection("produto", ProdutoDB.class);
        return clienteCollection.find(eq("_id", produtoDB.getId())).first();
    }

    private static UnaryOperator<CompraDB> getComprasComProdutos =
            compra -> {
                CompraDB compraDB = getById(compra);
                compraDB.setProdutos(compraDB.getProdutos()
                        .parallelStream()
                        .map(produto -> getById(produto))
                        .map(produto -> {
                            produto.setId(null);
                            return produto;
                        })
                        .collect(Collectors.toList()));
                compraDB.setId(null);
                return compraDB;
            };

    public static ClienteDB getRawCustomerById(String idCliente) {
        ClienteDB clienteDB = null;
        try {
            clienteDB = getClienteByCpf(idCliente);
            clienteDB.setCompras(
                    clienteDB.getCompras()
                            .parallelStream()
                            .map(getComprasComProdutos)
                            .collect(Collectors.toList())
            );
            clienteDB.setId(null);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            throw new NotFoundException("Usuário não encontrado");
        }
        return clienteDB;
    }

    public static CustomerStatsDTO getCustomerStats(ClienteDB clienteDB) {
        CustomerStatsDTO customerStatsDTO = new CustomerStatsDTO();
        customerStatsDTO.setNome(clienteDB.getNome());
        customerStatsDTO.setDataCadastro(clienteDB.getDataCadastro());
        customerStatsDTO.setDataNascimento(clienteDB.getDataNascimento());
        customerStatsDTO.setQuantidadeCompras(clienteDB.getCompras().size());
        customerStatsDTO.setLojaPreferida(getLojaPreferida(clienteDB));
        customerStatsDTO.setVendedorPreferido(getVendedorPreferido(clienteDB));
        customerStatsDTO.setTicketMedio(getTicketMedio(clienteDB));
        customerStatsDTO.setUltimasCompras(getUltimasCompras(clienteDB));
        customerStatsDTO.setPreferencias(getPreferencias(clienteDB));
        //TODO criar um PreferenciaDTO para preencher com as porcentagens da tela de preferencias
        //TODO refatorar esse método
        return customerStatsDTO;
    }

    private static LojaDTO getLojaPreferida(ClienteDB clienteDB) {
        Map<String, Long> quantidadeLoja = clienteDB.getCompras().stream()
                .collect(Collectors.groupingBy(CompraDB::getLoja, Collectors.counting()));
        Map.Entry<String, Long> lojaPreferida = quantidadeLoja.entrySet()
                .stream().max(Comparator.comparing(Map.Entry::getValue)).get();
        LojaDTO loja = new LojaDTO();
        loja.setNomeLoja(lojaPreferida.getKey());
        loja.setQuantidadeCompras(lojaPreferida.getValue());
        return loja;
    }

    private static VendedorDTO getVendedorPreferido(ClienteDB clienteDB) {
        Map<String, Long> quantidadeVendedor = clienteDB.getCompras().stream()
                .collect(Collectors.groupingBy(CompraDB::getVendedor, Collectors.counting()));
        Map.Entry<String, Long> vendedorPreferido = quantidadeVendedor.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get();
        VendedorDTO vendedor = new VendedorDTO();
        vendedor.setNomeVendedor(vendedorPreferido.getKey());
        vendedor.setQuantidadeCompras(vendedorPreferido.getValue());
        return vendedor;
    }

    private static BigDecimal getTicketMedio(ClienteDB clienteDB) {
        BigDecimal ticketMedio = clienteDB.getCompras().stream()
                .map(CompraDB::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(clienteDB.getCompras().size()),
                        RoundingMode.CEILING);
        return ticketMedio;
    }

    private static List<UltimaCompraDTO> getUltimasCompras(ClienteDB clienteDB) {
        List<UltimaCompraDTO> ultimasCompras = clienteDB.getCompras().stream()
                .map(i -> {
                    UltimaCompraDTO ultimaCompraDTO = new UltimaCompraDTO();
                    ultimaCompraDTO.setData(i.getData());
                    ultimaCompraDTO.setTotalGasto(i.getValorTotal());
                    return ultimaCompraDTO;
                })
                .sorted(Comparator.comparing(UltimaCompraDTO::getData).reversed())
                .collect(Collectors.toList());
        return ultimasCompras;
    }

    private static List<PreferenciaDTO> getPreferencias(ClienteDB clienteDB) {
        List<ProdutoDB> listaProdutos = clienteDB.getCompras().stream()
                .map(CompraDB::getProdutos)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        Map<String, Long> quantidadeProduto = listaProdutos.stream()
                .collect(Collectors.groupingBy(ProdutoDB::getIdProduto, Collectors.counting()));
        List<PreferenciaDTO> listaPreferencias = quantidadeProduto.entrySet().stream()
                .map(i -> produtoToPreferencia(
                        listaProdutos.stream()
                                .filter(j -> j.getIdProduto().equals(i.getKey()))
                                .findFirst().get(),
                        listaProdutos.stream()
                                .filter(j -> j.getIdProduto().equals(i.getKey()))
                                .mapToLong(ProdutoDB::getQuantidade)
                                .sum())
                ).sorted(Comparator.comparing(PreferenciaDTO::getQuantidadeTotal).reversed())
                .collect(Collectors.toList());
        return listaPreferencias;
    }

    private static PreferenciaDTO produtoToPreferencia(ProdutoDB produto, Long quantidade) {
        PreferenciaDTO preferenciaDTO = new PreferenciaDTO();
        preferenciaDTO.setCategoria(produto.getCategoria());
        preferenciaDTO.setCor(produto.getCor());
        preferenciaDTO.setDescricao(produto.getDescricao());
        preferenciaDTO.setGrife(produto.getGrife());
        preferenciaDTO.setIdProduto(produto.getIdProduto());
        preferenciaDTO.setLinha(produto.getLinha());
        preferenciaDTO.setQuantidadeTotal(quantidade);
        preferenciaDTO.setTamanho(produto.getTamanho());
        return preferenciaDTO;
    }
}
