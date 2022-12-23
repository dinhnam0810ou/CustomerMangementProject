package com.ndn.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.ndn.model.Customer;
import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;
import com.ndn.model.TicketOrder;
import com.ndn.utils.ViewUtils;
import com.ndn.utils.CreateUnitUtils;



public class TicketOrderDAO {
    public TicketOrder getTicketOrderById(int id) {   
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("select * from ticket_order where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int ticketOrderId = rs.getInt("id");
            Date orderDate = rs.getDate("orderdate");
            int quantity = rs.getInt("quantity");
            double unitPrice = rs.getDouble("unitprice");
            int customerId = rs.getInt("customerid");
            int movieId = rs.getInt("movieid");

            Movie movie = new Movie();
            movie.setId(movieId);
            Customer  customer = null;
            if (customerId != 0) {
                customer = new Customer();
                customer.setId(customerId);
            }
            TicketOrder ticketOrder = new TicketOrder();
            ticketOrder.setId(ticketOrderId);
            ticketOrder.setOrderDate(orderDate);
            ticketOrder.setQuantity(quantity);
            ticketOrder.setUnitPrice(unitPrice);
            ticketOrder.setMovie(movie);
            ticketOrder.setCustomer(customer);
            return ticketOrder;
            
        } catch (SQLException e) {
            return null;
        } finally {
            closeQuietly(preparedStatement);
        }
    }

    public void updateTicketOrder(TicketOrder ticketOrder) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);   
            preparedStatement = DatabaseConnection.getConnection().
                    prepareStatement("update ticket_order set orderdate = ?, quantity = ?, unitprice = ?,"
                            + " movieid = ? where id = ?");
            preparedStatement.setDate(1, new java.sql.Date(new Date().getTime()));
            preparedStatement.setInt(2, ticketOrder.getQuantity());
            preparedStatement.setDouble(3, ticketOrder.getUnitPrice());
            preparedStatement.setInt(4, ticketOrder.getMovie().getId());
            preparedStatement.setInt(5, ticketOrder.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteTicketOrderById(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("delete from ticket_order where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);
        }
    }
    public PaginatedResult<TicketOrder> getTicketOrders(int pageIndex) {
        PaginatedResult<TicketOrder> paginatedResult = new PaginatedResult<>();
        int count = 0;
        int offset = ViewUtils.calculateOffset(pageIndex);
         
        String selectSqlString = "select\n"
                + "    to2.*,\n"
                + "    m.\"name\" as moviename,\n"
                + "    c.\"name\" as customername\n"
                + "from\n"
                + "    ticket_order to2\n"
                + "left join customer c on\n"
                + "    to2.customerid = c.id\n"
                + "inner join movie m on\n"
                + "    to2 .movieid = m.id offset ? limit ?";        
        String countSqlString = "select count(*) from ticket_order"; 
        List<TicketOrder> ticketOrders = new ArrayList<>();
        PreparedStatement preparedStatementSelect = null;
        PreparedStatement preparedStatementCount = null; 
        Connection connection = null;
        connection = DatabaseConnection.getConnection();
        try {
            preparedStatementSelect = connection.prepareStatement(selectSqlString);
            preparedStatementCount = connection.prepareStatement(countSqlString);   
            preparedStatementSelect.setInt(1 , offset);
            preparedStatementSelect.setInt(2 , ViewUtils.PAGE_SIZE);
            ResultSet selectResultSet = preparedStatementSelect.executeQuery();
            while(selectResultSet.next()) {
                TicketOrder ticketOrder = CreateUnitUtils.createTicketOrderFromResultSet(selectResultSet);
                ticketOrders.add(ticketOrder);
            }
            ResultSet countResultSet = preparedStatementCount.executeQuery();
            countResultSet.next();
            count = countResultSet.getInt(1);
            
            paginatedResult.setItems(ticketOrders);
            paginatedResult.setCount(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatementSelect);
            closeQuietly(preparedStatementCount);
        }
        return paginatedResult;
    }
    public void addTicketOrder(TicketOrder ticketOrder) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);    
            preparedStatement = connection.prepareStatement(
                                    "insert into ticket_order(orderdate, quantity, unitprice, customerid, movieid) values (?, ? , ? ,? , ?)");
            preparedStatement.setDate(1, new java.sql.Date(new Date().getTime()));
            preparedStatement.setInt(2, ticketOrder.getQuantity());
            preparedStatement.setDouble(3, ticketOrder.getUnitPrice());
            Customer customer = ticketOrder.getCustomer();
            preparedStatement.setObject(4, customer == null ? null : customer.getId());
            preparedStatement.setInt(5, ticketOrder.getMovie().getId());     
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void closeQuietly(Statement statement) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    } 
}
