package EntityCinema;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import Exception.InvalidSeatException;

public class ShowTime {
    private int showtimeId;
    private Movie movie;
    private List<Seat> seats;

    public ShowTime(int showtimeId, Movie movie, int numberOfSeats) {
        this.showtimeId = showtimeId;
        this.movie = movie;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public boolean isSeatAvailable(int seatId) {
        return seats.get(seatId - 1).isAvailable();
    }

    public void reserveSeat(int seatId) throws InvalidSeatException {
        if (!isSeatAvailable(seatId)) {
            throw new InvalidSeatException("Seat " + seatId + " is not available.");
        }
        seats.get(seatId - 1).reserve();
    }

    @Override
    public String toString() {
        return "Showtime ID: " + showtimeId + ", Movie: " + movie.getTitle();
    }
}
