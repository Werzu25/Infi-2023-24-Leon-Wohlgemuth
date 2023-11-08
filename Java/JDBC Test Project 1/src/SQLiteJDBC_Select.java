import java.sql.*;

public class SQLiteJDBC_Select {

    public static void main( String args[] ) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT count(value2) AS 'gerade' FROM zufallszahlen WHERE value2 = 0;" ); //gerade
            System.out.println(rs.getInt("gerade"));

            rs = stmt.executeQuery( "SELECT count(value2) AS 'ungerade' FROM zufallszahlen WHERE value2 = 1;" ); //ungerade
            System.out.println(rs.getInt("ungerade"));

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}