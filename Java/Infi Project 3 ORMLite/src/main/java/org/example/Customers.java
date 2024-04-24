package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "customers")
public class Customers {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "e_Mail", canBeNull = false)
    private String eMAil;

    @DatabaseField(columnName = "firstName")
    private String firstName;

    @DatabaseField(columnName = "secondName")
    private String secondName;

    Customers() {

    }

    public Customers(String secondName, String firstName, String eMAil) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.eMAil = eMAil;
    }

    public int getId() {
        return id;
    }

    public String geteMAil() {
        return eMAil;
    }

    public void seteMAil(String eMAil) {
        this.eMAil = eMAil;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}