package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBWrapper dbWrapper = new DBWrapper();
        dbWrapper.intiDB();
        boolean running = true;
        while (running) {
            System.out.println("Choose an option: ");
            System.out.println("[1] Add a table entry [2] Delete a table entry [3] Update a table entry [4] View all table entries [5] Exit");
            char option = takeInput(scanner, new char[] { '1', '2', '3', '4', '5' });
            System.out.println("Enter the Table Name: ");
            System.out.println("[1] Customer [2] Shelf [3] Book");
            char table = takeInput(scanner, new char[] { '1', '2', '3' });
            Object object = new ArrayList<>();
            object = switch (table) {
                case '1' -> {
                    System.out.println("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter Email: ");
                    String email = scanner.nextLine();
                    yield new Customer(firstName, lastName, email);
                }
                case '2' -> {
                    yield new Shelf();
                }
                case '3' -> {
                    yield new Book();
                }
                default -> throw new IllegalStateException("Unexpected value: " + table);
            };
            switch (option) {
                case '1' -> {
                    dbWrapper.insertTable(object);
                }
                case '2' -> {
                    dbWrapper.deleteTable(object);
                }
                case '3' -> {
                    dbWrapper.updateTable(object);
                }
                case '4' -> {
                    dbWrapper.viewTable(object);
                }
                case '5' -> {
                    running = false;
                }
            }
        }
    }
    public static char takeInput(Scanner scanner, char[] validInputs) {
        String input = scanner.nextLine();
        char output = ' ';
        for (int i = 0; i < validInputs.length; i++) {
            if (input.charAt(0) == validInputs[i]) {
                output = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid Input");
                output = takeInput(scanner, validInputs);
                break;
            }
        }
        return output;
    }
}