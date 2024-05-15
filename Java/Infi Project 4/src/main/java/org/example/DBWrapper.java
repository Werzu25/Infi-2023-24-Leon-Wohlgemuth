package org.example;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.Scanner;

public class DBWrapper {
    private static String DATABASE_URL = "jdbc:mysql://localhost/";
    private Dao<Customer,Integer> customerDao;
    private Dao<Shelf,Integer> shelfDao;
    private Dao<Book,Integer> bookDao;


    public void intiDB() {
        ConnectionSource connectionSource = null;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        try {
            System.out.println("Database Name: ");
            String dbName = scanner.nextLine();
            DATABASE_URL += dbName;
            System.out.println("Password: ");
            String password = scanner.nextLine();
            System.out.println("Username: ");
            String username = scanner.nextLine();
            connectionSource = new JdbcConnectionSource(DATABASE_URL,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setupDatabase(ConnectionSource connectionSource) throws Exception {
        customerDao = DaoManager.createDao(connectionSource, Customer.class);
        shelfDao = DaoManager.createDao(connectionSource, Shelf.class);
        bookDao = DaoManager.createDao(connectionSource, Book.class);

        TableUtils.createTableIfNotExists(connectionSource, Customer.class);
        TableUtils.createTableIfNotExists(connectionSource, Shelf.class);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);
    }
}
