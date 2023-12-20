import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JDBConnector jdbConnector = new JDBConnector();
        Products products = new Products(jdbConnector);
        Customers customers = new Customers(jdbConnector);
        Orders orders  = new Orders(jdbConnector);
        jdbConnector.initializeDatabase();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("What do you want to do:");
            System.out.println("1. Create new entry");
            System.out.println("2. Delete entry");
            System.out.println("3. Show entries");
            System.out.println("4. Exit program");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("In which table do you to create the new entry:");
                    System.out.println("1. Products");
                    System.out.println("2. Customers");
                    System.out.println("3. Orders");
                    System.out.println("4. Go back");
                    String selectedCreateTable = scanner.nextLine();
                    switch (selectedCreateTable) {
                        case "1":
                            System.out.println("Designation: ");
                            String designation = scanner.nextLine();
                            System.out.println("Price: ");
                            double price = scanner.nextDouble();
                            scanner.reset();
                            products.insertData(designation,price);
                            break;
                        case "2":
                            System.out.println("Name: ");
                            String name = scanner.nextLine();
                            System.out.println("Email: ");
                            String email  = scanner.nextLine();
                            customers.insertData(name,email);
                            break;
                        case "3":
                            System.out.println("What do you want to do:");
                            System.out.println("1. Select by ID");
                            //System.out.println("2. Select by Name");
                            //System.out.println("3. Chose form Table");
                            System.out.println("4. Go back");
                            String insertMethod = scanner.nextLine();
                            switch (insertMethod) {
                                case "1":
                                    System.out.println("Customer ID: ");
                                    customers.showData();
                                    scanner.reset();
                                    int customerID = scanner.nextInt();
                                    scanner.reset();
                                    System.out.println("Product ID: ");
                                    products.showData();
                                    scanner.reset();
                                    int productID  = scanner.nextInt();
                                    scanner.reset();
                                    System.out.println("Count: ");
                                    int count  = scanner.nextInt();
                                    scanner.reset();
                                    orders.insertDataByID(customerID,productID,count);
                                    break;
                                case "2":
                                    break;
                                    /*
                                case "3":
                                    break;
                                case "4":
                                    break;
                                    */
                            }
                            break;
                        case "4":
                            break;
                    }
                    break;
                case "2":
                    System.out.println("In which table do you want to delete the entry:");
                    System.out.println("1. Products");
                    System.out.println("2. Customers");
                    System.out.println("3. Orders");
                    System.out.println("4. Go back");
                    String selectedDeleteTable = scanner.nextLine();
                    switch (selectedDeleteTable) {
                        case "1":
                            System.out.println("What do you want to delete:");
                            System.out.println("1. Entire table");
                            System.out.println("2. Single Row");
                            System.out.println("3. Go Back");
                            String selectedProductMethod = scanner.nextLine();
                            switch (selectedProductMethod) {
                                case "1":
                                    products.deleteTable();
                                    break;
                                case "2":
                                    break;
                                case "3":
                                    break;
                            }
                            break;
                        case "2":
                            System.out.println("What do you want to delete:");
                            System.out.println("1. Entire table");
                            System.out.println("2. Single Row");
                            System.out.println("3. Go Back");
                            String selectedCustomerMethod = scanner.nextLine();
                            switch (selectedCustomerMethod) {
                                case "1":
                                    customers.deleteTable();
                                    break;
                                case "2":
                                    break;
                                case "3":
                                    break;
                            }
                            break;
                        case "3":
                            System.out.println("What do you want to delete:");
                            System.out.println("1. Entire table");
                            System.out.println("2. Single Row");
                            System.out.println("3. Go Back");
                            String selectedOrderMethod = scanner.nextLine();
                            switch (selectedOrderMethod) {
                                case "1":
                                    orders.deleteTable();
                                    break;
                                case "2":
                                    break;
                                case "3":
                                    break;
                            }
                            break;
                        case "4":
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Which table do you want to show:");
                    System.out.println("1. Products");
                    System.out.println("2. Customers");
                    System.out.println("3. Orders");
                    System.out.println("4. Go back");
                    String selectedShowTable = scanner.nextLine();
                    switch (selectedShowTable) {
                        case "1":
                            products.showData();
                            break;
                        case "2":
                            customers.showData();
                            break;
                        case "3":
                            orders.showData();
                            break;
                        case "4":
                            break;
                    }
                    break;
                case "4":
                    isRunning = false;
                    break;
            }
            scanner.reset();
        }
    }
}