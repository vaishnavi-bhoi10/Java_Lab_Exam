package Service;

import java.util.ArrayList;
import java.util.List;
import Entity.*;

public class BookingService {

	private List<Booking> bookings;

	public BookingService() {
		this.bookings = new ArrayList<>();
	}

	public void add(Booking booking) {
		bookings.add(booking);
	}

	public void remove(Booking booking) {
		bookings.remove(booking);
	}
	
	public List<Booking> getBookings() {
		return bookings;
	}

	public void displayAllBooking() {
		System.out.println("\nAll Booking Details:");
		for (Booking b : bookings) {
			b.displayAllBooking();
		}
	}
	
	public Booking findBookingByPassengerId(int passengerId) {
		for (Booking b : bookings) {
			if (b.getPassenger().getId() == passengerId) {
				return b;
			}
		}
		return null;
	}

	public Booking findBookingByFlightId(int flightId) {
		for (Booking b : bookings) {
			if (b.getFlight().getFlighId() == flightId) {
				return b;
			}
		}
		return null;
	}

	public void saveAllDetailsToFile(String filename) {
		for (Booking b : bookings) {
			b.SaveDetailsToFile(filename);
		}
	}

	public void displayAllPassengers() {
		System.out.println("\nAll Passengers:");
		for (Booking b : bookings) {
			System.out.println(b.getPassenger().toString());
		}
	}

	// Method to display all flights
	public void displayAllFlights() {
		System.out.println("\nAll Flights:");
		for (Booking b : bookings) {
			System.out.println(b.getFlight().toString());
		}
	}

}
