package com.bitpuzzle.invoicegenerator.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientDto {

    private Integer id;
    private String displayName;
    private String numeClient;
    private String nrRegistruClient;
    private String cuiClient;
    private String bancaClient;
    private String nrContClient;
    private String adresaClient;
    private String numarContract;
    private String contractDinDataDe;
    private String pretPerUnitate;

}
