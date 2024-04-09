
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Connector connector = new Connector();
        connector.initializeDatabase();
        Products products = new Products(connector);
        Orders orders = new Orders(connector);
        Customers customers = new Customers(connector);
        Storage storage = new Storage(connector);
        String designation = "";
        String price = "";
        String productID = "";
        String customerID = "";
        String count = "";
        String name = "";
        String email = "";
        String input = "";
        while (running) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select your action [table/action]: ");
            System.out.println("Actions x: [d] Delete Entry | [c] Create Entry | [u] Update Entry | [s] Show Entry | [e] Export as JSON | [j] Join Tables");
            System.out.println("Select the Table: ");
            System.out.println("1x Products");
            System.out.println("2x Customers");
            System.out.println("3x Orders");
            System.out.println("4x Storage");
            System.out.println("5 Order Product");
            System.out.println("6 Exit");
            String action = scanner.nextLine();
            switch (action) {
                case "1d":
                    products.showData("select * from products");
                    System.out.println("Product ID: ");
                    productID = scanner.nextLine();
                    products.deleteEntry(Integer.parseInt(productID));
                    break;
                case "2d":
                    customers.showData();
                    System.out.println("Customer ID: ");
                    customerID = scanner.nextLine();
                    customers.deleteEntry(Integer.parseInt(customerID));
                    break;
                case "3d":
                    orders.showData();
                    System.out.println("Customer ID / Product ID: ");
                    String id = scanner.nextLine();
                    orders.deleteEntry(Integer.parseInt(id));
                    break;
                case "4d":
                    storage.showData();
                    System.out.println("Product ID: ");
                    productID = scanner.nextLine();
                    storage.deleteEntry(Integer.parseInt(productID));
                    break;
                case "1c":
                    createProduct(scanner,products);
                    break;
                case "2c":
                    createCustomer(scanner,customers);
                    break;
                case "3c":
                    System.out.println("Customers: ");
                    customers.showData();
                    System.out.println();
                    System.out.println("Products:");
                    products.showData("select * from products");
                    System.out.println();
                    System.out.println("Customer ID: ");
                    customerID = scanner.nextLine();
                    System.out.println("Product ID: ");
                    productID = scanner.nextLine();
                    System.out.println("Count: ");
                    count = scanner.nextLine();
                    try {
                        orders.writeData(Integer.parseInt(customerID), Integer.parseInt(productID), Integer.parseInt(count));
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("The number input format was wrong");
                    }
                    break;
                case "4c":
                    System.out.println("Product ID: ");
                    productID = scanner.nextLine();
                    System.out.println("Count: ");
                    count = scanner.nextLine();
                    storage.writeData(Integer.parseInt(productID),Integer.parseInt(count));
                    break;
                case "1u":
                    break;
                case "2u":
                    break;
                case "3u":
                    break;
                case "1s":
                    products.showData("select * from products");
                    break;
                case "2s":
                    customers.showData();
                    break;
                case "3s":
                    orders.showData();
                    break;
                case "4s":
                    storage.showData();
                    break;
                case "1e":
                    products.createJson();
                case "2e":
                    customers.createJson();
                case "3e":
                    orders.createJson();
                case "j":
                    System.out.println("How do you want to join the products table: ");
                    System.out.println("1 Left Join");
                    System.out.println("2 Right Join");
                    System.out.println("3 Inner Join");
                    System.out.println("4 Full Join");
                    input = scanner.nextLine();
                    switch (input) {
                        case "1":
                            products.showData("select * from orders inner join products p on p.product_id = orders.product_id");
                            break;
                        case "2":
                            products.showData("select * from orders left join products p on p.product_id = orders.product_id");
                            break;
                        case "3":
                            products.showData("select * from orders right join products p on p.product_id = orders.product_id");
                            break;
                        case "4":
                            products.showData("select * from orders union select * from products");
                            break;
                    }
                case "5":
                    System.out.println("Do you already have a customer account: y/n");
                    input = scanner.nextLine();
                    if (input.equals("y")) {
                        customers.showData();
                        System.out.println("What is the id of your customer account: ");
                    } else if (input.equals("n")) {
                        createCustomer(scanner,customers);
                    }
                    break;
                case "6":
                    break;
            }
        }
    }
    public static void createCustomer(Scanner scanner,Customers customers) {
        System.out.println("First Name: ");
        String first_name = scanner.nextLine();
        System.out.println("Second Name: ");
        String second_name = scanner.nextLine();
        System.out.println("E-Mail: ");
        String email = scanner.nextLine();
        try {
            customers.writeData(first_name,second_name,email);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("The number input format was wrong");
        }
    }
    public static void createProduct(Scanner scanner,Products products) {
        System.out.println("Designation: ");
        String designation = scanner.nextLine();
        System.out.println("Price: ");
        String price = scanner.nextLine();
        try {
            products.writeData(designation, Double.parseDouble(price));
        } catch (NumberFormatException numberFormatException) {
            System.out.println("The number input format was wrong");
        }
    }
}