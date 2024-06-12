package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.sql.Date;

@DatabaseTable(tableName = "borrowedBooks")
public class BorrowedBook {

    @DatabaseField(generatedId = true)
    private int borrowId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "bookId", canBeNull = false, foreignAutoCreate = true)
    private Book book;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "customerId", canBeNull = false, foreignAutoCreate = true)
    private Customer customer;
    @DatabaseField(canBeNull = true, columnName = "borrowTime" )
    private Date borrowDate;

    public BorrowedBook() {
    }

    public BorrowedBook(Book book, Customer customer, Date borrowDate) {
        this.book = book;
        this.customer = customer;
        this.borrowDate = borrowDate;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("borrowId", borrowId);
        jsonObject.put("book", book.toJsonObject());
        jsonObject.put("customer", customer.toJsonObject());
        jsonObject.put("borrowDate", borrowDate);
        return jsonObject;
    }

    @Override
    public String toString() {
        return String.format("Borrow Id: %-15s Book: %-15s Customer: %-15s Borrow Date: %-15s", borrowId, book.getTitle(), customer.getFirstName() + " " + customer.getLastName(), borrowDate);
    }
}
