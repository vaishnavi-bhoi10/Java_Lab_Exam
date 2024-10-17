package TestAirline;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import Exception.*;
import Entity.*;
import Service.*;


public class Test {

	public static void main(String[] args) {

		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BookingService bookingservice = new BookingService();
		boolean continueRunning = true;


		try {
			do {
			System.out.println("\n===== Airline Booking System =====");
			System.out.println("1. Add Passenger and Booking");
			System.out.println("2. Add Flight");
			System.out.println("3. Remove Passenger");
			System.out.println("4. Remove Flight");
			System.out.println("5. Display All Bookings");
			System.out.println("6. Update Passenger");
			System.out.println("7. Update Flight");
			System.out.println("8. Display All Passengers");
			System.out.println("9. Display All Filghts");
			System.out.println("0. Exit");
			System.out.println("==================================");
			System.out.print("Choose an option: ");

			int choice = Integer.parseInt(read.readLine());

			switch (choice) {
			case 1: 
				System.out.println("Enter Passenger id:");
				int id = Integer.parseInt(read.readLine());
				System.out.println("Enter Passenger name:");
				String name = read.readLine();
				System.out.println("Enter Contact Number");
				String contactNo = read.readLine();

				Passenger passenger = new Passenger(id, name, contactNo);

				System.out.println("Enter Flight id: ");
				int flightId = Integer.parseInt(read.readLine());
				System.out.println("Enter Destination:");
				String destination = read.readLine();
				System.out.println("Enter Departure Time:");
				String departureTime = read.readLine();
				System.out.println("Enter Seats Available For this flight:");
				int seatsAvailable = Integer.parseInt(read.readLine());
				System.out.println("Enter Price for one Ticket");
				int ticketPrice = Integer.parseInt(read.readLine());

				Flight flight = new Flight(flightId, destination, ticketPrice, seatsAvailable, departureTime);

				Booking booking = new Booking(passenger, flight);
				bookingservice.add(booking);
				bookingservice.saveAllDetailsToFile("C:\\Users\\CDAC\\Desktop\\Shweta\\Airline.txt");

				System.out.println("Passenger and Booking added successfully.");
				break;

			case 2:
				System.out.println("Enter Flight id: ");
				flightId = Integer.parseInt(read.readLine());
				System.out.println("Enter Destination:");
				destination = read.readLine();
				System.out.println("Enter Departure Time:");
				departureTime = read.readLine();
				System.out.println("Enter Seats Available For this flight:");
				seatsAvailable = Integer.parseInt(read.readLine());
				System.out.println("Enter Price for one Ticket");
				ticketPrice = Integer.parseInt(read.readLine());

				flight = new Flight(flightId, destination, ticketPrice, seatsAvailable, departureTime);
				System.out.println("Flight added successfully.");
				break;
				
			case 3: 
				System.out.println("Enter Passenger ID to remove:");
				int passengerId = Integer.parseInt(read.readLine());

				Booking bookingToRemove = null;
				for (Booking b : bookingservice.getBookings()) {
					if (b.getPassenger().getId() == passengerId) {
						bookingToRemove = b;
						break;
					}
				}
				if (bookingToRemove != null) {
					bookingservice.remove(bookingToRemove);
					System.out.println("Passenger removed successfully.");
				} else {
					System.out.println("Passenger not found.");
				}
				break;

			case 4: 
				System.out.println("Enter Flight ID to remove:");
				flightId = Integer.parseInt(read.readLine());

				Booking flightToRemove = null;
				for (Booking b : bookingservice.getBookings()) {
					if (b.getFlight().getFlighId() == flightId) {
						flightToRemove = b;
						break;
					}
				}
				if (flightToRemove != null) {
					bookingservice.remove(flightToRemove);
					System.out.println("Flight removed successfully.");
				} else {
					System.out.println("Flight not found.");
				}
				break;

			case 5: 
				bookingservice.displayAllBooking();
				break;

			case 6: 
				System.out.println("Enter Passenger ID to update:");
				passengerId = Integer.parseInt(read.readLine());

				for (Booking b : bookingservice.getBookings()) {
					if (b.getPassenger().getId() == passengerId) {
						System.out.println("Enter new Passenger Name:");
						String newName = read.readLine();
						System.out.println("Enter new Contact Number:");
						String newContactNo = read.readLine();
						b.getPassenger().setName(newName);
						b.getPassenger().setContactNumber(newContactNo);
						System.out.println("Passenger details updated successfully.");
						break;
					}
				}
				break;

			case 7: 
				System.out.println("Enter Flight ID to update:");
				flightId = Integer.parseInt(read.readLine());

				for (Booking b : bookingservice.getBookings()) {
					if (b.getFlight().getFlighId() == flightId) {
						System.out.println("Enter new Destination:");
						String newDestination = read.readLine();
						System.out.println("Enter new Departure Time:");
						String newDepartureTime = read.readLine();
						System.out.println("Enter new Seats Available:");
						int newSeatsAvailable = Integer.parseInt(read.readLine());
						System.out.println("Enter new Ticket Price:");
						double newTicketPrice = Double.parseDouble(read.readLine());

						b.getFlight().setDestination(newDestination);
						b.getFlight().setDepartureTime(newDepartureTime);
						b.getFlight().setSeatsAvailable(newSeatsAvailable);
						b.getFlight().setTicketPrice(newTicketPrice);
						System.out.println("Flight details updated successfully.");
						break;
					}
				}
				break;

			case 8: 
				bookingservice.displayAllPassengers();
				break;

			case 9: 
				bookingservice.displayAllFlights();
				break;

			case 0: 
				System.out.println("Thank You...");
				break;

			default:
				System.out.println("Invalid option. Please select correct.");
			}
		}while (continueRunning);

		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid input. Please enter correct data.");
		} catch (seatsAvailableException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}