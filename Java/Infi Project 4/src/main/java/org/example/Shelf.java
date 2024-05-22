package org.example;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "shelf")
public class Shelf {

    @DatabaseField(generatedId = true)
    private int shelfId;

    public Shelf () {
    }

}