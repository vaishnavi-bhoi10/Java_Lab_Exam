package Entity;

import Exception.seatsAvailableException;

public class Flight {

	private int flighId;
	private String destination;
	private double ticketPrice;
	private int seatsAvailable;
	private String departureTime;

	public Flight(int flighId, String destination, double ticketPrice, int seatsAvailable, String departureTime)
			throws seatsAvailableException {
		if (seatsAvailable <= 0) {
			throw new seatsAvailableException("Seats Not Available");
		}
		this.flighId = flighId;
		this.destination = destination;
		this.ticketPrice = ticketPrice;
		this.seatsAvailable = seatsAvailable;
		this.departureTime = departureTime;
	}

	public int getFlighId() {
		return flighId;
	}

	public void setFlighId(int flighId) {
		this.flighId = flighId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "Flight [flighId=" + flighId + ", destination=" + destination + ", ticketPrice=" + ticketPrice + " /- Rs"
				+ ", seatsAvailable=" + seatsAvailable + ", departureTime=" + departureTime + "]";
	}

}
