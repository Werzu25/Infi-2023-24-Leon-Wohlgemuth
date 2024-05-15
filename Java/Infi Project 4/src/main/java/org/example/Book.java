package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDateTime;

@DatabaseTable(tableName = "book")
public class Book {
    @DatabaseField(generatedId = true)
    private int bookId;

    @DatabaseField(columnName = "title", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "author", canBeNull = false)
    private String author;

    @DatabaseField(columnName = "publicationYear")
    private LocalDateTime publicationYear;

    @DatabaseField(columnName = "publisher")
    private String publisher;
}
