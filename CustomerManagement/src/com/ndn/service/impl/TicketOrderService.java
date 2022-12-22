package com.ndn.service.impl;

import com.ndn.datasource.CustomerDAO;
import com.ndn.datasource.TicketOrderDAO;
import com.ndn.model.Customer;
import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;
import com.ndn.service.ITicketOrderService;

public class TicketOrderService implements ITicketOrderService{
    public static final int POINT_OF_TICKET = 1;
    TicketOrderDAO ticketOrderDAO = new TicketOrderDAO();
    CustomerDAO customerDAO = new CustomerDAO();
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
    
}
