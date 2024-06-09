package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONObject;

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

    @Override
    public String toString() {
        return String.format("Book Id: %-15s  Title: %-15s Author: %-15s Publication Year: %-15s Publisher: %-15s", bookId, title, author, publicationYear, publisher);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookId", bookId);
        jsonObject.put("title", title);
        jsonObject.put("author", author);
        jsonObject.put("publicationYear", publicationYear);
        jsonObject.put("publisher", publisher);
        return jsonObject;
    }
}
