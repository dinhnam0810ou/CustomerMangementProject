package com.ndn.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.ndn.core.ServiceFactory;
import com.ndn.enums.Gender;
import com.ndn.model.Customer;
import com.ndn.service.ICustomerService;
import com.ndn.utils.ValidationUtils;
@WebServlet(urlPatterns = "/customer/save")
public class SaveCustomerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {     
        String error = req.getParameter("error");
        if (ValidationUtils.isNotEmpty(error)) req.setAttribute("errorId", "Can not find id");
        req.setAttribute("genders", Gender.getGenders());
        getServletContext().getRequestDispatcher("/pages/customerSave.jsp").forward(req, resp);  
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String phoneNumber = req.getParameter("phone");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String point = req.getParameter("point");
        if (ValidationUtils.isEmpty(name) || ValidationUtils.isEmpty(gender) || ValidationUtils.isEmpty(phoneNumber)) {
            if (ValidationUtils.isEmpty(name)) req.setAttribute("errorName", "Name can not empty");
            if (ValidationUtils.isEmpty(gender)) req.setAttribute("errorGender", "Gender can not empty");
            if (ValidationUtils.isEmpty(phoneNumber)) req.setAttribute("errorPhone", "Phone can not empty");
            if (ValidationUtils.isEmpty(id) && ValidationUtils.isNotEmpty(point)) {
                req.setAttribute("errorPoint", "Add new customer can not need point");  
            }
            try {
                Customer customerById = customerService.getCustomerById(Integer.parseInt(id));
            } catch (Exception e) {
                req.setAttribute("errorId", "Can not find id"); 
            }
            req.setAttribute("genders", Gender.getGenders());
            getServletContext().getRequestDispatcher("/pages/customerSave.jsp").forward(req, resp);
            return;
        }
        if (ValidationUtils.isEmpty(id) && ValidationUtils.isEmpty(point)) {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setGender(Gender.valueOf(gender));
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            customer.setEmail(email);
            customerService.addCustomer(customer);
            resp.sendRedirect(req.getContextPath() + "/customer/list");
            return;
        } 
        try {
            if (customerService.getCustomerById(Integer.parseInt(id))!= null) {
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(id));
                customer.setName(name);
                customer.setGender(Gender.valueOf(gender));
                customer.setPhoneNumber(phoneNumber);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setPoint(Integer.parseInt(point));
                customerService.updateCustomer(customer); 
                resp.sendRedirect(req.getContextPath() + "/customer/list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
