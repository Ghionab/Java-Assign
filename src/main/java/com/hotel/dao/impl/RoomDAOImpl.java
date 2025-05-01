package com.hotel.dao.impl;

import com.hotel.dao.RoomDAO;
import com.hotel.model.Room;
import com.hotel.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public Optional<Room> findById(int roomNumber) throws SQLException {
        String sql = "SELECT room_id, room_number, type, price, status, floor FROM Rooms WHERE room_number = ?";
        Optional<Room> room = Optional.empty();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(roomNumber));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Room foundRoom = mapRowToRoom(rs);
                room = Optional.of(foundRoom);
            }
        }
        return room;
    }

    @Override
    public List<Room> findAll() throws SQLException {
        String sql = "SELECT room_id, room_number, type, price, status, floor FROM Rooms ORDER BY room_number";
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(mapRowToRoom(rs));
            }
        }
        return rooms;
    }

    public boolean updateRoomStatus(int roomNumber, String newStatus) throws SQLException {
        String sql = "UPDATE Rooms SET status = ? WHERE room_number = ?";
        int affectedRows = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, roomNumber);

            affectedRows = pstmt.executeUpdate();
        }
        return affectedRows > 0;
    }

    @Override
    public boolean addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO Rooms (room_number, type, price, status, floor) VALUES (?, ?, ?, ?, ?)";
        int affectedRows = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(room.getRoomNumber()));
            pstmt.setString(2, room.getType());
            pstmt.setDouble(3, room.getPrice());
            pstmt.setString(4, room.getStatus());
            pstmt.setInt(5, room.getFloor());

            affectedRows = pstmt.executeUpdate();
        }
        return affectedRows > 0;
    }

    @Override
    public boolean updateRoom(Room room) throws SQLException {
        String sql = "UPDATE Rooms SET type = ?, price = ?, status = ?, floor = ? WHERE room_number = ?";
        int affectedRows = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, room.getType());
            pstmt.setDouble(2, room.getPrice());
            pstmt.setString(3, room.getStatus());
            pstmt.setInt(4, room.getFloor());
            pstmt.setString(5, String.valueOf(room.getRoomNumber()));

            affectedRows = pstmt.executeUpdate();
        }
        return affectedRows > 0;
    }

    @Override
    public boolean deleteRoom(int roomNumber) throws SQLException {
        String sql = "DELETE FROM Rooms WHERE room_number = ?";
        int affectedRows = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(roomNumber));

            affectedRows = pstmt.executeUpdate();
        }
        return affectedRows > 0;
    }

    @Override
    public List<Room> findAvailableRooms() throws SQLException {
        String sql = "SELECT room_id, room_number, type, price, status, floor FROM Rooms WHERE status = ?";
        List<Room> rooms = new ArrayList<>();
        String availableStatus = "Available";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, availableStatus);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rooms.add(mapRowToRoom(rs));
            }
        }
        return rooms;
    }

    @Override
    public int getTotalRoomCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Rooms";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public int getAvailableRoomCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Rooms WHERE status = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Available");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public int getOccupiedRoomCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Rooms WHERE status = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Booked");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public int getCheckedInGuestsCount() throws SQLException {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int getExpectedCheckInsToday() throws SQLException {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int getExpectedCheckOutsToday() throws SQLException {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int getNewBookingsToday() throws SQLException {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public double getRevenueToday() throws SQLException {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    private Room mapRowToRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setRoomNumber(Integer.parseInt(rs.getString("room_number")));
        room.setType(rs.getString("type"));
        room.setPrice(rs.getDouble("price"));
        room.setStatus(rs.getString("status"));
        room.setFloor(rs.getInt("floor"));
        return room;
    }
} 