package com.hotel.dao.impl;

import com.hotel.dao.CustomerDAO;
import com.hotel.model.Customer;
import com.hotel.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Optional<Customer> findById(int customerId) throws SQLException {
        // TODO: Implement DB logic
        System.out.println("CustomerDAOImpl: findById called (placeholder)");
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        // TODO: Implement DB logic
        System.out.println("CustomerDAOImpl: findAll called (placeholder)");
        return new ArrayList<>();
    }

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        // TODO: Implement DB logic (consider returning generated ID)
        System.out.println("CustomerDAOImpl: addCustomer called (placeholder)");
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        // TODO: Implement DB logic
        System.out.println("CustomerDAOImpl: updateCustomer called (placeholder)");
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerId) throws SQLException {
        // TODO: Implement DB logic
        System.out.println("CustomerDAOImpl: deleteCustomer called (placeholder)");
        return false;
    }

    @Override
    public List<Customer> findByName(String name) throws SQLException {
        // TODO: Implement DB logic (e.g., using LIKE for partial match)
        System.out.println("CustomerDAOImpl: findByName called (placeholder)");
        return new ArrayList<>();
    }
} 