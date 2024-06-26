import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class Customers {
    private Connection connection = null;
    private Connector connector = null;
    public Customers (Connector connector) {
        this.connector = connector;
        connection = connector.getConnection();
    }
    public void writeData(String first_name,String second_name, String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (first-name,second_name,email) values ( ?, ?, ?)");
            preparedStatement.setString(1,first_name);
            preparedStatement.setString(2,second_name);
            preparedStatement.setString(3,email);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public void showData() {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = connector.getSelectContent("select * from customers");
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
    public void deleteEntry(int customerID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from customers where (customer_id = ?)");
            preparedStatement.setInt(1,customerID);
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
            resultData = connector.getSelectContent("select * from customers");
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
