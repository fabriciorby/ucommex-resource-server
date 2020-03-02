package br.com.ucommex.resourceserver.customer.pojo;

import br.com.ucommex.resourceserver.db.ObjectDB;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Data
public class ProdutoDB extends ObjectDB {
    private String descricao;
    private String categoria;
    private String linha;
    private String cor;
    private String tamanho;
    private Integer quantidade;
    private String grife;

    @BsonProperty(value = "preco_total")
    private BigDecimal total;

    @BsonProperty(value = "preco_unitario")
    private BigDecimal precoUnitario;

    @BsonProperty(value = "id_produto")
    private String idProduto;

    @BsonProperty(value = "id_compra")
    private String idCompra;
}
