package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online com.pluralsight.Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                String sku = values[0];
                String name = values[1];
                double price = Double.parseDouble(values[2]);
                String department = values[3];
                Product product = new Product(sku, name, price, department);
                inventory.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }

        // This method should read a CSV file with product information and
        // populate the inventory ArrayList with com.pluralsight.Product objects. Each line
        // of the CSV file contains product information in the following format:
        //
        // id,name,price
        //
        // where id is a unique string identifier, name is the product name,
        // price is a double value representing the price of the product
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        System.out.println("How would you like to filter products?");
        System.out.println("1. By Name");
        System.out.println("2. By Price");
        System.out.println("3. By Department");
        System.out.println("4. Show All");

        int filterChoice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Product> filteredProducts = new ArrayList<>(inventory);

        switch (filterChoice) {
            case 1:
                System.out.println("Enter product name to search:");
                String name = scanner.nextLine().toLowerCase();
                filteredProducts.removeIf(product -> !product.getName().toLowerCase().contains(name));
                break;
            case 2:
                System.out.println("Enter maximum price:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                filteredProducts.removeIf(product -> product.getPrice() > price);
                break;
            case 3:
                System.out.println("Enter department to search:");
                String department = scanner.nextLine().toLowerCase();
                filteredProducts.removeIf(product -> !product.getDepartment().toLowerCase().contains(department));
                break;
            case 4:
                break; // Show all products (no filtering)
            default:
                System.out.println("Invalid filter choice. Showing all products.");
        }

        System.out.println("Available Products:");
        for (Product product : filteredProducts) {
            System.out.println(product);
        }

        System.out.println("Enter the SKU of the product to add to cart, or 'back' to return:");
        String input = scanner.nextLine();

        if (!input.equals("back")) {
            Product selectedProduct = findProductById(input, filteredProducts);
            if (selectedProduct != null) {
                cart.add(selectedProduct);
                System.out.println(selectedProduct.getName() + " added to cart.");
            } else {
                System.out.println("Product not found.");
            }
        }

        // This method should display a list of products from the inventory,
        // and prompt the user to add items to their cart. The method should
        // prompt the user to enter the ID of the product they want to add to
        // their cart. The method should
        // add the selected product to the cart ArrayList.
    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Your Cart:");
        totalAmount = 0.0;
        for (Product product : cart) {
            System.out.println(product);
            totalAmount += product.getPrice();
        }

        System.out.printf("Total Amount: $%.2f%n", totalAmount);
        System.out.println("Enter SKU to remove item, or 'checkout' to proceed to checkout, or 'back' to return:");

        String input = scanner.nextLine();

        if (input.equals("checkout")) {
            checkOut(cart, totalAmount);
        } else if (!input.equals("back")) {
            Product selectedProduct = findProductById(input, cart);
            if (selectedProduct != null) {
                cart.remove(selectedProduct);
                System.out.println(selectedProduct.getName() + " removed from cart.");
            } else {
                System.out.println("Product not found in cart.");
            }
        }
        // This method should display the items in the cart ArrayList, along
        // with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.
    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Total Amount: $%.2f. Please enter payment amount: ", totalAmount);
        double payment = scanner.nextDouble();

        if (payment >= totalAmount) {
            double change = payment - totalAmount;
            System.out.printf("Payment successful! Change: $%.2f%n", change);
            generateReceipt(cart, totalAmount, payment, change);
            cart.clear();
        } else {
            System.out.println("Insufficient funds. Returning to cart.");
        }
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and deduct the total cost
        // from their account if they confirm.
    }

    public static void generateReceipt(ArrayList<Product> cart, double totalAmount, double payment, double change) {
        System.out.println("Sales Receipt:");
        System.out.println("Order Date: " + LocalDateTime.now());
        for (Product product : cart) {
            System.out.println(product);
        }
        System.out.printf("Total: $%.2f | Paid: $%.2f | Change: $%.2f%n", totalAmount, payment, change);
        saveReceiptToFile(cart, totalAmount, payment, change);
    }

    public static void saveReceiptToFile(ArrayList<Product> cart, double totalAmount, double payment, double change) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String timestamp = dtf.format(LocalDateTime.now());
            File directory = new File("Receipts");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File receiptFile = new File(directory, timestamp + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
                writer.write("Sales Receipt:\n");
                writer.write("Order Date: " + LocalDateTime.now() + "\n");
                for (Product product : cart) {
                    writer.write(product + "\n");
                }
                writer.write(String.format("Total: $%.2f | Paid: $%.2f | Change: $%.2f%n", totalAmount, payment, change));
            }

            System.out.println("Receipt saved to " + receiptFile.getPath());
        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        }
    }
    public static Product findProductById(String id, ArrayList<Product> inventory) {
        
        // This method should search the inventory ArrayList for a product with
        // the specified ID, and return the corresponding com.pluralsight.Product object. If
        // no product with the specified ID is found, the method should return
        // null.
    }
}
