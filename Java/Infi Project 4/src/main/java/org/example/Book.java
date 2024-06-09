package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONObject;

import java.sql.Date;

@DatabaseTable(tableName = "book")
public class Book {
    @DatabaseField(generatedId = true)
    private int bookId;

    @DatabaseField(columnName = "title", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "author", canBeNull = false)
    private String author;

    @DatabaseField(columnName = "publicationDate")
    private Date publicationDate;

    @DatabaseField(columnName = "publisher")
    private String publisher;

    static boolean showId = true;

    public Book() {
    }

    public Book(String title, String author, Date publicationDate, String publisher) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        if (showId) {
            return String.format("Book Id: %-15s  Title: %-15s Author: %-15s Publication Year: %-15s Publisher: %-15s", bookId, title, author, publicationDate, publisher);
        }
        else {
            return String.format("Title: %-15s Author: %-15s Publication Year: %-15s Publisher: %-15s", title, author, publicationDate, publisher);
        }
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
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
        jsonObject.put("publicationDate", publicationDate);
        jsonObject.put("publisher", publisher);
        return jsonObject;
    }
}
