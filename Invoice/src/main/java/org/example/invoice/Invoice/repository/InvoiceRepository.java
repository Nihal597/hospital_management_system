package org.example.invoice.Invoice.repository;

import org.example.invoice.Invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // Custom query methods can be defined here if needed
}