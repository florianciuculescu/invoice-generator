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

    public void generateInvoice(InvoiceDto invoiceDto) throws FileNotFoundException, JRException {
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:invoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        parameters.put("numarFactura", invoiceDto.getNumarFactura());
        parameters.put("dataEmitere", invoiceDto.getDataEmitere().format(df));
        parameters.put("dataScadenta", invoiceDto.getDataScadenta().format(df));
        parameters.put("numeClient", invoiceDto.getClientDto().getNumeClient());
        parameters.put("nrRegistruClient", invoiceDto.getClientDto().getNrRegistruClient());
        parameters.put("cuiClient", invoiceDto.getClientDto().getCuiClient());
        parameters.put("bancaClient", invoiceDto.getClientDto().getBancaClient());
        parameters.put("nrContClient", invoiceDto.getClientDto().getNrContClient());
        parameters.put("adresaClient", invoiceDto.getClientDto().getAdresaClient());
        parameters.put("numarContract", invoiceDto.getClientDto().getNumarContract());
        parameters.put("contractDinDataDe", invoiceDto.getClientDto().getContractDinDataDe());


        String perioadaServicii = invoiceDto.getPerioadaServiciiStart().format(df) + " - " + invoiceDto.getPerioadaServiciiEnd().format(df);

        parameters.put("perioadaServicii", perioadaServicii);

        Integer cantitate = invoiceDto.getCantitate();
        Double pretPerUnitate = Double.valueOf(invoiceDto.getClientDto().getPretPerUnitate());

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        String valoare = String.valueOf( decimalFormat.format(cantitate * pretPerUnitate));

        parameters.put("cantitate", String.valueOf(invoiceDto.getCantitate()));
        parameters.put("pretPerUnitate", String.valueOf(pretPerUnitate));
        parameters.put("valoare", valoare);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.home") + "\\Desktop" + "\\invoice.pdf");
    }
}
