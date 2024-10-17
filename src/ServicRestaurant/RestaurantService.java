package ServicRestaurant;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import EntityRestaurant.*;
import Exception.InvalidOrderException;
import ServicRestaurant.*;

public class RestaurantService {
	
	    private List<MenuItem> menuItems;
	    private List<CustomerOrder> orders;
	    private static final String MENU_FILE = "menu.txt";

	    public RestaurantService() {
	        menuItems = new ArrayList<>();
	        orders = new ArrayList<>();
	        loadMenuItems();
	    }

	    public void addMenuItem(MenuItem item) throws InvalidOrderException {
	        if (findMenuItemById(item.getItemId()) != null) {
	            throw new InvalidOrderException("Menu item ID already exists.");
	        }
	        menuItems.add(item);
	        saveMenuItems();
	    }

	    public void updateMenuItem(int itemId, String newName, double newPrice) throws InvalidOrderException {
	        MenuItem item = findMenuItemById(itemId);
	        if (item == null) {
	            throw new InvalidOrderException("Menu item not found.");
	        }
	        menuItems.remove(item);
	        menuItems.add(new MenuItem(itemId, newName, newPrice));
	        saveMenuItems();
	    }

	    public void removeMenuItem(int itemId) throws InvalidOrderException {
	        MenuItem item = findMenuItemById(itemId);
	        if (item == null) {
	            throw new InvalidOrderException("Menu item not found.");
	        }
	        menuItems.remove(item);
	        saveMenuItems();
	    }

	    public void displayMenu() {
	        System.out.println("Menu:");
	        for (MenuItem item : menuItems) {
	            System.out.println(item);
	        }
	    }

	    public MenuItem findMenuItemById(int itemId) {
	        for (MenuItem item : menuItems) {
	            if (item.getItemId() == itemId) {
	                return item;
	            }
	        }
	        return null;
	    }

	    public CustomerOrder createOrder(int orderId) {
	        CustomerOrder order = new CustomerOrder(orderId);
	        orders.add(order);
	        return order;
	    }

	    public void displayOrders() {
	        for (CustomerOrder order : orders) {
	            order.displayOrder();
	        }
	    }

	    private void loadMenuItems() {
	        try (BufferedReader br = new BufferedReader(new FileReader(MENU_FILE))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                int itemId = Integer.parseInt(data[0]);
	                String itemName = data[1];
	                double price = Double.parseDouble(data[2]);
	                menuItems.add(new MenuItem(itemId, itemName, price));
	            }
	        } catch (IOException e) {
	            System.out.println("No previous menu data found.");
	        }
	    }

	    private void saveMenuItems() {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_FILE))) {
	            for (MenuItem item : menuItems) {
	                bw.write(item.getItemId() + "," + item.getItemName() + "," + item.getPrice());
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving menu data.");
	        }
	    }
	}