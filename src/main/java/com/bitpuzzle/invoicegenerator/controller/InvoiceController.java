package com.bitpuzzle.invoicegenerator.controller;

import net.sf.jasperreports.engine.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InvoiceController {

    @GetMapping("/invoice")
    public void createInvoice() throws FileNotFoundException, JRException {

        String path = "C:\\Users\\CiuculeF\\Desktop\\personal";
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:invoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();

        LocalDate localDateNow = LocalDate.now();
        LocalDate dataScadenta = LocalDate.of(2022,12,15);

        parameters.put("numarFactura", "2");
        parameters.put("dataEmitere", Date.from(localDateNow.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        parameters.put("dataScadenta", Date.from(dataScadenta.atStartOfDay(ZoneId.systemDefault()).toInstant()));


        parameters.put("numeClient", "HCL TECHNOLOGIES ROMANIA S.R.L");
        parameters.put("nrRegistruClient", "J40/6349/2009");
        parameters.put("cuiClient", "RO 25612455");
        parameters.put("bancaClient", "Citi Bank");
        parameters.put("nrContClient", "RO84CITI0000000724623018");
        parameters.put("adresaClient", "11 Uruguay str., 2nd floor, Desk 21," +
                " 1st District, Bucharest, Romania, Romania");

        parameters.put("numarContract", "134");
        parameters.put("contractDinDataDe", "06.10.2022");
        parameters.put("perioadaServicii", "01-12-2022 - 22-12-2022");

        Double pretPerUnitate = 171.633;
        Double valoare = 20595.960;

        parameters.put("cantitate", "120");
        parameters.put("pretPerUnitate", pretPerUnitate);
        parameters.put("valoare", valoare);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\invoice.pdf");

    }
}
