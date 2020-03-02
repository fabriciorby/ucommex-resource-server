package br.com.ucommex.resourceserver.customer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UltimaCompraDTO {
    private Date data;
    private BigDecimal totalGasto;
}
