package br.com.ucommex.resourceserver.feedback.dto;

import lombok.Data;

@Data
public class CustomerFeedbackDTO {
    private String cpf;
    private String infoAtendimento;
    private String infoAtendimentoOutraLoja;
    private int nps;
}
