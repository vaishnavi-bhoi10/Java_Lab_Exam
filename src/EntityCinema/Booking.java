package EntityCinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Exception.InvalidSeatException;

public class Booking {
	private int bookingId;
    private ShowTime showTime;
    private List<Seat> bookedSeats;

    public Booking(int bookingId, ShowTime showtime, List<Seat> bookedSeats) {
        this.bookingId = bookingId;
        this.showTime = showtime;
        this.bookedSeats = bookedSeats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public ShowTime getShowtime() {
        return showTime;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Showtime ID: " + showTime.getShowtimeId() + ", Seats: " + bookedSeats;
    }
}
