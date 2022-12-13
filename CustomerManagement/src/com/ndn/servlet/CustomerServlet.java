package com.ndn.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ndn.core.ServiceFactory;
import com.ndn.datasource.CustomerDAO;
import com.ndn.enums.Gender;
import com.ndn.model.Customer;
import com.ndn.service.ICustomerService;





@WebServlet(urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        String requestURI = req.getRequestURI();
        if ("/customer/list".equalsIgnoreCase(requestURI)) {
            String name = req.getParameter("kw_name");
            String gender = req.getParameter("kw_gender");
            String phone_number= req.getParameter("kw_phone");
            String membership_level = req.getParameter("kw_membership_level");
            String page = req.getParameter("page");
            try {       
                req.setAttribute("customers", 
                        customerService.searchCustomers(name, gender, phone_number, membership_level));          
            } catch (Exception e) {
                if(page == null || page.isEmpty()) {
                    req.setAttribute("customers", customerService.getCustomers(1));
                }
                else {
                    req.setAttribute("customers", customerService.getCustomers(Integer.parseInt(page)));
                }
            }
            req.setAttribute("counter", customerService.countCustomer());
            req.setAttribute("pageSize", CustomerDAO.PAGE_SIZE);
            getServletContext().getRequestDispatcher("/pages/customerList.jsp").forward(req, resp);
            
        } else if (requestURI.contains("/customer/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("delete customer id: " + id);
            customerService.deleteCustomerById(id);
            resp.sendRedirect(req.getContextPath() + "/customer/list");
            
        } else if (requestURI.contains("/customer/view")) {
            String id = req.getParameter("id");
            req.setAttribute("customer", customerService.getCustomerById(Integer.parseInt(id)));
            req.setAttribute("membership_level", customerService.getCustomerById(Integer.parseInt(id)).getMembershipLevel());
            req.setAttribute("ticket_free", customerService.getCustomerById(Integer.parseInt(id)).getTicketFree());
            getServletContext().getRequestDispatcher("/pages/customerDetail.jsp").forward(req, resp);     
        
        } else if (requestURI.contains("/customer/edit")) {
            String id = req.getParameter("id");
            req.setAttribute("customer", customerService.getCustomerById(Integer.parseInt(id)));
            getServletContext().getRequestDispatcher("/pages/customerEdit.jsp").forward(req, resp);  
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        String requestURI = req.getRequestURI();
        System.out.println("POST RequestURI: " + req.getRequestURI());
        if ("/customer/add".equalsIgnoreCase(requestURI)) {
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String phoneNumber = req.getParameter("phone");
            String address = req.getParameter("address");
            String email = req.getParameter("email");
            
            if(name.isEmpty() || name == null) {
                System.out.println("Name can not empty");    
            }
            else if(gender.isEmpty() || gender == null) {
                System.out.println("Gender can not empty");
            }
            else if(phoneNumber.isEmpty() || phoneNumber == null) {
                System.out.println("Phone number can not empty");
            }
            else {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setGender(Gender.valueOf(gender));
                customer.setPhoneNumber(phoneNumber);
                customer.setAddress(address);
                customer.setEmail(email);
                customerService.addCustomer(customer);    
            }       
            resp.sendRedirect(req.getContextPath() + "/customer/list");
            
        } else if (requestURI.contains("/customer/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String phoneNumber = req.getParameter("phone");
            String address = req.getParameter("address");
            String email = req.getParameter("email");  
            int point = Integer.parseInt(req.getParameter("point"));
            
            if(name.isEmpty() || name == null) {
                System.out.println("Name can not empty");    
            }
            else if(gender.isEmpty() || gender == null) {
                System.out.println("Gender can not empty");
            }
            else if(phoneNumber.isEmpty() || phoneNumber == null) {
                System.out.println("Phone number can not empty");
            }
            else {
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setGender(Gender.valueOf(gender));
                customer.setPhoneNumber(phoneNumber);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setPoint(point);
                customerService.updateCustomer(customer);        
            }   
            resp.sendRedirect(req.getContextPath() + "/customer/list");
        }          
    }
}
