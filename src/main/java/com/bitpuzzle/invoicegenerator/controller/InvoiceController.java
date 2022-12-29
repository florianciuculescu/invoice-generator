package com.bitpuzzle.invoicegenerator.controller;

import com.bitpuzzle.invoicegenerator.model.Client;
import com.bitpuzzle.invoicegenerator.model.InvoiceDto;
import com.bitpuzzle.invoicegenerator.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileNotFoundException;
import java.util.*;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class InvoiceController {

    public final InvoiceService invoiceService;

    @GetMapping("/")
    public String showForm(Model model) {
        InvoiceDto invoiceDto = new InvoiceDto();
        model.addAttribute("invoice", invoiceDto);

        // mocking db retrieval
        List<Client> clientList = Arrays.asList(getHCLClient());
        model.addAttribute("clientList", clientList);
        return "index.html";
    }

    @PostMapping("/generate")
    public String generateInvoice(@ModelAttribute("invoice") InvoiceDto invoiceDto) throws JRException, FileNotFoundException {
        invoiceService.generateInvoice(invoiceDto);
        return "generated";
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
