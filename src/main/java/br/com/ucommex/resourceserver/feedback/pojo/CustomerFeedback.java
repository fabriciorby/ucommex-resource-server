package br.com.ucommex.resourceserver.feedback.pojo;

import br.com.ucommex.resourceserver.db.ObjectDB;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerFeedback extends ObjectDB {
    private String cpf;
    private String infoAtendimento;
    private String infoAtendimentoOutraLoja;
    private int nps;
}
