package org.example.invoicegenerator.service;

import org.example.invoicegenerator.model.Order;

import java.io.IOException;

public interface InvoiceService {
    public byte[] generateInvoice(Order order) throws IOException;
}
