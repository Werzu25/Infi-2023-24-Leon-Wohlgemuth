import java.sql.*;

public class SQLiteJDBC {
    public static void main( String args[] ) {
        final String URL = "jdbc:sqlite:test.db";
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DROP TABLE IF EXISTS zufallszahlen";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE zufallszahlen" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " value   INT, " +
                    " value2  INT)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}