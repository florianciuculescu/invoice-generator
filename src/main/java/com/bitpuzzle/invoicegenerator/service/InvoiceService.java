package com.bitpuzzle.invoicegenerator.service;

import com.bitpuzzle.invoicegenerator.dto.ClientDto;
import com.bitpuzzle.invoicegenerator.dto.InvoiceDto;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static constants.InvoiceConstants.HCL;
import static constants.InvoiceConstants.INVOICE_HCL_JRXML;
import static constants.InvoiceConstants.INVOICE_TOTAL_JRXML;
import static constants.InvoiceConstants.TOTAL_SOFT;

@Service
public class InvoiceService {

    public void generateInvoice(InvoiceDto invoiceDto) throws FileNotFoundException, JRException {
        //load file and compile it
        ClientDto clientDto = invoiceDto.getClientDto();
        File file = getFileByClient(clientDto);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = createParameters(invoiceDto,clientDto);
        createParameters(invoiceDto, clientDto);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "invoice.pdf");
    }

    private Map<String, Object> createParameters(InvoiceDto invoiceDto, ClientDto clientDto) {
        Map<String, Object> parameters = new HashMap<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        parameters.put("numarFactura", invoiceDto.getNumarFactura());
        parameters.put("dataEmitere", invoiceDto.getDataEmitere().format(df));
        parameters.put("dataScadenta", invoiceDto.getDataScadenta().format(df));
        parameters.put("numeClient", clientDto.getNumeClient());
        parameters.put("nrRegistruClient", clientDto.getNrRegistruClient());
        parameters.put("cuiClient", clientDto.getCuiClient());
        parameters.put("bancaClient", clientDto.getBancaClient());
        parameters.put("nrContClient", clientDto.getNrContClient());
        parameters.put("adresaClient", clientDto.getAdresaClient());
        parameters.put("numarContract", clientDto.getNumarContract());
        parameters.put("contractDinDataDe", clientDto.getContractDinDataDe());
        parameters.put("sapId", clientDto.getSapId());
        parameters.put("cursValutar", String.valueOf(invoiceDto.getCursValutar()));
        String perioadaServicii = invoiceDto.getPerioadaServiciiStart().format(df) + " - " + invoiceDto.getPerioadaServiciiEnd().format(df);

        parameters.put("perioadaServicii", perioadaServicii);

        Integer cantitate = invoiceDto.getCantitate();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        Double pretPerUnitate = Double.valueOf(clientDto.getPretPerUnitate());
        if(TOTAL_SOFT.equals(clientDto.getDisplayName())) {
            pretPerUnitate = Double.valueOf(decimalFormat.format(invoiceDto.getCursValutar() * 35));
        }

        double valoareCalculata = cantitate * pretPerUnitate;
        String valoare = formatAmounts(valoareCalculata);

        parameters.put("cantitate", String.valueOf(invoiceDto.getCantitate()));
        parameters.put("pretPerUnitate", String.valueOf(pretPerUnitate));
        parameters.put("valoare", valoare);

        double valTva = valoareCalculata * 0.19;
        parameters.put("tva", formatAmounts(valTva));
        parameters.put("valTotal", formatAmounts(valoareCalculata + valTva));

        return parameters;
    }

    private static File getFileByClient(ClientDto clientDto) throws FileNotFoundException {
        File file;
        if(TOTAL_SOFT.equals(clientDto.getDisplayName())) {
            file = ResourceUtils.getFile("classpath:" + INVOICE_TOTAL_JRXML);
        } else if (HCL.equals(clientDto.getDisplayName())) {
            file = ResourceUtils.getFile("classpath:" + INVOICE_HCL_JRXML);
        } else {
            throw new RuntimeException("No Client Selected !!! ");
        }
        return file;
    }

    private String formatAmounts(double val) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return String.valueOf(decimalFormat.format(val));
    }
}
