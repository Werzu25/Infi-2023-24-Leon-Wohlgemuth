package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
}