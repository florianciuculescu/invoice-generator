package com.bitpuzzle.invoicegenerator.model;

import org.springframework.core.convert.converter.Converter;

public class StringToClientConverter implements Converter<String, Client> {
    @Override
    public Client convert(String source) {

        return getHCLClient();
    }

    private Client getHCLClient() {
        return Client.builder()
                .clientId(1L)
                .displayName("HCL")
                .numeClient("HCL TECHNOLOGIES ROMANIA S.R.L")
                .nrRegistruClient("J40/6349/2009")
                .cuiClient("RO 25612455")
                .bancaClient("Citi Bank")
                .nrContClient("RO84CITI0000000724623018")
                .adresaClient("11 Uruguay str., 2nd floor, Desk 21, 1st District, Bucharest, Romania, Romania")
                .numarContract("134")
                .contractDinDataDe("06.10.2022")
                .pretPerUnitate(171.633)
                .build();
    }
}
