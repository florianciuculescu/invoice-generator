package com.bitpuzzle.invoicegenerator.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

@Getter
@Setter
@ToString
public class InvoiceDto {
    private String numarFactura;

    private LocalDate dataScadenta;
    private ClientDto clientDto;
    private LocalDate perioadaServiciiStart;
    private LocalDate perioadaServiciiEnd;
    private LocalDate dataEmitere;
    private Integer cantitate;

}
