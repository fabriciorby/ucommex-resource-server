package br.com.ucommex.resourceserver.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerStatsDTO {

    private String nome;
    private Date dataNascimento;
    private Date dataCadastro;
    private Integer quantidadeCompras;
    private LojaDTO lojaPreferida;
    private VendedorDTO vendedorPreferido;
    private BigDecimal ticketMedio;
    private List<UltimaCompraDTO> ultimasCompras;
    private List<PreferenciaDTO> preferencias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(Integer quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public LojaDTO getLojaPreferida() {
        return lojaPreferida;
    }

    public void setLojaPreferida(LojaDTO lojaPreferida) {
        this.lojaPreferida = lojaPreferida;
    }

    public VendedorDTO getVendedorPreferido() {
        return vendedorPreferido;
    }

    public void setVendedorPreferido(VendedorDTO vendedorPreferido) {
        this.vendedorPreferido = vendedorPreferido;
    }

    public BigDecimal getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(BigDecimal ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public List<UltimaCompraDTO> getUltimasCompras() {
        return ultimasCompras;
    }

    public void setUltimasCompras(List<UltimaCompraDTO> ultimasCompras) {
        this.ultimasCompras = ultimasCompras;
    }

    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaDTO> preferencias) {
        this.preferencias = preferencias;
    }
}
