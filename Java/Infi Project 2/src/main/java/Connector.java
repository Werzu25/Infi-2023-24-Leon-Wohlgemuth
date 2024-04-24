import java.sql.*;
import java.util.Scanner;

public class Connector {
    private  Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ResultSetMetaData resultSetMetaData = null;
    private Scanner scanner = new Scanner(System.in);

    public void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Username: ");
            String user = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();V
            connection = DriverManager.getConnection("jdbc:mysql://localhost/infi_project1", user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException sqlException) {
            System.out.println("There was a error in your SQL Syntax" + sqlException);
            initializeDatabase();
        }
    }
    void writeContent(String sqlCommand) throws SQLException,NullPointerException {
        connection.prepareStatement(sqlCommand);

    }
    public String[][] getSelectContent(String sqlCommand) throws SQLException, ArrayIndexOutOfBoundsException {
        resultSet = statement.executeQuery(sqlCommand);
        resultSetMetaData = resultSet.getMetaData();
        String[] columns = new String[resultSetMetaData.getColumnCount()];
        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
            columns[i] = resultSetMetaData.getColumnName(i + 1);
        }
        resultSet.last();
        int rowCount = resultSet.getRow()+1;
        String[][] resultTable = new String[rowCount][columns.length];
        resultSet.first();
        for (int i = 0; i < columns.length; i++) {
            resultTable[0][i] = columns[i];
        }
        int row = 1;
        do {
            for (int j = 0; j < columns.length; j++) {
                resultTable[row][j] = resultSet.getString(columns[j]);
            }
            row++;
        } while ((resultSet.next()));
        return resultTable;
    }

    public Connection getConnection() {
        return connection;
    }
}
