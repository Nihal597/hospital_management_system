package org.example.invoice.Invoice.model;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private BigDecimal amount;
    private String status;

    // Constructors, getters, and setters
    public Invoice() {}

    public Invoice(String customerName, BigDecimal amount, String status) {
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    // Getters and setters
}