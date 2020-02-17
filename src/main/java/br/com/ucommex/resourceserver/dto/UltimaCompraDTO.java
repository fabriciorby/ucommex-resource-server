package br.com.ucommex.resourceserver.dto;

import java.math.BigDecimal;
import java.util.Date;

public class UltimaCompraDTO {

    private Date data;
    private BigDecimal totalGasto;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(BigDecimal totalGasto) {
        this.totalGasto = totalGasto;
    }
}
