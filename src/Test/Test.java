package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import ServicRestaurant.*;
import EntityRestaurant.*;
import Exception.InvalidOrderException;

public class Test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RestaurantService restaurantService = new RestaurantService();

		try {
			
			int choice;
			do {
			System.out.println("\nRestaurant Management System");
			System.out.println("1. Add Menu Item");
			System.out.println("2. Update Menu Item");
			System.out.println("3. Remove Menu Item");
			System.out.println("4. Place Order");
			System.out.println("5. Display Menu");
			System.out.println("6. Display Orders");
			System.out.println("7. Exit");
			System.out.print("Choose an option: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Item ID: ");
				int itemId = scanner.nextInt();
				System.out.print("Enter Item Name: ");
				String itemName = scanner.next();
				System.out.print("Enter Item Price: ");
				double price = scanner.nextDouble();
				restaurantService.addMenuItem(new MenuItem(itemId, itemName, price));
				System.out.println("Menu item added successfully.");
				break;

			case 2:
				System.out.print("Enter Item ID to update: ");
				int updateId = scanner.nextInt();
				System.out.print("Enter new Item Name: ");
				String newName = scanner.next();
				System.out.print("Enter new Item Price: ");
				double newPrice = scanner.nextDouble();
				restaurantService.updateMenuItem(updateId, newName, newPrice);
				System.out.println("Menu item updated successfully.");
				break;

			case 3:
				System.out.print("Enter Item ID to remove: ");
				int removeId = scanner.nextInt();
				restaurantService.removeMenuItem(removeId);
				System.out.println("Menu item removed successfully.");
				break;

			case 4:
				System.out.print("Enter Order ID: ");
				int orderId = scanner.nextInt();
				CustomerOrder order = restaurantService.createOrder(orderId);
				System.out.print("How many items to order? ");
				int numItems = scanner.nextInt();
				for (int i = 0; i < numItems; i++) {
					System.out.print("Enter Menu Item ID: ");
					int orderItemId = scanner.nextInt();
					MenuItem item = restaurantService.findMenuItemById(orderItemId);
					if (item == null) {
						throw new InvalidOrderException("Item not found in the menu.");
					}
					System.out.print("Enter Quantity: ");
					int quantity = scanner.nextInt();
					order.addItem(item, quantity);
				}
				System.out.println("Order placed successfully. Total Price: $" + order.getTotalPrice());
				break;

			case 5:
				restaurantService.displayMenu();
				break;

			case 6:
				System.out.println("Current orders:");
				restaurantService.displayOrders();
				break;

			case 7:
				System.out.println("Exiting the system.");
				scanner.close();
				return;

			default:
				System.out.println("Invalid option. Please choose again.");
			}
			}while(choice!=7);
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
