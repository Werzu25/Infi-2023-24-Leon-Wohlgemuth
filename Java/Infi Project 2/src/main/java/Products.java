import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class Products {
    private Connection connection = null;
    private Connector connector = null;
    public Products (Connector connector) {
        this.connector = connector;
        connection = connector.getConnection();
    }
    public void writeData(String designation, double price) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (designation, price) VALUES (?, ?)");
            preparedStatement.setString(1,designation);
            preparedStatement.setDouble(2,price);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public void showData(String sql) {
        int longestWord = 0;
        try {
            String[][] resultData = connector.getSelectContent(sql);
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
    public void deleteEntry(int productID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from products where (product_id = ?)");
            preparedStatement.setInt(1,productID);
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
            resultData = connector.getSelectContent("select * from products");
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

}
