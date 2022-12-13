package com.ndn.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.ndn.enums.Gender;
import com.ndn.model.Customer;





public class CustomerDAO {
    public static final int PAGE_SIZE = 4;
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
            
            int customer_id = rs.getInt("id");
            String name = rs.getString("name");
            Gender gender = Gender.valueOf(rs.getString("gender"));
            String phone_number = rs.getString("phone_number");
            String address = rs.getString("address");
            String email = rs.getString("email");
            int point = rs.getInt("point");
            
            Customer customer = new Customer();
            customer.setId(customer_id);
            customer.setName(name);
            customer.setGender(gender);
            customer.setPhoneNumber(phone_number);
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setPoint(point);
            
            return customer;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);

        }
    }
    
    public void updateCustomer(Customer customer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().
                    prepareStatement("update customer set name = ?, gender = ?, phone_number = ?,"
                            + "address = ?, email = ?, point = ? where id = ?");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getGender().toString());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getPoint());
            preparedStatement.setInt(7, customer.getId());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);

        }
    }
    
    public List<Customer> getCustomers(int page) {
        int offset = (page - 1) * PAGE_SIZE;
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("select * from customer order by id limit ? offset ?");
            preparedStatement.setInt(1, PAGE_SIZE);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();      
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Gender gender = Gender.valueOf(rs.getString("gender"));
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int point = rs.getInt("point");
                
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setGender(gender);
                customer.setPhoneNumber(phone_number);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setPoint(point);
                
                customers.add(customer);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);

        }
        return customers;
    }
    
    public List<Customer> searchCustomers(String name, String gender, String phone_number, String membership_level) {
        int index = 0;
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        if(name.isEmpty() && gender.isEmpty() && phone_number.isEmpty() && membership_level.isEmpty()) {
            return getCustomers(1);
        }
        try {
            String sql ="select * from customer where ";
            String subSql = "";
            if (name != null && !name.isEmpty()) {
                if (!subSql.isEmpty()) subSql += "and ";
                subSql += "name like ? "; 
            }
            if (gender != null && !gender.isEmpty()) {
                if (!subSql.isEmpty()) subSql += "and ";
                subSql += "gender like ? ";
            }
            if (phone_number != null && !phone_number.isEmpty()) {
                if (!subSql.isEmpty()) subSql += "and ";
                subSql += "phone_number like ? ";
            }
            if (membership_level != null && !membership_level.isEmpty()) {
                if (!subSql.isEmpty()) subSql += "and ";
                if (membership_level.equals("Silver")) subSql += "point < 100 ";
                if (membership_level.equals("Gold")) subSql += "point >= 100 and point <250 ";
                if (membership_level.equals("Platinum")) subSql += "point >= 250 ";
            }
           
            sql += subSql + " order by id limit ? offset ?";
            System.out.println(sql);
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql);
            if(name != null && !name.isEmpty()) {
                index ++;
                preparedStatement.setString(index, "%"+name+"%");
            }
            if(gender != null && !gender.isEmpty()) {
                index ++;
                preparedStatement.setString(index, "%"+gender+"%");
            }
            if(phone_number != null && !phone_number.isEmpty()) {
                index ++;
                preparedStatement.setString(index, "%"+phone_number+"%");
            }

            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int customer_id = rs.getInt("id");
                String customer_name = rs.getString("name");
                String customer_gender = rs.getString("gender");   
                String customer_phone_number = rs.getString("phone_number");
                String customer_address = rs.getString("address");
                String customer_email = rs.getString("email");
                int customer_point = rs.getInt("point");
                
                Customer customer = new Customer();
                customer.setId(customer_id);
                customer.setName(customer_name);
                customer.setGender(Gender.valueOf(customer_gender));
                customer.setPhoneNumber(customer_phone_number);
                customer.setAddress(customer_address);
                customer.setEmail(customer_email);
                customer.setPoint(customer_point);
                
                customers.add(customer);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(preparedStatement);
        }
        return customers;
    }
    
    public int countCustomer() {
        int count = 0;
        Statement statement = null;
        String sql = "select count(*) from customer";
        try {
            statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            count =resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }      
        return count;
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
