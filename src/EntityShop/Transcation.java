package EntityShop;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Transcation {
	class Transaction {
		private Product product;
		private Customer customer;
		private int quantity;
		private double totalAmount;

		public Transaction(Product product, Customer customer, int quantity) {
			this.product = product;
			this.customer = customer;
			this.quantity = quantity;
			this.totalAmount = product.getPrice() * quantity;
		}

		public Product getProduct() {
			return product;
		}

		public Customer getCustomer() {
			return customer;
		}

		public int getQuantity() {
			return quantity;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		@Override
		public String toString() {
			return "Transaction: Product=" + product.getProductName() + ", Customer=" + customer.getCustomerName()
					+ ", Quantity=" + quantity + ", Total= $" + totalAmount;
		}
	}
}
