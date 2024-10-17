package Entity;

import java.util.Calendar;

public class Reservation {
	  private int reservationId;
	    private int roomId;
	    private int customerId;
	    private String checkInDate;
	    private String checkOutDate;

	    public Reservation(int reservationId, int roomId, int customerId, String checkInDate, String checkOutDate) {
	        this.reservationId = reservationId;
	        this.roomId = roomId;
	        this.customerId = customerId;
	        this.checkInDate = checkInDate;
	        this.checkOutDate = checkOutDate;
	    }

	    public int getReservationId() {
	        return reservationId;
	    }

	    public int getRoomId() {
	        return roomId;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public String getCheckInDate() {
	        return checkInDate;
	    }

	    public String getCheckOutDate() {
	        return checkOutDate;
	    }

	    public double calculateTotalCost(Room room) {
	        // Calculate the number of nights
	        long nights = (convertDateToMillis(checkOutDate) - convertDateToMillis(checkInDate)) / (1000 * 60 * 60 * 24);
	        return nights * room.getPricePerNight();
	    }

	    private long convertDateToMillis(String date) {
	        String[] parts = date.split("-");
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
	        return calendar.getTimeInMillis();
	    }

	    @Override
	    public String toString() {
	        return "Reservation ID: " + reservationId + ", Room ID: " + roomId + ", Customer ID: " + customerId + ", Check-In: " + checkInDate + ", Check-Out: " + checkOutDate;
	    }
	}
