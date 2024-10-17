package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ServiceCinema.*;
import EntityCinema.*;
import Exception.*;

public class TestCinema {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BookingService bookingService = new BookingService();

		try {

			int choice;
			do {
				System.out.println("\nCinema Booking System");
				System.out.println("1. Add Movie");
				System.out.println("2. Add Showtime");
				System.out.println("3. View Movies");
				System.out.println("4. View Showtimes");
				System.out.println("5. Book Seats");
				System.out.println("6. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine(); 

				switch (choice) {
				case 1:
					System.out.print("Enter Movie ID: ");
					int movieId = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter Movie Title: ");
					String title = scanner.nextLine();
					Movie movie = new Movie(movieId, title);
					bookingService.addMovie(movie);
					System.out.println("Movie added successfully.");
					break;

				case 2:
					System.out.print("Enter Showtime ID: ");
					int showtimeId = scanner.nextInt();
					System.out.print("Enter Movie ID for this showtime: ");
					int associatedMovieId = scanner.nextInt();
					Movie associatedMovie = bookingService.getMovies().stream()
							.filter(m -> m.getMovieId() == associatedMovieId).findFirst().orElse(null);
					if (associatedMovie == null) {
						System.out.println("Movie not found!");
						break;
					}
					System.out.print("Enter Number of Seats: ");
					int numberOfSeats = scanner.nextInt();
					ShowTime showtime = new ShowTime(showtimeId, associatedMovie, numberOfSeats);
					bookingService.addShowtime(showtime);
					System.out.println("Showtime added successfully.");
					break;

				case 3:
					System.out.print("Enter Showtime ID to check seats: ");
					int checkShowtimeId = scanner.nextInt();
					ShowTime checkShowtime = bookingService.findShowtimeById(checkShowtimeId);
					if (checkShowtime != null) {
						System.out.println("Available seats for " + checkShowtime.getMovie().getTitle() + ":");
						for (Seat seat : checkShowtime.getSeats()) {
							System.out.println(seat);
						}
					} else {
						System.out.println("Showtime not found.");
					}
					break;

				case 4:
					System.out.print("Enter Showtime ID to book seats: ");
					int bookingShowtimeId = scanner.nextInt();
					System.out.print("Enter number of seats to book: ");
					int seatsToBook = scanner.nextInt();
					List<Integer> seatIds = new ArrayList<>();
					for (int i = 0; i < seatsToBook; i++) {
						System.out.print("Enter Seat ID to book: ");
						seatIds.add(scanner.nextInt());
					}
					try {
						bookingService.bookSeats(bookingShowtimeId, seatIds);
						System.out.println("Seats booked successfully.");
					} catch (InvalidSeatException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 5:
					System.out.println("Exiting the system...");
					scanner.close();
					return;

				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} while (choice != 5);
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter numbers only.");
			scanner.nextLine(); // Clear the invalid input
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}

	}
}
