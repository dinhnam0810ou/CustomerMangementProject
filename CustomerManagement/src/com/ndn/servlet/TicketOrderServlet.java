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
import com.ndn.enums.Gender;
import com.ndn.model.Customer;
import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;
import com.ndn.service.ICustomerService;
import com.ndn.service.IMovieService;
import com.ndn.service.ITicketOrderService;
import com.ndn.utils.ViewUtils;
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
                if (error.equals("quantity")) req.setAttribute(Error.Quantity.toString(), ViewUtils.getErrorMessage(Error.Quantity));
                if (error.equals("freeTicket")) req.setAttribute(Error.FreeTicket.toString(), ViewUtils.getErrorMessage(Error.FreeTicket));
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
            req.setAttribute("pageSize", ViewUtils.PAGE_SIZE);  
            getServletContext().getRequestDispatcher("/pages/ticketOrderList.jsp").forward(req, resp);  
        } else if (requestURI.contains("/order/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            ticketOrderService.deleteTicketOrder(id);
            resp.sendRedirect(req.getContextPath() + "/order/list");
        } else if (requestURI.contains("/order/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("movies", movieService.getMovies().getItems());
            TicketOrder ticketOrder = ticketOrderService.getTicketOrderById(id);
            req.setAttribute("ticketOrder", ticketOrder);  
            getServletContext().getRequestDispatcher("/pages/ticketOrderView.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = ServiceFactory.get(ICustomerService.class);
        ITicketOrderService ticketOrderService = ServiceFactory.get(ITicketOrderService.class);
        IMovieService movieService = ServiceFactory.get(IMovieService.class);
        String requestURI = req.getRequestURI();
        if ("/order/save".equalsIgnoreCase(requestURI)) {
            String ticketOrderId = req.getParameter("orderId");
            String existingCustomerSeletion = req.getParameter("customerRadioChecked");
            int freeTicket = Integer.parseInt(req.getParameter("freeTicket"));
            int movieId = Integer.parseInt(req.getParameter("movieId"));    
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int customerId = 0;
            if (ValidationUtils.isNotEmpty(req.getParameter("customerId"))) {
                customerId = Integer.parseInt(req.getParameter("customerId"));
            }
            Movie movie = movieService.getMovieById(movieId);
            Customer customer = null;
            if (quantity > 0) {
                if (ValidationUtils.isNotEmpty(existingCustomerSeletion) && existingCustomerSeletion.equals("true")) {
                    customer = customerService.getCustomerById(customerId);
                    if (freeTicket > customer.getTicketFree() || freeTicket < 0 || freeTicket > quantity) {
                        resp.sendRedirect(req.getContextPath() + "/order/view?error=freeTicket");
                        return;
                    }
                } else {
                    freeTicket = 0;
                }
                TicketOrder ticketOrder = new TicketOrder();
                ticketOrder.setMovie(movie);
                ticketOrder.setUnitPrice(movie.getPrice());
                ticketOrder.setQuantity(quantity);
                ticketOrder.setCustomer(customer);
                if (ValidationUtils.isNotEmpty(ticketOrderId)) {
                    ticketOrder.setId(Integer.parseInt(ticketOrderId));
                    ticketOrderService.updateTicketOrder(ticketOrder);
                    
                    resp.sendRedirect(req.getContextPath() + "/order/list");
                    return;
                }
                if (customer != null) {
                    customer.setUsedFreeTicketAmount(customer.getUsedFreeTicketAmount() + freeTicket);
                    customerService.updateCustomer(customer);
                }
               
                ticketOrderService.addTicketOrder(ticketOrder);
                resp.sendRedirect(req.getContextPath() + "/order/list");
            } else {
                resp.sendRedirect(req.getContextPath() + "/order/view?error=quantity");
            }
        }
    }
}
