package com.hotel.controllers;

import com.hotel.MainApp;
import com.hotel.dao.UserDAO; // Assuming you'll create an implementation like UserDAOImpl
import com.hotel.dao.impl.UserDAOImpl;
import com.hotel.model.User;
import com.hotel.util.DatabaseConnection; // Needed if DAO impl requires connection passed

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label statusLabel;

    private UserDAO userDAO; // Use the interface

    public void initialize() {
        // Instantiate the DAO implementation
        // In a real app, use dependency injection (e.g., Spring) or a factory
        // userDAO = new UserDAOImpl(); // Example: Assumes UserDAOImpl exists
        statusLabel.setText(""); // Clear status on init

        System.out.println("LoginController initialized."); // Debug message
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        statusLabel.setText(""); // Clear previous messages

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Username and password cannot be empty.");
            return;
        }

        System.out.println("Attempting login for user: " + username); // Debug message

        // --- Placeholder Logic - Replace with DAO Call ---
        // Commenting out placeholder logic
        /*
        if ("admin".equals(username) && "password".equals(password)) {
             loginSuccess("admin");
        } else if ("reception".equals(username) && "password".equals(password)) {
            loginSuccess("receptionist");
        } else {
            statusLabel.setText("Invalid username or password.");
            System.out.println("Login failed for user: " + username);
        }
        */
        // --- End Placeholder Logic ---

        // --- DAO Logic (Now active) ---
        try {
            if (userDAO == null) {
                userDAO = new UserDAOImpl(); // Initialize if null
            }
            Optional<User> userOptional = userDAO.findByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // IMPORTANT: Use password hashing and verification in a real app!
                // Example: if (PasswordUtil.verifyPassword(password, user.getPassword())) {
                if (user.getPassword().equals(password)) { // Simple comparison (INSECURE)
                    System.out.println("Login successful for user: " + username + " with role: " + user.getRole());
                    loginSuccess(user.getRole());
                } else {
                    statusLabel.setText("Invalid username or password.");
                     System.out.println("Incorrect password for user: " + username);
                }
            } else {
                statusLabel.setText("Invalid username or password.");
                System.out.println("User not found: " + username);
            }

        } catch (SQLException e) {
            statusLabel.setText("Database error during login.");
            System.err.println("SQL Error during login: " + e.getMessage());
            e.printStackTrace(); // Log the full error
        } catch (Exception e) {
            statusLabel.setText("An unexpected error occurred.");
            System.err.println("Unexpected Error during login: " + e.getMessage());
            e.printStackTrace(); // Log the full error
        }
        // --- End DAO Logic ---
    }

    private void loginSuccess(String userRole) {
        try {
            // Switch to the main application window
            MainApp.showMainInterface(userRole);
        } catch (IOException e) {
            statusLabel.setText("Error loading main application window.");
            System.err.println("IOException when loading MainInterface: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 