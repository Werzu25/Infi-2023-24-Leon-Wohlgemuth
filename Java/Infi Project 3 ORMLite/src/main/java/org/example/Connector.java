package org.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Connector {

    private Dao<Customers, Integer> customerDao;
    private Dao<Products, Integer> productDao;
    private Dao<Orders, Integer> orderDao;

    public Connector (ConnectionSource connectionSource) {

    }

    public void writeCustomer(String firstName, String secondName, String eMail) throws SQLException {
        Customers customers = new Customers(secondName,firstName,eMail);
        customerDao.create(customers);
    }

    public void writeProduct(String description, double price) throws SQLException {
        Products product = new Products(description,price);
        productDao.create(product);
    }

    public void writeOrder(int productID, int customerID, int amount) throws SQLException {
        Orders orders = new Orders(productID,customerID,amount);
        orderDao.create(orders);
        orderDao.updateId(orders,1);
    }

    public void deleteCustomer(int customerID) throws SQLException {
        customerDao.deleteById(customerID);
    }

    public void deleteProduct(int productID) throws SQLException {
        productDao.deleteById(productID);
    }

    public void deleteOrder(int orderID) throws SQLException {
        orderDao.deleteById(orderID);
    }

    public void updateCustomer(int customerID,String secondName, String firstName, String email) throws SQLException {
        Customers customer = new Customers(secondName,firstName,email);
        customerDao.updateId(customer,customerID);
    }

    public void updateProduct(int productID,String description, double price) throws SQLException {
        Products product = new Products(description,price);
        productDao.updateId(product,productID);
    }

    public void updateOrder(int orderID, int productID, int customerID, int amount) throws SQLException {
        Orders order = new Orders(productID,customerID,amount);
        orderDao.updateId(order,orderID);
    }

    public void setupDatabase(ConnectionSource connectionSource) throws Exception {
        customerDao = DaoManager.createDao(connectionSource, Customers.class);
        orderDao = DaoManager.createDao(connectionSource,Orders.class);
        productDao = DaoManager.createDao(connectionSource, Products.class);

        TableUtils.createTableIfNotExists(connectionSource, Customers.class);
        TableUtils.createTableIfNotExists(connectionSource, Orders.class);
        TableUtils.createTableIfNotExists(connectionSource, Products.class);
    }
}
