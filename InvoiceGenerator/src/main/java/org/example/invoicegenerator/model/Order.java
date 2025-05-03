package org.example.invoicegenerator.model;

import java.util.List;

public class Order {
    private int id;
    private List<Item> items;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Order(int id, String customerName, List<Item> items) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
