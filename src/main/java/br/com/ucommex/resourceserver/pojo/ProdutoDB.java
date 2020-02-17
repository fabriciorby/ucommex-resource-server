package br.com.ucommex.resourceserver.pojo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class ProdutoDB extends ObjectDB {

    private ObjectId id;
    private String descricao;
    private String categoria;
    private String linha;
    private String cor;
    private String tamanho;
    private Integer quantidade;
    private String grife;

    @Override
    public String toString() {
        return "ProdutoDB{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", linha='" + linha + '\'' +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", quantidade=" + quantidade +
                ", grife='" + grife + '\'' +
                ", total=" + total +
                ", precoUnitario=" + precoUnitario +
                ", idProduto='" + idProduto + '\'' +
                ", idCompra='" + idCompra + '\'' +
                '}';
    }

    @BsonProperty(value = "preco_total")
    private BigDecimal total;

    @BsonProperty(value = "preco_unitario")
    private BigDecimal precoUnitario;

    @BsonProperty(value = "id_produto")
    private String idProduto;

    @BsonProperty(value = "id_compra")
    private String idCompra;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getGrife() {
        return grife;
    }

    public void setGrife(String grife) {
        this.grife = grife;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }
}
