package EntityRestaurant;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrder {
	
	    private int orderId;
	    private List<MenuItem> itemList;
	    private List<Integer> quantities; 
	    private double totalPrice;

	    public CustomerOrder(int orderId) {
	        this.orderId = orderId;
	        this.itemList = new ArrayList<>();
	        this.quantities = new ArrayList<>();
	        this.totalPrice = 0.0;
	    }

	    public void addItem(MenuItem item, int quantity) {
	        itemList.add(item);
	        quantities.add(quantity);
	        totalPrice += item.getPrice() * quantity;
	    }

	    public double getTotalPrice() {
	        return totalPrice;
	    }

	    public void displayOrder() {
	        System.out.println("Order ID: " + orderId);
	        for (int i = 0; i < itemList.size(); i++) {
	            System.out.println(itemList.get(i).getItemName() + " (Quantity: " + quantities.get(i) + ") - $" + (itemList.get(i).getPrice() * quantities.get(i)));
	        }
	        System.out.println("Total Price: $" + totalPrice);
	    }
	} 