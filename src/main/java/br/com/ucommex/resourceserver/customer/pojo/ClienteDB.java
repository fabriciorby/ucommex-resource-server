package br.com.ucommex.resourceserver.customer.pojo;

import br.com.ucommex.resourceserver.db.ObjectDB;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class ClienteDB extends ObjectDB {
    private String cpf;
    private String nome;
    private Character genero;
    private List<CompraDB> compras;
    @BsonProperty(value = "id_cliente")
    private String idCliente;

    @BsonProperty(value = "data_nascimento")
    private Date dataNascimento;

    @BsonProperty(value = "data_cadastro")
    private Date dataCadastro;
}
