package ServiceCinema;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import EntityCinema.*;
import Exception.InvalidSeatException;

public class BookingService {
	 private List<Movie> movies;
	    private List<ShowTime> showtimes;
	    private List<Booking> bookings;
	    private static final String BOOKING_LOG_FILE = "booking_log.txt";

	    public BookingService() {
	        movies = new ArrayList<>();
	        showtimes = new ArrayList<>();
	        bookings = new ArrayList<>();
	    }

	    public void addMovie(Movie movie) {
	        movies.add(movie);
	    }

	    public List<Movie> getMovies() {
	        return movies;
	    }

	   
	    public void addShowtime(ShowTime showtime) {
	        showtimes.add(showtime);
	    }

	    public List<ShowTime> getShowtimes() {
	        return showtimes;
	    }

	    public void bookSeats(int showtimeId, List<Integer> seatIds) throws InvalidSeatException {
	        ShowTime showtime = findShowtimeById(showtimeId);
	        List<Seat> bookedSeats = new ArrayList<>();

	        for (int seatId : seatIds) {
	            showtime.reserveSeat(seatId);
	            bookedSeats.add(showtime.getSeats().get(seatId - 1));
	        }

	        Booking booking = new Booking(bookings.size() + 1, showtime, bookedSeats);
	        bookings.add(booking);
	        logBooking(booking);
	    }

	    public ShowTime findShowtimeById(int showtimeId) {
	        for (ShowTime showtime : showtimes) {
	            if (showtime.getShowtimeId() == showtimeId) {
	                return showtime;
	            }
	        }
	        return null;
	    }

	    private void logBooking(Booking booking) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKING_LOG_FILE, true))) {
	            bw.write(booking.toString());
	            bw.newLine();
	        } catch (IOException e) {
	            System.out.println("Error logging booking: " + e.getMessage());
	        }
	    }
	}
