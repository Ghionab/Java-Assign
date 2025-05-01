package com.hotel.controllers;

import com.hotel.model.Booking;
import com.hotel.model.Room;
import com.hotel.model.Customer;
// Import DAOs and Impls

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookingController {

    @FXML private TextField bookingIdField;
    @FXML private ComboBox<Room> roomNumberComboBox; // Populate with available rooms
    @FXML private ComboBox<Customer> customerComboBox; // Populate with existing customers
    @FXML private DatePicker checkInDatePicker;
    @FXML private DatePicker checkOutDatePicker;
    @FXML private Label statusMessageLabel;
    @FXML private TableView<Booking> bookingsTableView;
    @FXML private TableColumn<Booking, Integer> colBookingId;
    @FXML private TableColumn<Booking, Integer> colRoomNumber;
    @FXML private TableColumn<Booking, Integer> colCustomerId; // Consider showing name
    @FXML private TableColumn<Booking, LocalDate> colCheckIn;
    @FXML private TableColumn<Booking, LocalDate> colCheckOut;

    // DAOs needed: BookingDAO, RoomDAO, CustomerDAO
    // private BookingDAO bookingDAO;
    // private RoomDAO roomDAO;
    // private CustomerDAO customerDAO;

    private ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    private ObservableList<Room> availableRoomList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public void initialize() {
        // Instantiate DAOs
        // bookingDAO = new BookingDAOImpl();
        // roomDAO = new RoomDAOImpl();
        // customerDAO = new CustomerDAOImpl();

        statusMessageLabel.setText("");

        // Setup TableView columns
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        bookingsTableView.setItems(bookingList);

        // Add listener to TableView selection
        bookingsTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> showBookingDetails(newSelection));

        // Setup ComboBoxes
        roomNumberComboBox.setItems(availableRoomList);
        customerComboBox.setItems(customerList);
        // Set converters to display Room/Customer info correctly in ComboBox
        roomNumberComboBox.setConverter(new RoomStringConverter());
        customerComboBox.setConverter(new CustomerStringConverter());

        // Load initial data
        loadInitialData();
        System.out.println("BookingController initialized.");
    }

    private void loadInitialData() {
        loadBookings();
        loadAvailableRooms();
        loadCustomers();
    }

    private void loadBookings() {
        bookingList.clear();
        statusMessageLabel.setText("");
        // TODO: Load bookings from DB using bookingDAO
         System.out.println("Placeholder: Load bookings here");
        // Add placeholder data if needed for testing UI
         // bookingList.add(new Booking(1, 101, 1, LocalDate.now(), LocalDate.now().plusDays(3)));
        /*
        try {
             if (bookingDAO == null) { setStatusMessage("Error: Booking service not available.", false); return; }
            List<Booking> bookings = bookingDAO.findAll();
            bookingList.setAll(bookings);
        } catch (SQLException e) {
            setStatusMessage("Error loading bookings: " + e.getMessage(), false);
            e.printStackTrace();
        }
        */
    }

    private void loadAvailableRooms() {
        availableRoomList.clear();
         // TODO: Load *available* rooms from DB using roomDAO
         System.out.println("Placeholder: Load available rooms here");
         // Add placeholder data if needed
         // availableRoomList.addAll(new Room(101, "Single", "available"), new Room(301, "Suite", "available"));
        /*
        try {
            if (roomDAO == null) return; // Or show error
            // Ideally, DAO method should find rooms marked 'available' or not currently booked for selected dates
            List<Room> rooms = roomDAO.findAvailableRooms(); // Or just findAll() for simplicity initially
            availableRoomList.setAll(rooms);
        } catch (SQLException e) {
             setStatusMessage("Error loading rooms: " + e.getMessage(), false);
            e.printStackTrace();
        }
        */
    }

    private void loadCustomers() {
        customerList.clear();
         // TODO: Load customers from DB using customerDAO
          System.out.println("Placeholder: Load customers here");
         // Add placeholder data if needed
         // customerList.addAll(new Customer(1, "Alice", "alice@ex.com"), new Customer(2, "Bob", "bob@ex.com"));
        /*
        try {
            if (customerDAO == null) return; // Or show error
            List<Customer> customers = customerDAO.findAll();
            customerList.setAll(customers);
        } catch (SQLException e) {
            setStatusMessage("Error loading customers: " + e.getMessage(), false);
            e.printStackTrace();
        }
        */
    }

    private void showBookingDetails(Booking booking) {
        if (booking != null) {
            bookingIdField.setText(String.valueOf(booking.getBookingId()));
            checkInDatePicker.setValue(booking.getCheckInDate());
            checkOutDatePicker.setValue(booking.getCheckOutDate());

            // Select room in ComboBox (find matching room object)
            Room selectedRoom = availableRoomList.stream()
                                    .filter(r -> r.getRoomNumber() == booking.getRoomNumber())
                                    .findFirst().orElse(null);
            // If the booked room wasn't in the 'available' list initially, add it temporarily or handle differently
            if(selectedRoom == null) {
                // Fetch the specific room? Or add a placeholder? Handle this case.
                 System.out.println("Booked room "+ booking.getRoomNumber() +" not in available list. Add handling.");
            }
            roomNumberComboBox.setValue(selectedRoom);

            // Select customer in ComboBox
            Customer selectedCustomer = customerList.stream()
                                          .filter(c -> c.getCustomerId() == booking.getCustomerId())
                                          .findFirst().orElse(null);
            customerComboBox.setValue(selectedCustomer);

        } else {
            handleClearFields();
        }
    }

    @FXML
    private void handleAddBooking() {
         if (!validateInput()) return;

         Room selectedRoom = roomNumberComboBox.getValue();
         Customer selectedCustomer = customerComboBox.getValue();
         LocalDate checkIn = checkInDatePicker.getValue();
         LocalDate checkOut = checkOutDatePicker.getValue();

         Booking newBooking = new Booking(0, selectedRoom.getRoomNumber(), selectedCustomer.getCustomerId(), checkIn, checkOut);
         System.out.println("Attempting to add booking: " + newBooking);

         // TODO: Implement DAO logic to add booking and handle potential conflicts (e.g., room already booked)
         // After adding, update the room status via RoomDAO if necessary
         setStatusMessage("Placeholder: Add booking logic here", true);

         /*
         if (bookingDAO == null) { setStatusMessage("Error: Booking service not available.", false); return; }
         try {
            // Optional: Check if room is available for the dates before adding
            boolean success = bookingDAO.addBooking(newBooking);
            if (success) {
                // Optional: Update Room status to 'occupied' via RoomDAO
                // roomDAO.updateRoomStatus(selectedRoom.getRoomNumber(), "occupied");
                loadInitialData(); // Reload lists
                handleClearFields();
                setStatusMessage("Booking added successfully!", true);
            } else {
                 setStatusMessage("Failed to add booking.", false);
            }
         } catch (SQLException e) {
            setStatusMessage("Database error adding booking: " + e.getMessage(), false);
            e.printStackTrace();
         }
        */
    }

    @FXML
    private void handleUpdateBooking() {
        Booking selectedBooking = bookingsTableView.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a booking to update.");
            return;
        }
         if (!validateInput()) return;

         Room selectedRoom = roomNumberComboBox.getValue();
         Customer selectedCustomer = customerComboBox.getValue();
         LocalDate checkIn = checkInDatePicker.getValue();
         LocalDate checkOut = checkOutDatePicker.getValue();

         // Create updated booking object (use existing ID)
         Booking updatedBooking = new Booking(selectedBooking.getBookingId(), selectedRoom.getRoomNumber(), selectedCustomer.getCustomerId(), checkIn, checkOut);
         System.out.println("Attempting to update booking: " + updatedBooking);

         // TODO: Implement DAO logic to update booking
         setStatusMessage("Placeholder: Update booking logic here", true);
         /*
          if (bookingDAO == null) { setStatusMessage("Error: Booking service not available.", false); return; }
         try {
            boolean success = bookingDAO.updateBooking(updatedBooking);
            if (success) {
                loadInitialData(); // Reload lists
                 setStatusMessage("Booking updated successfully!", true);
            } else {
                 setStatusMessage("Failed to update booking.", false);
            }
         } catch (SQLException e) {
             setStatusMessage("Database error updating booking: " + e.getMessage(), false);
            e.printStackTrace();
         }
        */
    }

    @FXML
    private void handleDeleteBooking() {
         Booking selectedBooking = bookingsTableView.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a booking to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Booking ID: " + selectedBooking.getBookingId());
        alert.setContentText("Are you sure you want to delete this booking?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Attempting to delete booking: " + selectedBooking.getBookingId());
             // TODO: Implement DAO logic to delete booking
             // After deleting, consider updating the room status back to 'available' if appropriate
              setStatusMessage("Placeholder: Delete booking logic here", true);
            /*
            if (bookingDAO == null) { setStatusMessage("Error: Booking service not available.", false); return; }
            try {
                boolean success = bookingDAO.deleteBooking(selectedBooking.getBookingId());
                if (success) {
                     // Optional: Update Room status back to 'available' if no other bookings exist for it
                     // roomDAO.updateRoomStatus(selectedBooking.getRoomNumber(), "available");
                    loadInitialData(); // Reload lists
                    handleClearFields();
                    setStatusMessage("Booking deleted successfully!", true);
                } else {
                    setStatusMessage("Failed to delete booking.", false);
                }
            } catch (SQLException e) {
                setStatusMessage("Database error deleting booking: " + e.getMessage(), false);
                e.printStackTrace();
            }
            */
        }
    }

    @FXML
    private void handleClearFields() {
        bookingsTableView.getSelectionModel().clearSelection();
        bookingIdField.clear();
        roomNumberComboBox.getSelectionModel().clearSelection();
        customerComboBox.getSelectionModel().clearSelection();
        checkInDatePicker.setValue(null);
        checkOutDatePicker.setValue(null);
        statusMessageLabel.setText("");
    }

     private boolean validateInput() {
        String errorMessage = "";
        if (roomNumberComboBox.getValue() == null) {
            errorMessage += "Room must be selected.\n";
        }
        if (customerComboBox.getValue() == null) {
            errorMessage += "Customer must be selected.\n";
        }
        if (checkInDatePicker.getValue() == null) {
            errorMessage += "Check-in date is required.\n";
        }
        if (checkOutDatePicker.getValue() == null) {
            errorMessage += "Check-out date is required.\n";
        }
        if (checkInDatePicker.getValue() != null && checkOutDatePicker.getValue() != null) {
            if (checkOutDatePicker.getValue().isBefore(checkInDatePicker.getValue()) || checkOutDatePicker.getValue().isEqual(checkInDatePicker.getValue())) {
                errorMessage += "Check-out date must be after check-in date.\n";
            }
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

    // --- Helper Classes for ComboBox Display ---
    private static class RoomStringConverter extends javafx.util.StringConverter<Room> {
        @Override
        public String toString(Room room) {
            return (room == null) ? null : "Room " + room.getRoomNumber() + " (" + room.getType() + ")";
        }

        @Override
        public Room fromString(String string) {
            // Not needed if ComboBox is not editable
            return null;
        }
    }

    private static class CustomerStringConverter extends javafx.util.StringConverter<Customer> {
        @Override
        public String toString(Customer customer) {
            return (customer == null) ? null : customer.getName() + " (ID: " + customer.getCustomerId() + ")";
        }

        @Override
        public Customer fromString(String string) {
             // Not needed if ComboBox is not editable
            return null;
        }
    }
} 