package br.com.ucommex.resourceserver.customer.pojo;

import br.com.ucommex.resourceserver.db.ObjectDB;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CompraDB extends ObjectDB {
    private String loja;
    private String vendedor;
    private String canal;
    private Date data;
    private List<ProdutoDB> produtos;
    @BsonProperty(value = "id_compra")
    private String idCompra;

    @BsonProperty(value = "id_cliente")
    private String idCliente;

    @BsonProperty(value = "valor_total")
    private BigDecimal valorTotal;

    @BsonProperty(value = "quantidade_total")
    private Integer quantidadeTotal;
}
