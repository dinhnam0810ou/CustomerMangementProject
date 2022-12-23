package com.ndn.service;

import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;

public interface ITicketOrderService {
    public TicketOrder getTicketOrderById(int id);
    public void addTicketOrder(TicketOrder ticketOrder);
    public void updateTicketOrder(TicketOrder ticketOrder);
    public void deleteTicketOrder(int id);
    public PaginatedResult<TicketOrder> getTicketOrders(int pageIndex);
}
