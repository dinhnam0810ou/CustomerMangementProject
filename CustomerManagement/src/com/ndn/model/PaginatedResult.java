package com.ndn.model;

import java.util.List;

public class PaginatedResult {
    private int count;
    private List<Customer> customers;
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
}
