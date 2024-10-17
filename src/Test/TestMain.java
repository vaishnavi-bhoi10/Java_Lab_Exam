package Test;

import java.util.Scanner;

import Entity.Customer;
import Entity.Room;
import Exception.RoomNotAvailableException;
import Service.HotelReservationService;

public class TestMain {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        HotelReservationService reservationService = new HotelReservationService();

	        while (true) {
	            System.out.println("\nHotel Reservation System");
	            System.out.println("1. Add Room");
	            System.out.println("2. View Available Rooms");
	            System.out.println("3. Book Room");
	            System.out.println("4. Add Customer");
	            System.out.println("5. Display Current Reservations");
	            System.out.println("6. Generate Invoice");
	            System.out.println("7. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter Room ID: ");
	                    int roomId = scanner.nextInt();
	                    scanner.nextLine(); 
	                    System.out.print("Enter Room Type: ");
	                    String roomType = scanner.nextLine();
	                    System.out.print("Enter Price per Night: ");
	                    double pricePerNight = scanner.nextDouble();
	                    Room room = new Room(roomId, roomType, pricePerNight);
	                    reservationService.addRoom(room);
	                    System.out.println("Room added successfully!");
	                    break;

	                case 2:
	                    reservationService.viewAvailableRooms();
	                    break;

	                case 3:
	                    try {
	                        System.out.print("Enter Room ID to book: ");
	                        int roomToBookId = scanner.nextInt();
	                        System.out.print("Enter Customer ID: ");
	                        int customerId = scanner.nextInt();
	                        scanner.nextLine();
	                        System.out.print("Enter Check-In Date (YYYY-MM-DD): ");
	                        String checkInDate = scanner.nextLine();
	                        System.out.print("Enter Check-Out Date (YYYY-MM-DD): ");
	                        String checkOutDate = scanner.nextLine();
	                        reservationService.bookRoom(roomToBookId, customerId, checkInDate, checkOutDate);
	                        System.out.println("Room booked successfully!");
	                    } catch (RoomNotAvailableException e) {
	                        System.out.println(e.getMessage());
	                    }
	                    break;

	                case 4:
	                    System.out.print("Enter Customer ID: ");
	                    int customerIdInput = scanner.nextInt();
	                    scanner.nextLine(); 
	                    System.out.print("Enter Customer Name: ");
	                    String customerName = scanner.nextLine();
	                    System.out.print("Enter Contact Number: ");
	                    String contactNumber = scanner.nextLine();
	                    Customer customer = new Customer(customerIdInput, customerName, contactNumber);
	                    reservationService.addCustomer(customer);
	                    System.out.println("Customer added successfully!");
	                    break;

	                case 5:
	                    reservationService.displayCurrentReservations();
	                    break;

	                case 6:
	                    System.out.print("Enter Reservation ID to generate invoice: ");
	                    int reservationId = scanner.nextInt();
	                    reservationService.generateInvoice(reservationId);
	                    break;

	                case 7:
	                    System.out.println("Exiting the system. Goodbye!");
	                    System.exit(0);
	                    break;

	                default:
	                    System.out.println("Invalid option. Please choose again.");
	            }
	        }
	    }
	}
