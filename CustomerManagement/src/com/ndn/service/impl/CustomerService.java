package com.ndn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ndn.datasource.CustomerDAO;
import com.ndn.model.Customer;
import com.ndn.service.ICustomerService;

public class CustomerService implements ICustomerService{
    
    CustomerDAO customerDAO = new CustomerDAO();
    
    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public List<Customer> getCustomers(int page) {
        return customerDAO.getCustomers(page);
    }

    @Override
    public void deleteCustomerById(int id) {
        customerDAO.deleteCustomerById(id);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
       customerDAO.updateCustomer(customer);
    }

    @Override
    public List<Customer> searchCustomers(String name, String gender, String phone, String membership_level) {
        return customerDAO.searchCustomers(name, gender, phone, membership_level);
    }

    @Override
    public int countCustomer() {
        return customerDAO.countCustomer();
    }

}
