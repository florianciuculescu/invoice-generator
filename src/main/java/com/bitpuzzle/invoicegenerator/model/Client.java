package com.bitpuzzle.invoicegenerator.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Long clientId;
    private String displayName;
    private String numeClient;
    private String nrRegistruClient;
    private String cuiClient;
    private String bancaClient;
    private String nrContClient;
    private String adresaClient;
    private String numarContract;
    private String contractDinDataDe;
    private Double pretPerUnitate;

    @Override
    public String toString() {
        return displayName;
    }
}
