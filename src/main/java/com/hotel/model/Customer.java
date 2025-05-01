package com.hotel.model;

public class Customer {
    private int customerId;
    private String name;
    private String contactDetails;
    // Add first_name, last_name if preferred over single 'name'

    // Constructors
    public Customer() {}

    public Customer(int customerId, String name, String contactDetails) {
        this.customerId = customerId;
        this.name = name;
        this.contactDetails = contactDetails;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return name + " (ID: " + customerId + ")";
    }
} 