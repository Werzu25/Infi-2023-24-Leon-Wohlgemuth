import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Storage {
    private Connection connection = null;
    private Connector connector = null;
    public Storage (Connector connector) {
        this.connector = connector;
        connection = connector.getConnection();
    }
    public void writeData(int productID, int storageCount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storage (product_id,storageCount) values ( ?, ?)");
            preparedStatement.setInt(1,productID);
            preparedStatement.setInt(2,storageCount);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
    public void showData() {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = connector.getSelectContent("select * from storage");
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete from storage where (product_id = ?)");
            preparedStatement.setInt(1,productID);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("There was an error in the SQL syntax");
        }
    }
}
