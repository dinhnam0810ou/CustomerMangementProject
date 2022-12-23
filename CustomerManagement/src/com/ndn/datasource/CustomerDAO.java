package com.ndn.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

import com.ndn.enums.MembershipLevel;
import com.ndn.model.Customer;
import com.ndn.model.PaginatedResult;
import com.ndn.utils.ViewUtils;
import com.ndn.utils.CreateUnitUtils;
import com.ndn.utils.ValidationUtils;

public class CustomerDAO {
    
    public void deleteCustomerById(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("delete from customer where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);
        }
    }
    
    public Customer getCustomerById(int id) {   
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("select * from customer where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Customer customer = CreateUnitUtils.createCustomerFromResultSet(rs);
            return customer;
            
        } catch (SQLException e) {
            return null;
        } finally {
            closeQuietly(preparedStatement);
        }
    }
    public void addCustomer(Customer customer) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            
            connection.setAutoCommit(false);    
            preparedStatement = connection.prepareStatement(
                                    "insert into customer(name, gender, phone_number, address, email, point) values (?, ?, ? , ? ,? , ?)");
            
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getGender().toString());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getPoint());
            
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
    
    public void updateCustomer(Customer customer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().
                    prepareStatement("update customer set name = ?, gender = ?, phone_number = ?,"
                            + "address = ?, email = ?, point = ?, useticketfree = ? where id = ?");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getGender().toString());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getPoint());
            preparedStatement.setInt(7, customer.getUsedFreeTicketAmount());
            preparedStatement.setInt(8, customer.getId());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);
        }
    }
    public PaginatedResult<Customer> getCustomers(String name, String gender, String phoneNumber, String membershipLevel, int pageIndex) {
        PaginatedResult<Customer> paginatedResult = new PaginatedResult<>();
        int count = 0;
        int offset = ViewUtils.calculateOffset(pageIndex);
        String selectSqlString = "select * from customer";        
        String countSqlString = "select count(*) from customer"; 
        String subSql = "";
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatementSelect = null;
        PreparedStatement preparedStatementCount = null; 
        Connection connection = null;
        
        List<String> conditions = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        if (ValidationUtils.isNotEmpty(membershipLevel)) {
            if (membershipLevel.equals(MembershipLevel.Silver.toString())) {
                conditions.add("point < ?");
                values.add(MembershipLevel.Gold.getPoint());
            }
            if (membershipLevel.equals(MembershipLevel.Gold.toString())) {
                conditions.add("point >= ? and point < ?");
                values.add(MembershipLevel.Gold.getPoint());
                values.add(MembershipLevel.Platinum.getPoint());
            }
            if (membershipLevel.equals(MembershipLevel.Platinum.toString())) {
                conditions.add("point >= ?");
                values.add(MembershipLevel.Platinum.getPoint());
            }
        }
        if (ValidationUtils.isNotEmpty(name)) {
            conditions.add("name ilike ?");
            values.add("%" + name + "%");
        }
        if (ValidationUtils.isNotEmpty(gender)) {
            conditions.add("gender = ?");
            values.add(gender);
        }
        if (ValidationUtils.isNotEmpty(phoneNumber)) {
            conditions.add("phone_number ilike ?");
            values.add("%" + phoneNumber + "%");
        }
        
        connection = DatabaseConnection.getConnection();
        try {
            if (!conditions.isEmpty()) {
                subSql += " where " + StringUtils.join(conditions, " and ");
            }
            selectSqlString += subSql + " offset ? limit ?";
            countSqlString += subSql;
            preparedStatementSelect = connection.prepareStatement(selectSqlString);
            preparedStatementCount = connection.prepareStatement(countSqlString);
            int index = 0;
            if (!values.isEmpty()) {
                for (Object value : values) {
                    ++ index;
                    preparedStatementSelect.setObject(index, value);
                    preparedStatementCount.setObject(index, value);
                }
            }
            
            preparedStatementSelect.setInt(++ index, offset);
            preparedStatementSelect.setInt(++ index, ViewUtils.PAGE_SIZE);
            ResultSet selectResultSet = preparedStatementSelect.executeQuery();
            while(selectResultSet.next()) {
                Customer customer = CreateUnitUtils.createCustomerFromResultSet(selectResultSet);
                customers.add(customer);
            }
            ResultSet countResultSet = preparedStatementCount.executeQuery();
            countResultSet.next();
            count = countResultSet.getInt(1);
            
            paginatedResult.setItems(customers);
            paginatedResult.setCount(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatementSelect);
            closeQuietly(preparedStatementCount);
        }
        return paginatedResult;
    }
    public List<Customer> getCustomers() {
        PreparedStatement preparedStatement = null;
        List<Customer> customers = new ArrayList<>();
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("select * from customer order by name");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = CreateUnitUtils.createCustomerFromResultSet(rs);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            return null;
        } finally {
            closeQuietly(preparedStatement);
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
