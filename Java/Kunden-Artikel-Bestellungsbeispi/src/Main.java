import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JDBConnector jdbConnector = new JDBConnector();
        Products products = new Products(jdbConnector);
        Customers customers = new Customers(jdbConnector);
        Orders orders = new Orders(jdbConnector);
        jdbConnector.initializeDatabase();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("What do you want to do:");
            System.out.println("1. Create new entry");
            System.out.println("2. Delete entry");
            System.out.println("3. Show entries");
            System.out.println("4. Update entries");
            System.out.println("5. Exit program");
            String option = scanner.nextLine();
            String email = "";
            String designation = "";
            String name = "";
            double price = 0;
            int customerID = 0;
            int count = 0;
            int productID = 0;
            switch (option) {
                case "1":
                    System.out.println("In which table do you to create the new entry:");
                    System.out.println("1. Products");
                    System.out.println("2. Customers");
                    System.out.println("3. Orders");
                    System.out.println("4. Go back");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            System.out.println("Designation: ");
                            designation = scanner.nextLine();
                            System.out.println("Price: ");
                            price = scanner.nextDouble();
                            products.insertData(designation, price);
                            break;
                        case "2":
                            System.out.println("Name: ");
                            name = scanner.nextLine();
                            System.out.println("Email: ");
                            email = scanner.nextLine();
                            customers.insertData(name, email);
                            break;
                        case "3":
                            System.out.println("What do you want to do:");
                            System.out.println("1. Select by ID");
                            //System.out.println("2. Select by Name");
                            //System.out.println("3. Chose form Table");
                            System.out.println("4. Go back");
                            option = scanner.nextLine();
                            switch (option) {
                                case "1":
                                    System.out.println("Customer ID: ");
                                    customers.showData();
                                    customerID = scanner.nextInt();
                                    System.out.println("Product ID: ");
                                    products.showData();
                                    productID = scanner.nextInt();
                                    System.out.println("Count: ");
                                    count = scanner.nextInt();
                                    orders.insertDataByID(customerID, productID, count);
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
                    option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            System.out.println("What do you want to delete:");
                            System.out.println("1. Entire table");
                            System.out.println("2. Single Row");
                            System.out.println("3. Go Back");
                            option = scanner.nextLine();
                            switch (option) {
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
                            option = scanner.nextLine();
                            switch (option) {
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
                            option = scanner.nextLine();
                            switch (option) {
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
                    option = scanner.nextLine();
                    switch (option) {
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
                    System.out.println("Which table do you want to Update: ");
                    System.out.println("1. Products");
                    System.out.println("2. Customers");
                    System.out.println("3. Orders");
                    System.out.println("4. Go back");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            products.showData();
                            System.out.println("Product ID: ");
                            productID = scanner.nextInt();
                            System.out.println("Designation: ");
                            designation = scanner.nextLine();
                            System.out.println("Price: ");
                            price = scanner.nextInt();
                            products.updateTable(designation, price, productID);
                            break;
                        case "2":
                            customers.showData();
                            System.out.println("Customer ID: ");
                            customerID = scanner.nextInt();
                            System.out.println("Name: ");
                            name = scanner.nextLine();
                            System.out.println("Email: ");
                            email = scanner.nextLine();
                            customers.updateTable(name, email, customerID);
                            break;
                        case "3":
                            orders.showData();
                            System.out.println("Product ID: ");
                            productID = scanner.nextInt();
                            System.out.println("Customer ID: ");
                            customerID = scanner.nextInt();
                            System.out.println("Count: ");
                            count = scanner.nextInt();
                            orders.updateTable(customerID, productID, count);
                            break;
                        case "4":
                            break;
                    }
                    break;
                case "5":
                    isRunning = false;
                    break;
            }
        }
    }
}