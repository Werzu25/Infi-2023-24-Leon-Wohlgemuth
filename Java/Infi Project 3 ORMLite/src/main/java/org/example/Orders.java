package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "orders")
public class Orders {

    @DatabaseField(columnName = "productID", canBeNull = false)
    private int productID;

    @DatabaseField(columnName = "customerID", canBeNull = false)
    private int customerID;

    @DatabaseField(columnName = "amount")
    private int amount;

    Orders() {

    }

    public Orders(int productID, int customerID, int amount) {
        this.productID = productID;
        this.customerID = customerID;
        this.amount = amount;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}