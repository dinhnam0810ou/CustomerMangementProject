package com.ndn.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ndn.core.ServiceFactory;
import com.ndn.enums.Gender;
import com.ndn.enums.MembershipLevel;
import com.ndn.model.Customer;
import com.ndn.model.PaginatedResult;
import com.ndn.service.ICustomerService;
import com.ndn.utils.PageUtils;
import com.ndn.utils.ValidationUtils;

@WebServlet(urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        String requestURI = req.getRequestURI();
        if ("/customer/list".equalsIgnoreCase(requestURI)) {
            
            String name = req.getParameter("kw_name");
            String gender = req.getParameter("kw_gender");
            String phoneNumber= req.getParameter("kw_phone");
            String membershipLevel = req.getParameter("kw_membership_level");
            String page = req.getParameter("page");
            String queryString = "";
            int pageIndex = ValidationUtils.isNotEmpty(page) ? Integer.parseInt(page) - 1 : 0;
            if (ValidationUtils.isNotEmpty(name) || ValidationUtils.isNotEmpty(gender)
                    || ValidationUtils.isNotEmpty(phoneNumber) || ValidationUtils.isNotEmpty(membershipLevel)) {
                
                queryString ="&kw_name=" + name + "&kw_gender=" 
                    + gender + "&kw_phone=" + phoneNumber + "&kw_membership_level=" + membershipLevel;
            }
            PaginatedResult<Customer> paginatedResult = customerService.getCustomers(name, gender, phoneNumber, membershipLevel, pageIndex);
            req.setAttribute("customers", paginatedResult.getItems());
            req.setAttribute("counter", paginatedResult.getCount());        
            req.setAttribute("queryString", queryString);
            req.setAttribute("pageSize", PageUtils.PAGE_SIZE);
            req.setAttribute("genders", Gender.values());
            req.setAttribute("membershipLevels", MembershipLevel.values());
            getServletContext().getRequestDispatcher("/pages/customerList.jsp").forward(req, resp);  
            
        } else if (requestURI.contains("/customer/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("delete customer id: " + id);
            customerService.deleteCustomerById(id);
            resp.sendRedirect(req.getContextPath() + "/customer/list");
                
        } else if (requestURI.contains("/customer/view")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Customer customer = customerService.getCustomerById(id);
            req.setAttribute("customer", customer);
            req.setAttribute("membershipLevel", customer.getMembershipLevel());
            req.setAttribute("ticketFree", customer.getTicketFree());
            getServletContext().getRequestDispatcher("/pages/customerDetail.jsp").forward(req, resp);     
        
        } else if (requestURI.contains("/customer/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("customer", customerService.getCustomerById(id));  
            req.setAttribute("genders", Gender.values());
            getServletContext().getRequestDispatcher("/pages/customerSave.jsp").forward(req, resp);
        }
    }
    
   
}
