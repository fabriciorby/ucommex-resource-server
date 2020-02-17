package br.com.ucommex.resourceserver.pojo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class ClienteDB extends ObjectDB{

    private ObjectId id;
    private String cpf;
    private String nome;
    private Character genero;
    private List<CompraDB> compras;

    @Override
    public String toString() {
        return "ClienteDB{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", genero=" + genero +
                ", compras=" + compras +
                ", idCliente='" + idCliente + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataCadastro=" + dataCadastro +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public List<CompraDB> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraDB> compras) {
        this.compras = compras;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @BsonProperty(value = "id_cliente")
    private String idCliente;

    @BsonProperty(value = "data_nascimento")
    private Date dataNascimento;

    @BsonProperty(value = "data_cadastro")
    private Date dataCadastro;

}
