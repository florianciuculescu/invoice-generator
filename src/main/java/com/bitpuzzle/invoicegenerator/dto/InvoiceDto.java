package com.bitpuzzle.invoicegenerator.dto;

import com.bitpuzzle.invoicegenerator.model.Client;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class InvoiceDto {
    private String numarFactura;

    @DateTimeFormat(pattern = "dd/MMM/yyyy")
    private LocalDate dataScadenta;
    private Client client;
    private LocalDate perioadaServiciiStart;
    private LocalDate perioadaServiciiEnd;
    private Integer cantitate;

}
