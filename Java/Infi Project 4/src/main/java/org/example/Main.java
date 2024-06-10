package org.example;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBWrapper dbWrapper = new DBWrapper();
        dbWrapper.intiDB();
        boolean running = true;
        while (running) {
            System.out.println("Choose mode: ");
            System.out.println("[1] Developer Mode [2] User Mode [3] Exit");
            char mode = takeInput(scanner, new char[] { '1', '2', '3' });
            switch (mode) {
                case '1' -> developerMode(scanner, dbWrapper, running);
                case '2' -> userMode(scanner, dbWrapper, running);
                case '3' -> running = false;
            }
        }
    }

    private static void userMode(Scanner scanner, DBWrapper dbWrapper, boolean running) {
        boolean credentialsSet = false;
        int customerId = 0;
        while (running) {
            System.out.println("[1] Borrow a Book [2] Return a Book [3] View all Books [4] Join Reading [5] View Borrowed Books [6] Log Out");
            char option = takeInput(scanner, new char[]{'1', '2', '3', '4', '5', '6'});
            if (option == '6') {
                running = false;
                break;
            }
            if (!credentialsSet) {
                System.out.println("Do you have a library card? [Y/N]");
                char hasCard = takeInput(scanner, new char[]{'Y', 'N'});
                if (hasCard == 'N' || dbWrapper.getEntries(new Customer()).isEmpty()) {
                    System.out.println("Enter your First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter your Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter your Email: ");
                    String email = scanner.nextLine();
                    Customer newCustomer = new Customer(firstName, lastName, email);
                    dbWrapper.insertTable(newCustomer);
                    customerId = newCustomer.getCustomerId();
                } else if (hasCard == 'Y'){
                    dbWrapper.viewTable(new Customer());
                    System.out.println("Enter your Customer ID: ");
                    customerId = Integer.parseInt(scanner.nextLine());
                }
                credentialsSet = true;
            }
            switch (option) {
                case '1' -> {
                    dbWrapper.viewTable(new Book());
                    System.out.println("Enter the ID of the book you want to borrow: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    dbWrapper.insertTable(new BorrowedBook((Book) dbWrapper.getEntriesById(new Book(),bookId), (Customer) dbWrapper.getEntriesById(new Customer(), customerId), Date.valueOf(LocalDate.now())));
                }
                case '2' -> {
                    getBorrowedBooks(dbWrapper, customerId);
                    System.out.println("Enter the ID of the book you want to return: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    dbWrapper.deleteEntry(new BorrowedBook((Book) dbWrapper.getEntriesById(new Book(),bookId),(Customer) dbWrapper.getEntriesById(new Customer(), customerId),null),0);
                }
                case '3' -> {
                    dbWrapper.viewTable(new Book());
                }
                case '4' -> {
                    Random random = new Random();
                    int bookId = random.nextInt(0, dbWrapper.getEntries(new Book()).size());
                    int workerId = random.nextInt(0, dbWrapper.getEntries(new LibraryWorker()).size());
                    LibraryWorker worker = (LibraryWorker) dbWrapper.getEntries(new LibraryWorker()).get(workerId);
                    System.out.println("Today's reading is brought to you by: " + worker.getFirstName() + " " + worker.getLastName());
                    Book.showId = false;
                    System.out.println("In today's reading, we have: " + dbWrapper.getEntries(new Book()).get(bookId).toString());
                    Book.showId = true;
                    ArrayList<Customer> customers = (ArrayList<Customer>) dbWrapper.getEntries(new Customer());
                    ArrayList<Customer> joiningCustomers = new ArrayList<>();
                    for (Customer customer : customers) {
                        if (random.nextBoolean()) {
                            joiningCustomers.add(customer);
                        }
                    }
                    System.out.println("Reading in progress:");
                    for (int i = 0; i < 5; i++) {
                        System.out.print('#');
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("\nReading complete.");
                    ArrayList <Integer> bookChances = new ArrayList<>();
                    for (int i = 0; i < joiningCustomers.size(); i++) {
                        bookChances.add(random.nextInt(0,101));
                    }
                    int highestChance = 0;
                    for (int i = 0; i < joiningCustomers.size(); i++) {
                        if (bookChances.get(i) > highestChance) {
                            highestChance = bookChances.get(i);
                        }
                    }
                    Customer winner = joiningCustomers.get(bookChances.indexOf(highestChance));
                    System.out.println("The winner of the book is: " + winner.getFirstName() + " " + winner.getLastName());
                    if (random.nextBoolean()) {
                        System.out.println("The winner "+ winner.getFirstName() +" " + winner.getLastName() + " has borrowed the book.");
                        dbWrapper.insertTable(new BorrowedBook((Book) dbWrapper.getEntriesById(new Book(),bookId), (Customer) dbWrapper.getEntriesById(new Customer(), winner.getCustomerId()), Date.valueOf(LocalDate.now())));
                    } else {
                        System.out.println("The winner "+ winner.getFirstName() +" " + winner.getLastName() + " has not borrowed the book.");
                    }
                }
                case '5' -> {
                    getBorrowedBooks(dbWrapper, customerId);
                }
            }
        }
    }

    public static void developerMode(Scanner scanner, DBWrapper dbWrapper, boolean running) {
        while (running) {
            JsonHandler jsonHandler = new JsonHandler(dbWrapper);
            System.out.println("[1] Add a table entry [2] Delete a table entry [3] Update a table entry [4] View all table entries [5] Export as JSON [6] Import from Json [7] Exit");
            char option = takeInput(scanner, new char[]{'1', '2', '3', '4', '5', '6', '7'});
            boolean creatingNew = option == '1';
            if (option == '7') {
                running = false;
                break;
            }
            System.out.println("Enter the Table Name: ");
            System.out.println("[1] Customer [2] Library Worker [3] Book [4] Borrowed Book");
            char table = takeInput(scanner, new char[]{'1', '2', '3', '4'});
            Object object = new ArrayList<>();
            object = switch (table) {
                case '1' -> {
                    if (!creatingNew) {
                        yield new Customer();
                    }
                    String firstName, lastName, email;
                    System.out.println("Enter First Name: ");
                    firstName = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    lastName = scanner.nextLine();
                    System.out.println("Enter Email: ");
                    email = scanner.nextLine();
                    yield new Customer(firstName, lastName, email);
                }
                case '2' -> {
                    if (!creatingNew) {
                        yield new LibraryWorker();
                    }
                    String firstName, lastName;
                    System.out.println("Enter First Name: ");
                    firstName = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    lastName = scanner.nextLine();
                    yield new LibraryWorker(firstName, lastName);
                }
                case '3' -> {
                    if (!creatingNew) {
                        yield new Book();
                    }
                    String title, author, publisher, publicationYear;
                    System.out.println("Enter Title: ");
                    title = scanner.nextLine();
                    System.out.println("Enter Author: ");
                    author = scanner.nextLine();
                    System.out.println("Enter Publisher: ");
                    publisher = scanner.nextLine();
                    System.out.println("Enter Publication Year: ");
                    publicationYear = scanner.nextLine();
                    yield new Book(title, author, Date.valueOf(publicationYear), publisher);
                }
                case '4' -> {
                    if (!creatingNew) {
                        yield new BorrowedBook();
                    }
                    int bookId, customerId;
                    dbWrapper.viewTable(new Book());
                    System.out.println("Enter Book ID: ");
                    bookId = Integer.parseInt(scanner.nextLine());
                    dbWrapper.viewTable(new Customer());
                    System.out.println("Enter Customer ID: ");
                    customerId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter Borrowed Date: ");
                    Date borrowedDate = Date.valueOf(scanner.nextLine());
                    yield new BorrowedBook((Book) dbWrapper.getEntriesById(new Book(), bookId), (Customer) dbWrapper.getEntriesById(new Customer(), customerId),borrowedDate);
                }
                default -> throw new IllegalStateException("Unexpected value: " + table);
            };
            switch (option) {
                case '1' -> {
                    dbWrapper.insertTable(object);
                }
                case '2' -> {
                    dbWrapper.viewTable(object);
                    System.out.println("Enter the ID of the entry you want to delete: ");
                    int id =  Integer.parseInt(scanner.nextLine());
                    dbWrapper.deleteEntry(object,id);
                }
                case '3' -> {
                    dbWrapper.viewTable(object);
                    System.out.println("Enter the ID of the entry you want to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    dbWrapper.updateEntry(object, id);
                }
                case '4' -> {
                    dbWrapper.viewTable(object);
                }
                case '5' -> {
                    System.out.println("Enter the file name: ");
                    String fileName = scanner.nextLine();
                    jsonHandler.exportToJson(fileName, dbWrapper.getEntries(object));
                }
                case '6' -> {
                    System.out.println("Enter the file name: ");
                    String fileName = scanner.nextLine();
                    jsonHandler.importFromJson(fileName);
                }
            }
        }
    }

    public static char takeInput(Scanner scanner, char[] validInputs) {
        String input = scanner.nextLine();
        char output = ' ';
        boolean valid = false;
        for (char validInput : validInputs) {
            char validInputUpper = (String.valueOf(validInput).toUpperCase()).charAt(0);
            if (validInputUpper == validInput) {
                output = (String.valueOf(input).toUpperCase()).charAt(0);
                valid = true;
                break;
            }
        }
        if (valid) {
            return output;
        }
        else {
            System.out.println("Invalid Input. Please try again.");
            return takeInput(scanner, validInputs);
        }
    }

    public static void getBorrowedBooks(DBWrapper dbWrapper, int customerId) {
        ArrayList<Object> tables = new ArrayList<>();
        tables.add(new BorrowedBook());
        tables.add((Customer) dbWrapper.getEntriesById(new Customer(), customerId));
        ArrayList<Object> result = (ArrayList<Object>) dbWrapper.joinEntries(tables);
        if (result == null) {
            System.out.println("No borrowed books found.");
        } else  {
            for (Object object : result) {
                if (object instanceof BorrowedBook) {
                    System.out.println(((BorrowedBook) object).getBook().toString());
                }
            }
        }
    }
}