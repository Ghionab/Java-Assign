package com.hotel.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
// Import other necessary classes

public class MainController {

    @FXML private TabPane mainTabPane;

    // Inject controllers for each tab if needed for communication
    // @FXML private RoomController roomTabController; // Make sure fx:id matches in FXML
    // @FXML private BookingController bookingTabController;
    // @FXML private CustomerController customerTabController;
    // @FXML private ReportController reportTabController;

    private String currentUserRole;

    // Called after the FXML file has been loaded
    public void initialize() {
        // Perform initial setup for the main interface if needed
        System.out.println("MainController initialized.");

        // Example: Load initial data into tabs
        // roomTabController.loadRooms(); // If you have such methods
        // bookingTabController.loadBookings();
        // customerTabController.loadCustomers();

        // TODO: Add listener to tab changes if needed
        // mainTabPane.getSelectionModel().selectedItemProperty().addListener(...);
    }

    // Method to be called by MainApp to pass the user role
    public void setUserRole(String role) {
        this.currentUserRole = role;
        System.out.println("User role set to: " + role);
        // Example: Customize UI based on role
        // if (!"admin".equals(role)) {
        //     // Disable or hide certain tabs or features
        //     Tab userManagementTab = findTabById("userManagementTab"); // Assuming fx:id="userManagementTab"
        //     if (userManagementTab != null) {
        //         userManagementTab.setDisable(true);
        //     }
        // }
    }

    // Helper method to find a tab by its fx:id (if you set them)
    /*
    private Tab findTabById(String fxId) {
        for (Tab tab : mainTabPane.getTabs()) {
            if (fxId.equals(tab.getId())) {
                return tab;
            }
        }
        return null;
    }
    */

    // Add any other methods needed for the main interface logic
} 