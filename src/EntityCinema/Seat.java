package EntityCinema;

public class Seat {
	private int seatId;
    private boolean available;

    public Seat(int seatId) {
        this.seatId = seatId;
        this.available = true; 
    }

    public int getSeatId() {
        return seatId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void reserve() {
        this.available = false;
    }

    @Override
    public String toString() {
        return "Seat ID: " + seatId + ", Available: " + (available ? "Yes" : "No");
    }
}
