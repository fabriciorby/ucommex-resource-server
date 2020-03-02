package br.com.ucommex.resourceserver.customer.helper;

import br.com.ucommex.resourceserver.customer.dto.*;
import br.com.ucommex.resourceserver.customer.pojo.ClienteDB;
import br.com.ucommex.resourceserver.customer.pojo.CompraDB;
import br.com.ucommex.resourceserver.customer.pojo.ProdutoDB;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.ucommex.resourceserver.customer.helper.CustomerDbHelper.getRawCustomerById;

public class CustomerStatsHelper {

    public static CustomerStatsDTO getCustomerStatsById(String idCliente) {
        return getCustomerStats(getRawCustomerById(idCliente));
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
