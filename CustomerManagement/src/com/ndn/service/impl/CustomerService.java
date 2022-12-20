package com.ndn.service.impl;



import com.ndn.datasource.CustomerDAO;
import com.ndn.model.Customer;
import com.ndn.model.PaginatedResult;
import com.ndn.service.ICustomerService;

public class CustomerService implements ICustomerService{
    
    CustomerDAO customerDAO = new CustomerDAO();
    
    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
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
    public PaginatedResult getCustomers(String name, String gender, String phone, String membershipLevel,
            int pageIndex) {
        return customerDAO.getCustomers(name, gender, phone, membershipLevel, pageIndex);
    }


}
