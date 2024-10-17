package Test;
import java.io.BufferedReader;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.InputStreamReader;
import EntityShop.*;
import Exception.InvalidOrderException;
import ServiceShop.*;

public class TestShop {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        ShopService shopService = new ShopService();

	        while (true) {
	            System.out.println("\nShop Management System");
	            System.out.println("1. Add Product");
	            System.out.println("2. Update Product");
	            System.out.println("3. Remove Product");
	            System.out.println("4. Add Customer");
	            System.out.println("5. Process Sale");
	            System.out.println("6. Show Stock Levels");
	            System.out.println("7. Exit");
	            System.out.print("Choose an option: ");

	            int choice = scanner.nextInt();
	            try {
	                switch (choice) {
	                    case 1:
	                        System.out.print("Enter Product ID: ");
	                        int pId = scanner.nextInt();
	                        System.out.print("Enter Product Name: ");
	                        String pName = scanner.next();
	                        System.out.print("Enter Price: ");
	                        double price = scanner.nextDouble();
	                        System.out.print("Enter Stock Quantity: ");
	                        int stock = scanner.nextInt();
	                        shopService.addProduct(new Product(pId, pName, price, stock));
	                        System.out.println("Product added successfully.");
	                        break;

	                    case 2:
	                        System.out.print("Enter Product ID to update: ");
	                        int updateId = scanner.nextInt();
	                        System.out.print("Enter new Price: ");
	                        double newPrice = scanner.nextDouble();
	                        System.out.print("Enter new Stock Quantity: ");
	                        int newStock = scanner.nextInt();
	                        shopService.updateProduct(updateId, newPrice, newStock);
	                        System.out.println("Product updated successfully.");
	                        break;

	                    case 3:
	                        System.out.print("Enter Product ID to remove: ");
	                        int removeId = scanner.nextInt();
	                        shopService.removeProduct(removeId);
	                        System.out.println("Product removed successfully.");
	                        break;

	                    case 4:
	                        System.out.print("Enter Customer ID: ");
	                        int cId = scanner.nextInt();
	                        System.out.print("Enter Customer Name: ");
	                        String cName = scanner.next();
	                        System.out.print("Enter Contact Number: ");
	                        String contact = scanner.next();
	                        shopService.addCustomer(new Customer(cId, cName, contact));
	                        System.out.println("Customer added successfully.");
	                        break;

	                    case 5:
	                        System.out.print("Enter Product ID for sale: ");
	                        int saleProductId = scanner.nextInt();
	                        System.out.print("Enter Customer ID: ");
	                        int saleCustomerId = scanner.nextInt();
	                        System.out.print("Enter Quantity: ");
	                        int quantity = scanner.nextInt();
	                        shopService.processSale(saleProductId, saleCustomerId, quantity);
	                        break;

	                    case 6:
	                        System.out.println("Current stock levels:");
	                        shopService.showStock();
	                        break;

	                    case 7:
	                        System.out.println("Exiting the system.");
	                        scanner.close();
	                        return;

	                    default:
	                        System.out.println("Invalid option. Please choose again.");
	                }
	            } catch (InvalidOrderException e) {
	                System.out.println("Error: " + e.getMessage());
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter numeric values.");
	                scanner.next(); 
	            } catch (Exception e) {
	                System.out.println("An unexpected error occurred: " + e.getMessage());
	            }
	        }
	    }
	}