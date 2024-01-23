import java.sql.*;
import java.util.Scanner;

public class JDBConnector {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ResultSetMetaData resultSetMetaData = null;
    private Scanner scanner = new Scanner(System.in);
    public JDBConnector() {
    }
    public void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Username: ");
            String user = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            System.out.println("Database: ");
            String database = scanner.nextLine();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException sqlException) {
            System.out.println("There was a error in your SQL Syntax"+sqlException);
            initializeDatabase();
        }
    }
    void writeContent(String sqlCommand) throws SQLException {
        try {
            statement.execute(sqlCommand);
        } catch (NullPointerException nullPointerException) {
            System.out.println("There was a problem connecting to the database");
            System.out.println("Please try again");
            initializeDatabase();
        }
    }
    public String [][] getSelectContent(String sqlCommand) throws SQLException, ArrayIndexOutOfBoundsException {
        resultSet = statement.executeQuery(sqlCommand);
        resultSetMetaData = resultSet.getMetaData();
        String[] columns = new String[resultSetMetaData.getColumnCount()];
        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
            columns[i] = resultSetMetaData.getColumnName(i+1);
        }
        resultSet.last();
        int rowCount = resultSet.getRow();
        if (rowCount == 1) rowCount++;
        String [][] resultTable = new String[rowCount][columns.length];
        resultSet.first();
        for (int i = 0; i < columns.length; i++) {
            resultTable[0][i] = columns[i];
        }
        for (int i = 1; resultSet.next(); i++) {
            for (int j = 0; j < columns.length; j++) {
                resultTable[i][j] = resultSet.getString(columns[j]);
            }
        }
        return resultTable;
    }
}
