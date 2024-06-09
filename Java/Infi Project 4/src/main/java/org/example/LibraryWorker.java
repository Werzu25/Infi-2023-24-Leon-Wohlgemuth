package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONObject;

@DatabaseTable(tableName = "libraryWorker")
public class LibraryWorker {

    @DatabaseField(generatedId = true)
    private int workerId;

    @DatabaseField(columnName = "first_name", canBeNull = false)
    private String firstName;

    @DatabaseField(columnName = "last_name", canBeNull = false)
    private String lastName;


    public LibraryWorker() {

    }

    public LibraryWorker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Worker Id: %-15s First Name: %-15s Last Name: %-15s", workerId, firstName , lastName);
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workerId", workerId);
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        return jsonObject;
    }
}