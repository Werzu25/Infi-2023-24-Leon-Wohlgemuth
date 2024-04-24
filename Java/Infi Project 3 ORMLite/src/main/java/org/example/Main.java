package org.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.Scanner;

public class Main {
    private final static String DATABASE_URL = "jdbc:mysql://localhost/infi_project1";

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        try {
            System.out.println("Password: ");
            String password = scanner.nextLine();
            System.out.println("Username: ");
            String username = scanner.nextLine();
            connectionSource = new JdbcConnectionSource(DATABASE_URL,username,password);
            Connector connector = new Connector(connectionSource);
            connector.setupDatabase(connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(running) {
            System.out.println("Select your action [table/action]: ");
            System.out.println("Actions x: [d] Delete Entry | [c] Create Entry | [u] Update Entry | [s] Show Entry | [e] Export as JSON | [j] Join Tables");
            System.out.println("Select the Table: ");
            System.out.println("1x Products");
            System.out.println("2x Customers");
            System.out.println("3x Orders");
            System.out.println("6 Exit");
        }
    }
}