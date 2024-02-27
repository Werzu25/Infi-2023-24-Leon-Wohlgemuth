import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import java.io.FileReader;

public class Orders {
    private Connection connection = null;
    private Connector connector = null;
    public Orders (Connector connector) {
        this.connector = connector;
        connection = connector.getConnection();
    }
    public void writeData(int customer_id, int order_id, int count) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (customer_id,order_id,count) values (?, ?, ?)");
            preparedStatement.setInt(1,customer_id);
            preparedStatement.setInt(2,order_id);
            preparedStatement.setInt(3,count);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public void showData() {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = connector.getSelectContent("select * from orders");
            printData(resultData, longestWord);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("The selected table is empty");
        }
    }
    public void showDataById(int customerID, int productID) {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = connector.getSelectContent("");
            printData(resultData, longestWord);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("The selected table is empty");
        }
    }
    public void printData(String[][] resultData, int longestWord) {
        for (int i = 0; i < resultData.length; i++) {
            for (int j = 0; j < resultData[i].length; j++) {
                if (resultData[i][j].length() > longestWord) {
                    longestWord = resultData[i][j].length();
                }
            }
        }
        for (int i = 0; i < resultData.length; i++) {
            for (int j = 0; j < resultData[i].length; j++) {
                System.out.printf("%" + (longestWord + 5) + "s", resultData[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void deleteEntry(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where product_id = ? or customer_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public void createJson() {
        JSONObject jsonObject = new JSONObject();
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = connector.getSelectContent("select * from orders");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("The selected table is empty");
        }
        for (int i = 0; i < resultData.length; i++) {
            for (int j = 0; j < resultData[i].length; j++) {
                jsonObject.put(resultData[i],resultData[j]);
            }
        }
        try {
            FileWriter file = new FileWriter("./table.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeJson() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./table.json");
            int item;
            while ((item = fileReader.read()) != -1) {
                System.out.print((char)item);
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}
