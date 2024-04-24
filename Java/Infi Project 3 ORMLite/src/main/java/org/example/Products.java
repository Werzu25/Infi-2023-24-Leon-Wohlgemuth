package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "products")
public class Products {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "description", canBeNull = false)
    private String description;

    @DatabaseField(columnName = "price")
    private double price;

    Products() {

    }

    public Products(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}