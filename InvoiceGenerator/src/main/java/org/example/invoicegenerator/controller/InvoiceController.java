package org.example.invoicegenerator.controller;

import org.example.invoicegenerator.model.Item;
import org.example.invoicegenerator.model.Order;
import org.example.invoicegenerator.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/getInvoice/{userId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable("userId") int userId) throws IOException {
        Item i1 =new Item("Complete Blood Count (CBC)", 100, 1);
        Item i2 =new Item("Basic Metabolic Panel (BMP)", 1200, 1);
        Item i3 =new Item("Lipid Panel", 700, 1);
        Item i4 =new Item("Liver Function Tests (LFT):", 2000, 2);
        Item i5 =new Item("Thyroid Function Tests", 250, 2);


        Order order =new Order(userId, "Virat kohli", Arrays.asList(i1,i2,i3,i4,i5));
        byte [] bytes= 	invoiceService.generateInvoice(order);

        HttpHeaders headers =new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");


        return ResponseEntity.ok().headers(headers).body(bytes);
    }
}
