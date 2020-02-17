package br.com.ucommex.resourceserver.dto;

public class LojaDTO {
    private String nomeLoja;
    private Long quantidadeCompras;

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public Long getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(Long quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }
}
