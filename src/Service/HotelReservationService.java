package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entity.Customer;
import Entity.Reservation;
import Entity.Room;
import Exception.RoomNotAvailableException;

public class HotelReservationService {
	 private List<Room> rooms;
	    private List<Customer> customers;
	    private List<Reservation> reservations;
	    private static final String ROOMS_FILE = "rooms.txt";
	    private static final String CUSTOMERS_FILE = "customers.txt";

	    public HotelReservationService() {
	        rooms = new ArrayList<>();
	        customers = new ArrayList<>();
	        reservations = new ArrayList<>();
	        loadRooms();
	        loadCustomers();
	    }

	    public void addRoom(Room room) {
	        rooms.add(room);
	        saveRooms();
	    }

	    public void viewAvailableRooms() {
	        System.out.println("Available Rooms:");
	        for (Room room : rooms) {
	            if (room.isAvailable()) {
	                System.out.println(room);
	            }
	        }
	    }

	    public void bookRoom(int roomId, int customerId, String checkInDate, String checkOutDate) throws RoomNotAvailableException {
	        Room room = findRoomById(roomId);
	        if (room == null || !room.isAvailable()) {
	            throw new RoomNotAvailableException("Room not available for booking.");
	        }

	        room.setAvailability(false);
	        reservations.add(new Reservation(reservations.size() + 1, roomId, customerId, checkInDate, checkOutDate));
	        saveRooms();
	    }

	    public void displayCurrentReservations() {
	        for (Reservation reservation : reservations) {
	            System.out.println(reservation);
	        }
	    }

	    public void generateInvoice(int reservationId) {
	        for (Reservation reservation : reservations) {
	            if (reservation.getReservationId() == reservationId) {
	                Room room = findRoomById(reservation.getRoomId());
	                double totalCost = reservation.calculateTotalCost(room);
	                System.out.println("Invoice for Reservation ID: " + reservationId);
	                System.out.println("Customer ID: " + reservation.getCustomerId());
	                System.out.println("Room ID: " + reservation.getRoomId());
	                System.out.println("Total Cost: $" + totalCost);
	                return;
	            }
	        }
	        System.out.println("Reservation not found.");
	    }

	    public void addCustomer(Customer customer) {
	        customers.add(customer);
	        saveCustomers();
	    }

	    public void loadRooms() {
	        try (BufferedReader br = new BufferedReader(new FileReader(ROOMS_FILE))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                int roomId = Integer.parseInt(data[0]);
	                String roomType = data[1];
	                double pricePerNight = Double.parseDouble(data[2]);
	                rooms.add(new Room(roomId, roomType, pricePerNight));
	            }
	        } catch (IOException e) {
	            System.out.println("No previous room data found.");
	        }
	    }

	    public void loadCustomers() {
	        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                int customerId = Integer.parseInt(data[0]);
	                String customerName = data[1];
	                String contactNumber = data[2];
	                customers.add(new Customer(customerId, customerName, contactNumber));
	            }
	        } catch (IOException e) {
	            System.out.println("No previous customer data found.");
	        }
	    }

	    public Room findRoomById(int roomId) {
	        for (Room room : rooms) {
	            if (room.getRoomId() == roomId) {
	                return room;
	            }
	        }
	        return null;
	    }

	    private void saveRooms() {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
	            for (Room room : rooms) {
	                bw.write(room.getRoomId() + "," + room.getRoomType() + "," + room.getPricePerNight());
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving room data.");
	        }
	    }

	    private void saveCustomers() {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
	            for (Customer customer : customers) {
	                bw.write(customer.getCustomerId() + "," + customer.getCustomerName() + "," + customer.getContactNumber());
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving customer data.");
	        }
	    }
	}

