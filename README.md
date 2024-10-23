# Online Store CLI Application

## Description of the Project

This Java console application simulates a simple command-line online store. It allows users to browse products, add items to their cart, remove items, and proceed to checkout. The purpose of the application is to demonstrate basic command-line interaction and file handling in Java while offering a simple shopping experience. The intended users are individuals learning Java and those looking for a demonstration of a basic store interface.

The main functionality includes displaying products, filtering them by various criteria, managing a shopping cart, processing payments, and generating a receipt. It aims to solve the problem of practicing file input/output (CSV parsing), basic data structures, and user interaction in a command-line setting.

## User Stories

- As a user, I want to be able to view all available products so that I can see what the store offers.

- As a user, I want to filter products by name, price, or department so that I can find relevant products quickly.

- As a user, I want to add products to my shopping cart so that I can prepare my order.

- As a user, I want to remove products from my shopping cart so that I can change my order if needed.

- As a user, I want to view the contents of my cart so that I can see the items I have added and their prices.

- As a user, I want to proceed to check out and pay for the items in my cart so that I can complete my purchase.

- As a user, I want to receive a receipt of my purchase so that I can keep a record of my transaction.

- As a user, I want the cart to display each product only once, even if I add multiple units of the same product.

- As a user, I want to be informed of invalid actions so that I can correct my input.

- As a user, I want the store to save my purchase details so that I can review them later.

- As a user, I want to exit the store at any time so that I can end my session.

- As a user, I want a clear, easy-to-use command-line interface so that I can navigate the store without confusion.

## Setup

### Prerequisites

- **IntelliJ IDEA**: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- **Java SDK**: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Locate the main class in `Store.java` containing the `public static void main(String[] args)` method.
5. Right-click on the `Store.java` file and select 'Run 'Store.main()'' to start the application.

## Technologies Used

- **Java**: Version corretto-17.0.13.
- **CSV File Handling**: Used to load product data from `products.csv`.
- **Maven**: For managing dependencies and building the project.

## Demo

![Application Screenshot](path/to/your/screenshot.png)

(Optional: Record a GIF or take screenshots of key functionalities such as product listing, adding/removing items to/from the cart, and checkout.)

## Future Work

- Implement product quantity management, so that multiple units of the same product are handled efficiently in the cart.
- Add a more robust search feature, allowing for complex filters and sorting options.
- Expand payment methods beyond cash.
- Include user authentication to simulate a more realistic shopping experience.
- Enhance the receipt generation to support more detailed transaction logs and better formatting.

## Resources

- [Java Programming Basics](https://www.java.com/en/)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Maven: The Complete Reference](https://maven.apache.org/guides/index.html)

## Team Members

- **Mehmet Uzun** - Developer

## Thanks

- Thank you to **Raymond Maroun** for continuous support and guidance.
- A special thanks to all teammates for their dedication and teamwork throughout the project.

