package com.ndn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ndn.core.ServiceFactory;
import com.ndn.enums.Error;
import com.ndn.model.Customer;
import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;
import com.ndn.service.ICustomerService;
import com.ndn.service.IMovieService;
import com.ndn.service.ITicketOrderService;
import com.ndn.utils.PageUtils;
import com.ndn.utils.ValidationUtils;
@WebServlet(urlPatterns = "/order/*")
public class TicketOrderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IMovieService movieService = ServiceFactory.get(IMovieService.class);
        ITicketOrderService ticketOrderService = ServiceFactory.get(ITicketOrderService.class);
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        String requestURI = req.getRequestURI();
        if ("/order/view".equalsIgnoreCase(requestURI)) {
            String error = req.getParameter("error");
            if (ValidationUtils.isNotEmpty(error)) {
                if (error.equals("quantity")) req.setAttribute(Error.Quantity.toString(), PageUtils.getErrorMessage(Error.Quantity));
                if (error.equals("freeTicket")) req.setAttribute(Error.FreeTicket.toString(), PageUtils.getErrorMessage(Error.FreeTicket));
            }
            req.setAttribute("movies", movieService.getMovies().getItems());
            List<Customer> customers = customerService.getCustomers();
            req.setAttribute("customers", customers);
            getServletContext().getRequestDispatcher("/pages/ticketOrderView.jsp").forward(req, resp);  
        } if (requestURI.equals("/order/list")) {
            String page = req.getParameter("page");
            int pageIndex = ValidationUtils.isNotEmpty(page) ? Integer.parseInt(page) - 1 : 0;
            PaginatedResult<TicketOrder> paginatedResult = ticketOrderService.getTicketOrders(pageIndex);
            req.setAttribute("ticketOrders", paginatedResult.getItems());
            req.setAttribute("counter", paginatedResult.getCount());        
            req.setAttribute("pageSize", PageUtils.PAGE_SIZE);  
            getServletContext().getRequestDispatcher("/pages/ticketOrderList.jsp").forward(req, resp);  
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        ITicketOrderService ticketOrderService = ServiceFactory.get(ITicketOrderService.class);
        IMovieService movieService = ServiceFactory.get(IMovieService.class);
        String requestURI = req.getRequestURI();
        if ("/order/save".equalsIgnoreCase(requestURI)) {
            String option = req.getParameter("option");
            int freeTicket = Integer.parseInt(req.getParameter("freeTicket"));
            int movieId = Integer.parseInt(req.getParameter("movieId"));    
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int customerId = 0;
            if (ValidationUtils.isNotEmpty(req.getParameter("customerId"))) {
                customerId = Integer.parseInt(req.getParameter("customerId"));
            }
            Movie movie = movieService.getMovieById(movieId);
            
            if (quantity > 0) {
                if (option.equals("false")) {
                    TicketOrder ticketOrder = new TicketOrder();
                    ticketOrder.setMovie(movie);
                    ticketOrder.setUnitPrice(movie.getPrice());
                    ticketOrder.setQuantity(quantity);
                    ticketOrder.setCustomer(null);
                    ticketOrderService.addTicketOrder(ticketOrder);
                    resp.sendRedirect(req.getContextPath() + "/order/list");
                } else {
                    Customer customer = customerService.getCustomerById(customerId);
                    if (freeTicket <= customer.getTicketFree() && freeTicket >= 0 && freeTicket <= quantity) {
                        customer.setUseFreeTicket(customer.getUseFreeTicket() + freeTicket);
                        customerService.updateCustomer(customer);
                        TicketOrder ticketOrder = new TicketOrder();
                        ticketOrder.setMovie(movie);
                        ticketOrder.setQuantity(quantity);
                        ticketOrder.setUnitPrice(movie.getPrice());
                        ticketOrder.setCustomer(customer);
                        ticketOrderService.addTicketOrder(ticketOrder);
                        resp.sendRedirect(req.getContextPath() + "/order/list");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/order/view?error=freeTicket");
                    }
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/order/view?error=quantity");
            }
        }
    }
}
