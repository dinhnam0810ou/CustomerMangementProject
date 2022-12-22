package com.ndn.service;

import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;

public interface ITicketOrderService {
    public void addTicketOrder(TicketOrder ticketOrder);
    public PaginatedResult<TicketOrder> getTicketOrders(int pageIndex);
}
