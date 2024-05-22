package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "customer")
public class Customer {

    @DatabaseField(generatedId = true)
    private int customerId;

    @DatabaseField(columnName = "first_name", canBeNull = false)
    private String firstName;

    @DatabaseField(columnName = "last_name", canBeNull = false)
    private String lastName;

    @DatabaseField(columnName = "email", canBeNull = false)
    private String email;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
