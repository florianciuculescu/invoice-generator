package com.bitpuzzle.invoicegenerator.controller;

import com.bitpuzzle.invoicegenerator.model.Client;
import com.bitpuzzle.invoicegenerator.dto.InvoiceDto;
import com.bitpuzzle.invoicegenerator.service.ClientService;
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
    public final ClientService clientService;

    @GetMapping("/")
    public String showForm(Model model) {
        InvoiceDto invoiceDto = new InvoiceDto();
        model.addAttribute("invoice", invoiceDto);

        List<Client> clientList = clientService.fetchClientsList();
        model.addAttribute("clientList", clientList);
        return "index2.html";
    }

    @PostMapping("/generate")
    public String generateInvoice(@ModelAttribute("invoice") InvoiceDto invoiceDto) throws JRException, FileNotFoundException {
        invoiceService.generateInvoice(invoiceDto);
        return "generated";
    }
}
