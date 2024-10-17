package ServiceVehicle;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import EntityVehicle.*;
import Exception.InvalidVehicleException;
import ServiceVehicle.*;

public class RentalService {
	
	 private List<Vehicle> vehicles;
	    private List<Customer> customers;
	    private List<Rental> rentals;
	    private int rentalIdCounter;

	    public RentalService() {
	        vehicles = new ArrayList<>();
	        customers = new ArrayList<>();
	        rentals = new ArrayList<>();
	        rentalIdCounter = 1; 
	    }

	    public void addVehicle(Vehicle vehicle) {
	        vehicles.add(vehicle);
	    }

	    public void addCustomer(Customer customer) {
	        customers.add(customer);
	    }

	    public void rentVehicle(int vehicleId, int customerId, int rentalDuration) throws InvalidVehicleException {
	        Vehicle vehicle = findVehicleById(vehicleId);
	        Customer customer = findCustomerById(customerId);

	        if (vehicle == null) {
	            System.out.println("Vehicle not found!");
	            return;
	        }
	        if (customer == null) {
	            System.out.println("Customer not found!");
	            return;
	        }
	        if (!vehicle.isAvailable()) {
	            throw new InvalidVehicleException("Vehicle is not available for rent.");
	        }

	        vehicle.setAvailable(false); 
	        Rental rental = new Rental(rentalIdCounter++, vehicle, customer, rentalDuration);
	        rentals.add(rental);
	        logRentalTransaction(rental);
	    }

	    public void returnVehicle(int vehicleId) {
	        for (Rental rental : rentals) {
	            if (rental.getVehicle().getVehicleId() == vehicleId) {
	                rental.getVehicle().setAvailable(true); 
	                System.out.println("Vehicle returned successfully.");
	                return;
	            }
	        }
	        System.out.println("Rental record not found for this vehicle.");
	    }

	    public void viewAvailableVehicles() {
	        System.out.println("Available Vehicles:");
	        for (Vehicle vehicle : vehicles) {
	            if (vehicle.isAvailable()) {
	                System.out.println("ID: " + vehicle.getVehicleId() + ", Type: " + vehicle.getType());
	            }
	        }
	    }

	    private Vehicle findVehicleById(int vehicleId) {
	        for (Vehicle vehicle : vehicles) {
	            if (vehicle.getVehicleId() == vehicleId) {
	                return vehicle;
	            }
	        }
	        return null;
	    }

	    private Customer findCustomerById(int customerId) {
	        for (Customer customer : customers) {
	            if (customer.getCustomerId() == customerId) {
	                return customer;
	            }
	        }
	        return null;
	    }

	    private void logRentalTransaction(Rental rental) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rental_log.txt", true))) {
	            writer.write("Rental ID: " + rental.getRentalId() +
	                         ", Vehicle ID: " + rental.getVehicle().getVehicleId() +
	                         ", Customer ID: " + rental.getCustomer().getCustomerId() +
	                         ", Duration: " + rental.getRentalDuration() + " days\n");
	        } catch (IOException e) {
	            System.out.println("Failed to log rental transaction: " + e.getMessage());
	        }
	    }
	}