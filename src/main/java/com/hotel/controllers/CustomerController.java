package com.hotel.controllers;

import com.hotel.model.Customer;
// Import CustomerDAO and Impl

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    @FXML private TextField customerIdField;
    @FXML private TextField nameField;
    @FXML private TextField contactField;
    @FXML private Label statusMessageLabel;
    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> colCustomerId;
    @FXML private TableColumn<Customer, String> colName;
    @FXML private TableColumn<Customer, String> colContact;

    // private CustomerDAO customerDAO;
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public void initialize() {
        // customerDAO = new CustomerDAOImpl();
        statusMessageLabel.setText("");

        // Setup TableView columns
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactDetails")); // Matches Customer field name

        customersTableView.setItems(customerList);

        // Add listener to TableView selection
        customersTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> showCustomerDetails(newSelection));

        // Load initial data
        loadCustomers();
        System.out.println("CustomerController initialized.");
    }

    private void loadCustomers() {
        customerList.clear();
        statusMessageLabel.setText("");
        // TODO: Load customers from DB using customerDAO
         System.out.println("Placeholder: Load customers here");
        // Add placeholder data
        // customerList.add(new Customer(1, "Alice Wonderland", "alice@example.com"));
        // customerList.add(new Customer(2, "Bob The Builder", "bob@example.com"));
         /*
        try {
             if (customerDAO == null) { setStatusMessage("Error: Customer service not available.", false); return; }
            List<Customer> customers = customerDAO.findAll();
            customerList.setAll(customers);
        } catch (SQLException e) {
            setStatusMessage("Error loading customers: " + e.getMessage(), false);
            e.printStackTrace();
        }
        */
    }

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            customerIdField.setText(String.valueOf(customer.getCustomerId()));
            nameField.setText(customer.getName());
            contactField.setText(customer.getContactDetails());
        } else {
            handleClearFields();
        }
    }

    @FXML
    private void handleAddCustomer() {
        if (!validateInput()) return;

        String name = nameField.getText();
        String contact = contactField.getText();

        Customer newCustomer = new Customer(0, name, contact); // ID is auto-generated by DB
        System.out.println("Attempting to add customer: " + newCustomer);

        // TODO: Implement DAO logic to add customer
        setStatusMessage("Placeholder: Add customer logic here", true);
        /*
         if (customerDAO == null) { setStatusMessage("Error: Customer service not available.", false); return; }
        try {
            boolean success = customerDAO.addCustomer(newCustomer);
            if (success) {
                loadCustomers(); // Refresh list
                handleClearFields();
                setStatusMessage("Customer added successfully!", true);
            } else {
                setStatusMessage("Failed to add customer.", false);
            }
        } catch (SQLException e) {
            setStatusMessage("Database error adding customer: " + e.getMessage(), false);
            e.printStackTrace();
        }
        */
    }

    @FXML
    private void handleUpdateCustomer() {
        Customer selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a customer to update.");
            return;
        }
        if (!validateInput()) return;

        String name = nameField.getText();
        String contact = contactField.getText();

        // Create updated customer object (use existing ID)
        Customer updatedCustomer = new Customer(selectedCustomer.getCustomerId(), name, contact);
        System.out.println("Attempting to update customer: " + updatedCustomer);

        // TODO: Implement DAO logic to update customer
         setStatusMessage("Placeholder: Update customer logic here", true);
        /*
        if (customerDAO == null) { setStatusMessage("Error: Customer service not available.", false); return; }
        try {
            boolean success = customerDAO.updateCustomer(updatedCustomer);
            if (success) {
                loadCustomers(); // Refresh list
                setStatusMessage("Customer updated successfully!", true);
            } else {
                setStatusMessage("Failed to update customer.", false);
            }
        } catch (SQLException e) {
            setStatusMessage("Database error updating customer: " + e.getMessage(), false);
            e.printStackTrace();
        }
        */
    }

    @FXML
    private void handleDeleteCustomer() {
        Customer selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a customer to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Customer: " + selectedCustomer.getName());
        alert.setContentText("Are you sure you want to delete this customer? This might fail if they have existing bookings.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Attempting to delete customer: " + selectedCustomer.getCustomerId());
             // TODO: Implement DAO logic to delete customer
             setStatusMessage("Placeholder: Delete customer logic here", true);
            /*
             if (customerDAO == null) { setStatusMessage("Error: Customer service not available.", false); return; }
            try {
                boolean success = customerDAO.deleteCustomer(selectedCustomer.getCustomerId());
                if (success) {
                    loadCustomers(); // Refresh list
                    handleClearFields();
                    setStatusMessage("Customer deleted successfully!", true);
                } else {
                    setStatusMessage("Failed to delete customer (perhaps they have bookings?).", false);
                }
            } catch (SQLException e) {
                 setStatusMessage("Database error deleting customer: " + e.getMessage(), false);
                 // Check for foreign key constraint violation
                 if (e.getSQLState().startsWith("23")) { // Integrity constraint violation
                      showAlert(Alert.AlertType.ERROR, "Deletion Failed", "Cannot delete customer. They may have existing bookings.");
                 } else {
                      showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting customer: " + e.getMessage());
                 }
                e.printStackTrace();
            }
            */
        }
    }

    @FXML
    private void handleClearFields() {
        customersTableView.getSelectionModel().clearSelection();
        customerIdField.clear();
        nameField.clear();
        contactField.clear();
        statusMessageLabel.setText("");
        nameField.requestFocus();
    }

    private boolean validateInput() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            errorMessage += "Name is required.\n";
        }
        // Contact details might be optional depending on requirements
        if (contactField.getText() == null || contactField.getText().trim().isEmpty()) {
           // errorMessage += "Contact Details are required.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", errorMessage);
            return false;
        }
    }

    private void setStatusMessage(String message, boolean success) {
         statusMessageLabel.setText(message);
         statusMessageLabel.setStyle(success ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
     }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 