
package br.com.ucommex.resourceserver.feedback.dto;

import lombok.Data;

@Data
public class EmployeeFeedbackDTO {
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
