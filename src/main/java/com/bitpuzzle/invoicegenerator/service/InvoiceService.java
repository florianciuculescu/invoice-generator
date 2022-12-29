package com.bitpuzzle.invoicegenerator.service;

import com.bitpuzzle.invoicegenerator.dto.InvoiceDto;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceService {

    public void generateInvoice(InvoiceDto invoice) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\CiuculeF\\Desktop\\personal";
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:invoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataEmitere = LocalDate.now().format(df);


        parameters.put("numarFactura", invoice.getNumarFactura());
        parameters.put("dataEmitere", dataEmitere);
        parameters.put("dataScadenta", invoice.getDataScadenta().format(df));
        parameters.put("numeClient", invoice.getClient().getNumeClient());
        parameters.put("nrRegistruClient", invoice.getClient().getNrRegistruClient());
        parameters.put("cuiClient", invoice.getClient().getCuiClient());
        parameters.put("bancaClient", invoice.getClient().getBancaClient());
        parameters.put("nrContClient", invoice.getClient().getNrContClient());
        parameters.put("adresaClient", invoice.getClient().getAdresaClient());
        parameters.put("numarContract", invoice.getClient().getNumarContract());
        parameters.put("contractDinDataDe", invoice.getClient().getContractDinDataDe());


        String perioadaServicii = invoice.getPerioadaServiciiStart().format(df) + " - " + invoice.getPerioadaServiciiEnd().format(df);

        parameters.put("perioadaServicii", perioadaServicii);

        Integer cantitate = invoice.getCantitate();
        Double pretPerUnitate = Double.valueOf(invoice.getClient().getPretPerUnitate());

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        String valoare = String.valueOf( decimalFormat.format(cantitate * pretPerUnitate));

        parameters.put("cantitate", String.valueOf(invoice.getCantitate()));
        parameters.put("pretPerUnitate", String.valueOf(pretPerUnitate));
        parameters.put("valoare", valoare);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.home") + "\\Desktop" + "\\invoice.pdf");
    }
}
