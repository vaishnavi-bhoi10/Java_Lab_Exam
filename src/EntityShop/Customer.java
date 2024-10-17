package EntityShop;

public class Customer {
	
		private int customerId;
		private String customerName;
		private String contactNumber;

		public Customer(int customerId, String customerName, String contactNumber) {
			this.customerId = customerId;
			this.customerName = customerName;
			this.contactNumber = contactNumber;
		}

		public int getCustomerId() {
			return customerId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		@Override
		public String toString() {
			return "Customer ID: " + customerId + ", Name: " + customerName + ", Contact: " + contactNumber;
		}
	}