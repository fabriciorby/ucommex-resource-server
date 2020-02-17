package br.com.ucommex.resourceserver.dto;

public class VendedorDTO {
    private String nomeVendedor;
    private Long quantidadeCompras;

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public Long getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(Long quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }
}
