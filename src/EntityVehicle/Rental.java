package EntityVehicle;

import java.io.BufferedWriter;
import java.time.LocalDate;

public class Rental {
	
	    private int rentalId;
	    private Vehicle vehicle;
	    private Customer customer;
	    private int rentalDuration; 

	    public Rental(int rentalId, Vehicle vehicle, Customer customer, int rentalDuration) {
	        this.rentalId = rentalId;
	        this.vehicle = vehicle;
	        this.customer = customer;
	        this.rentalDuration = rentalDuration;
	    }

	    public int getRentalId() {
	        return rentalId;
	    }

	    public Vehicle getVehicle() {
	        return vehicle;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public int getRentalDuration() {
	        return rentalDuration;
	    }
	}
