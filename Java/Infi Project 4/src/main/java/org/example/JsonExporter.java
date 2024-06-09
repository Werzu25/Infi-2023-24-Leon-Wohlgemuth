package org.example;

import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class JsonExporter {
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    DBWrapper dbWrapper;

    public JsonExporter(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    public void exportToJson(String fileName, Object object) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        if (object instanceof List<?> list) {
            if (list.getFirst() instanceof BorrowedBook) {
                for (BorrowedBook borrowedBook : (List<BorrowedBook>) list) {
                    jsonArray.put(borrowedBook.toJsonObject());
                }
            } else if (list.getFirst() instanceof Book) {
                for (Book book : (List<Book>) list) {
                    jsonArray.put(book.toJsonObject());
                }
            } else if (list.getFirst() instanceof Customer) {
                for (Customer customer : (List<Customer>) list) {
                    jsonArray.put(customer.toJsonObject());
                }
            } else if (list.getFirst() instanceof LibraryWorker) {
                for (LibraryWorker libraryWorker : (List<LibraryWorker>) list) {
                    jsonArray.put(libraryWorker.toJsonObject());
                }
            }
        }
        try {
            bufferedWriter.write(jsonArray.toString());
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
        JSONObject.stringToValue(JSONString.toString());
    }
}
