package br.com.ucommex.resourceserver.customer.dto;

import lombok.Data;

@Data
public class PreferenciaDTO {
    private String descricao;
    private String categoria;
    private String linha;
    private String cor;
    private String tamanho;
    private Long quantidadeTotal;
    private String grife;
    private String idProduto;
}
