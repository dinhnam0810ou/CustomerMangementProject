package com.ndn.service.impl;

import com.ndn.datasource.CustomerDAO;
import com.ndn.datasource.MovieDAO;
import com.ndn.datasource.TicketOrderDAO;
import com.ndn.model.Customer;
import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;
import com.ndn.service.ITicketOrderService;

public class TicketOrderService implements ITicketOrderService {
    public static final int POINT_OF_TICKET = 1;
    TicketOrderDAO ticketOrderDAO = new TicketOrderDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    MovieDAO movieDAO = new MovieDAO();
    @Override
    public void addTicketOrder(TicketOrder ticketOrder) {
        ticketOrderDAO.addTicketOrder(ticketOrder);
        if (ticketOrder.getCustomer() != null) {
            Customer customer = customerDAO.getCustomerById(ticketOrder.getCustomer().getId());
            customer.setPoint(customer.getPoint() + (ticketOrder.getQuantity() * POINT_OF_TICKET));
            customerDAO.updateCustomer(customer);
        }
    }
    @Override
    public PaginatedResult<TicketOrder> getTicketOrders(int pageIndex) {
        return ticketOrderDAO.getTicketOrders(pageIndex);
    }
    @Override
    public void updateTicketOrder(TicketOrder ticketOrder) {
        if (ticketOrder.getCustomer() != null) {
            TicketOrder oldTicketOrder = ticketOrderDAO.getTicketOrderById(ticketOrder.getId());
            Customer customer = customerDAO.getCustomerById(ticketOrder.getCustomer().getId());
            customer.setPoint(customer.getPoint() - (oldTicketOrder.getQuantity() * POINT_OF_TICKET));
            customer.setPoint(customer.getPoint() + (ticketOrder.getQuantity() * POINT_OF_TICKET));
            customerDAO.updateCustomer(customer);
        }
        ticketOrderDAO.updateTicketOrder(ticketOrder);
    }
    @Override
    public void deleteTicketOrder(int id) {
        ticketOrderDAO.deleteTicketOrderById(id);
    }
    @Override
    public TicketOrder getTicketOrderById(int id) {
        TicketOrder ticketOrder = ticketOrderDAO.getTicketOrderById(id);
        Customer customer = null;
        if (ticketOrder.getCustomer() != null) {
             customer = customerDAO.getCustomerById(ticketOrder.getCustomer().getId());
        }
        Movie movie = movieDAO.getMovieById(ticketOrder.getMovie().getId());
        ticketOrder.setCustomer(customer);
        ticketOrder.setMovie(movie);
        return ticketOrder;
    }
    
}
