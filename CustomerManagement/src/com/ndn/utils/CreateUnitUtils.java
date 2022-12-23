package com.ndn.utils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.ndn.enums.Gender;
import com.ndn.model.Customer;
import com.ndn.model.Movie;
import com.ndn.model.TicketOrder;

public class CreateUnitUtils {
    public static Movie createMovieFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        Movie movie = new Movie();
        movie.setId(id);
        movie.setName(name);
        movie.setPrice(price);
        return movie;
    }
    
    public static Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Gender gender = Gender.valueOf(resultSet.getString("gender"));
        String phoneNumber = resultSet.getString("phone_number");
        String address = resultSet.getString("address");
        String email = resultSet.getString("email");
        int point = resultSet.getInt("point");
        int useFreeTicket = resultSet.getInt("useticketfree");
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName(name);
        customer.setGender(gender);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPoint(point);
        customer.setUsedFreeTicketAmount(useFreeTicket);
        return customer;
    }
    
    public static TicketOrder createTicketOrderFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Date orderDate = resultSet.getDate("orderdate");
        int quantity = resultSet.getInt("quantity");
        double unitPrice = resultSet.getDouble("unitprice");
        String customerName = resultSet.getString("customername");
        String movieName = resultSet.getString("moviename");
        
        Movie movie = new Movie();
        movie.setName(movieName);;
        Customer customer = null;
        if (ValidationUtils.isNotEmpty(customerName)) {
            customer = new Customer();
            customer.setName(customerName);
        }            
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setId(id);
        ticketOrder.setOrderDate(orderDate);
        ticketOrder.setQuantity(quantity);
        ticketOrder.setUnitPrice(unitPrice);
        ticketOrder.setMovie(movie);
        ticketOrder.setCustomer(customer);
        return ticketOrder;
    }
}
