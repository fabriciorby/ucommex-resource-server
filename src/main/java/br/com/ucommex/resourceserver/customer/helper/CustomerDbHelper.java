package br.com.ucommex.resourceserver.customer.helper;

import br.com.ucommex.resourceserver.customer.pojo.ClienteDB;
import br.com.ucommex.resourceserver.customer.pojo.CompraDB;
import br.com.ucommex.resourceserver.customer.pojo.ProdutoDB;
import com.mongodb.client.MongoCollection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static br.com.ucommex.resourceserver.db.MongoConnect.getDatabase;
import static com.mongodb.client.model.Filters.eq;

public class CustomerDbHelper {

    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "To show an example of a custom message")
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
        }
    }

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

    public static ClienteDB getClienteByCpf(String cpf) {
        MongoCollection<ClienteDB> clienteCollection = getDatabase().getCollection("cliente", ClienteDB.class);
        return clienteCollection.find(eq("id_cliente", cpf)).first();
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

    public static CompraDB getById(CompraDB compraDB) {
        MongoCollection<CompraDB> clienteCollection = getDatabase().getCollection("compra", CompraDB.class);
        return clienteCollection.find(eq("_id", compraDB.getId())).first();
    }

    public static ProdutoDB getById(ProdutoDB produtoDB) {
        MongoCollection<ProdutoDB> clienteCollection = getDatabase().getCollection("produto", ProdutoDB.class);
        return clienteCollection.find(eq("_id", produtoDB.getId())).first();
    }

}
