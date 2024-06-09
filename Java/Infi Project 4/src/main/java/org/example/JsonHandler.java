package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.util.List;

public class JsonHandler {
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    DBWrapper dbWrapper;

    public JsonHandler(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    public void exportToJson(String fileName, Object object) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if (object instanceof List<?> list) {
            if (list.getFirst() instanceof BorrowedBook) {
                jsonObject.put("type", "BorrowedBook");
                for (BorrowedBook borrowedBook : (List<BorrowedBook>) list) {
                    jsonArray.put(borrowedBook.toJsonObject());
                }
            } else if (list.getFirst() instanceof Book) {
                jsonObject.put("type", "Book");
                for (Book book : (List<Book>) list) {
                    jsonArray.put(book.toJsonObject());
                }
            } else if (list.getFirst() instanceof Customer) {
                jsonObject.put("type", "Customer");
                for (Customer customer : (List<Customer>) list) {
                    jsonArray.put(customer.toJsonObject());
                }
            } else if (list.getFirst() instanceof LibraryWorker) {
                jsonObject.put("type", "LibraryWorker");
                for (LibraryWorker libraryWorker : (List<LibraryWorker>) list) {
                    jsonArray.put(libraryWorker.toJsonObject());
                }
            }
        }
        try {
            jsonObject.put("data", jsonArray);
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void importFromJson(String fileName) {
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder JSONString = new StringBuilder();
        try {
            while (bufferedReader.ready()) {
                JSONString.append(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(JSONString.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        switch (jsonObject.get("type").toString()) {
            case "BorrowedBook":
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject borrowedBookJson = jsonArray.getJSONObject(i);
                    BorrowedBook borrowedBook = new BorrowedBook();
                    borrowedBook.setBook((Book) dbWrapper.getEntriesById(Book.class, borrowedBookJson.getJSONObject("book").getInt("bookId")));
                    borrowedBook.setCustomer((Customer) dbWrapper.getEntriesById(Customer.class, borrowedBookJson.getJSONObject("customer").getInt("customerId")));
                    borrowedBook.setBorrowDate(Date.valueOf(borrowedBookJson.getString("borrowDate")));
                    dbWrapper.insertTable(borrowedBook);
                }
                break;
            case "Book":
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject bookJson = jsonArray.getJSONObject(i);
                    Book book = new Book();
                    book.setAuthor(bookJson.getString("author"));
                    book.setTitle(bookJson.getString("title"));
                    book.setPublicationDate(Date.valueOf(bookJson.getString("publicationDate")));
                    dbWrapper.insertTable(book);
                }
                break;
            case "Customer":
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject customerJson = jsonArray.getJSONObject(i);
                    Customer customer = new Customer();
                    customer.setFirstName(customerJson.getString("firstName"));
                    customer.setLastName(customerJson.getString("lastName"));
                    dbWrapper.insertTable(customer);
                }
                break;
            case "LibraryWorker":
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject libraryWorkerJson = jsonArray.getJSONObject(i);
                    LibraryWorker libraryWorker = new LibraryWorker();
                    libraryWorker.setFirstName(libraryWorkerJson.getString("firstName"));
                    libraryWorker.setLastName(libraryWorkerJson.getString("lastName"));
                    dbWrapper.insertTable(libraryWorker);
                }
                break;
        }
    }
}
