package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
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
    private Date publicationYear;

    @DatabaseField(columnName = "publisher")
    private String publisher;

    public Book() {
    }

    public Book(String title, String author, Date publicationYear, String publisher) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }
}
