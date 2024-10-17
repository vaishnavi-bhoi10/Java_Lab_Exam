package ServiceShop;
import Exception.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import EntityShop.*;

public class ShopService {
	
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private static final String PRODUCT_FILE = "products.txt";
    private static final String CUSTOMER_FILE = "customers.txt";

    public ShopService() {
        loadProducts();
        loadCustomers();
    }

    public void addProduct(Product product) throws InvalidOrderException {
        if (findProductById(product.getProductId()) != null) {
            throw new InvalidOrderException("Product ID already exists.");
        }
        products.add(product);
        saveProducts();
    }

    public void updateProduct(int productId, double price, int stock) throws InvalidOrderException {
        Product product = findProductById(productId);
        if (product == null) {
            throw new InvalidOrderException("Product not found.");
        }
        product.setStockQuantity(stock);
        saveProducts();
    }

    public void removeProduct(int productId) throws InvalidOrderException {
        Product product = findProductById(productId);
        if (product == null) {
            throw new InvalidOrderException("Product not found.");
        }
        products.remove(product);
        saveProducts();
    }

    public void showStock() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) throws InvalidOrderException {
        if (findCustomerById(customer.getCustomerId()) != null) {
            throw new InvalidOrderException("Customer ID already exists.");
        }
        customers.add(customer);
        saveCustomers();
    }

    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void processSale(int productId, int customerId, int quantity) throws InvalidOrderException {
        Product product = findProductById(productId);
        if (product == null) {
            throw new InvalidOrderException("Product not found.");
        }
        if (product.getStockQuantity() < quantity) {
            throw new InvalidOrderException("Insufficient stock.");
        }

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new InvalidOrderException("Customer not found.");
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        System.out.println("Transaction completed: " + customer.getCustomerName() + " bought " + quantity + " of " + product.getProductName() + ".");
        saveProducts();
    }

  
    private void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int productId = Integer.parseInt(data[0]);
                String productName = data[1];
                double price = Double.parseDouble(data[2]);
                int stockQuantity = Integer.parseInt(data[3]);
                products.add(new Product(productId, productName, price, stockQuantity));
            }
        } catch (IOException e) {
            System.out.println("No previous product data found.");
        }
    }

    private void saveProducts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product product : products) {
                bw.write(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + "," + product.getStockQuantity());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving product data.");
        }
    }

  
    private void loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int customerId = Integer.parseInt(data[0]);
                String customerName = data[1];
                String contactNumber = data[2];
                customers.add(new Customer(customerId, customerName, contactNumber));
            }
        } catch (IOException e) {
            System.out.println("No previous customer data found.");
        }
    }

    private void saveCustomers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer customer : customers) {
                bw.write(customer.getCustomerId() + "," + customer.getCustomerName() + "," + customer.getContactNumber());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customer data.");
        }
    }
}