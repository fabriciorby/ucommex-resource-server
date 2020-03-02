package br.com.ucommex.resourceserver.customer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
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

}
