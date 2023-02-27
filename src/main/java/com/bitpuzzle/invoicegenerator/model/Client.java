package com.bitpuzzle.invoicegenerator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "nume_client")
    private String numeClient;

    @Column(name = "nr_registru_client")
    private String nrRegistruClient;

    @Column(name = "cui_client")
    private String cuiClient;

    @Column(name = "banca_client")
    private String bancaClient;

    @Column(name = "nr_cont_client")
    private String nrContClient;

    @Column(name = "adresa_client")
    private String adresaClient;

    @Column(name = "numar_contract")
    private String numarContract;

    @Column(name = "contract_din_data_de")
    private String contractDinDataDe;

    @Column(name = "pret_per_unitate")
    private String pretPerUnitate;

    @Column(name = "sap_id")
    private String sapId;

    //TODO: needs to be removed. make a dto and use the mapper
    @Override
    public String toString() {
        return displayName;
    }
}
