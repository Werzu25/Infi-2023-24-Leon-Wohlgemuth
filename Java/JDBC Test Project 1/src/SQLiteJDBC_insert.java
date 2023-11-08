import java.sql.*;
import java.util.Random;

public class SQLiteJDBC_insert {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        final String URL = "jdbc:sqlite:test.db";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = null;
            Random rdm = new Random();
            for (int i = 0; i < 20; i++) {
                int random = rdm.nextInt(1,11);
                int val2 = random%2;
                sql = "INSERT INTO zufallszahlen (value, value2)"+
                        "VALUES ("+ random + ", " + val2 + ")";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}