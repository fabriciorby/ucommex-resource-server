package br.com.ucommex.resourceserver.feedback.pojo;

import br.com.ucommex.resourceserver.db.ObjectDB;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeFeedback extends ObjectDB {
    private String idLojista;
    private String cpfCliente;
    private Boolean converteu;
    private Boolean dadosHistoricosAjudaram;
    private Boolean dadosPessoaisAjudaram;
    private Boolean informacoesAjudaram;
    private String motivoDadosHistoricos;
    private String motivoDadosPessoais;
    private String motivoNaoConverteu;
    private String percepcao;
}
