package TestVehicle;

import EntityVehicle.*;
import Exception.InvalidVehicleException;
import ServiceVehicle.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestVehicle {

	public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     RentalService rentalService = new RentalService();

     try {
                    	
          int choice;
      	  do {
		       System.out.println("\nVehicle Rental System");
			   System.out.println("1. Add Vehicle");
			   System.out.println("2. Add Customer");
			   System.out.println("3. View Available Vehicles");
			   System.out.println("4. Rent Vehicle");
			   System.out.println("5. Return Vehicle");
			   System.out.println("6. Exit");
			   System.out.print("Enter your choice: ");

			   choice = scanner.nextInt();
			   scanner.nextLine(); 
			   switch(choice) {
			   case 1:
                     System.out.print("Enter Vehicle ID: ");
                     int vehicleId = scanner.nextInt();
                     scanner.nextLine();
                     System.out.print("Enter Vehicle Type: ");
                     String vehicleType = scanner.nextLine();
                     rentalService.addVehicle(new Vehicle(vehicleId, vehicleType));
                     System.out.println("Vehicle added successfully.");
                     break;

                case 2:
                     System.out.print("Enter Customer ID: ");
                     int customerId = scanner.nextInt();
                     scanner.nextLine();
                     System.out.print("Enter Customer Name: ");
                     String customerName = scanner.nextLine();
                     rentalService.addCustomer(new Customer(customerId, customerName));
                     System.out.println("Customer added successfully.");
                     break;

                case 3:
                      rentalService.viewAvailableVehicles();
                      break;

               case 4:
                      System.out.print("Enter Vehicle ID to rent: ");
                      int rentVehicleId = scanner.nextInt();
                      System.out.print("Enter Customer ID: ");
                      int rentCustomerId = scanner.nextInt();
                      System.out.print("Enter Rental Duration (in days): ");
                      int rentalDuration = scanner.nextInt();
                      try {
                             rentalService.rentVehicle(rentVehicleId, rentCustomerId, rentalDuration);
                             System.out.println("Vehicle rented successfully.");
                              } catch (InvalidVehicleException e) {
                                 System.out.println(e.getMessage());
                              }
                      break;

                case 5:
                       System.out.print("Enter Vehicle ID to return: ");
                       int returnVehicleId = scanner.nextInt();
                       rentalService.returnVehicle(returnVehicleId);
                       break;

                case 6:
                       System.out.println("Exiting the system...");
                       scanner.close();
                       return;

                default:
                       System.out.println("Invalid choice. Please try again.");
                       
                }
			   }while(choice!=6);}
      	  		catch (InputMismatchException e) {
                     System.out.println("Invalid input. Please enter numbers only.");
                     scanner.nextLine(); 
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
     }
}

