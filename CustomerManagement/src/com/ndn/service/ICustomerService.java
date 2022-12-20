package com.ndn.service;



import com.ndn.model.Customer;
import com.ndn.model.PaginatedResult;



public interface ICustomerService {
    public void addCustomer(Customer customer);
    public void deleteCustomerById(int id);
    public Customer getCustomerById(int id);
    public void updateCustomer(Customer customer);
    public PaginatedResult getCustomers(String name, String gender, String phone, String membershipLevel,int pageIndex);
}
