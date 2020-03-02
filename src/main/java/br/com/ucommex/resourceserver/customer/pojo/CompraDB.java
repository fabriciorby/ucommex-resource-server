package br.com.ucommex.resourceserver.customer.pojo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CompraDB extends ObjectDB {
    private ObjectId id;
    private String loja;
    private String vendedor;
    private String canal;
    private Date data;
    private List<ProdutoDB> produtos;

    @Override
    public String toString() {
        return "CompraDB{" +
                "id=" + id +
                ", loja='" + loja + '\'' +
                ", vendedor='" + vendedor + '\'' +
                ", canal='" + canal + '\'' +
                ", data=" + data +
                ", produtos=" + produtos +
                ", idCompra='" + idCompra + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", valorTotal=" + valorTotal +
                ", quantidadeTotal=" + quantidadeTotal +
                '}';
    }

    @BsonProperty(value = "id_compra")
    private String idCompra;

    @BsonProperty(value = "id_cliente")
    private String idCliente;

    @BsonProperty(value = "valor_total")
    private BigDecimal valorTotal;

    @BsonProperty(value = "quantidade_total")
    private Integer quantidadeTotal;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ProdutoDB> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDB> produtos) {
        this.produtos = produtos;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
}
