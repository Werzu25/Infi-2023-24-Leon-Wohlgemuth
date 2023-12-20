import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.Random;

public class randomNumber
{
    public static void main(String args[])
    {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        Random rdm = new Random();

        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Silva\\Documents\\Anichstraße\\INFI-Informationssysteme\\sqlite\\numbers.db");

            //Create table
            st = c.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS randomNumbers;");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS randomNumbers "+
                    "(id INTEGER PRIMARY KEY autoincrement," +
                    "value int," +
                    "value2 int);");
            //Insert records
            for (int i = 0; i < 20; i++)
            {
                int rdmNumber = rdm.nextInt(10);
                st.executeUpdate("INSERT INTO randomNumbers (value, value2)" +
                        "VALUES( "+rdmNumber+", "+(rdmNumber%2)+");");
            }
            //select statement
            rs = st.executeQuery("SELECT count(id) AS 'even number' FROM randomNumbers WHERE value2 = 0;");
            System.out.println("Anzahl gerader Zahlen: "+rs.getInt("even number"));

            rs = st.executeQuery("SELECT count(id) AS 'odd number' FROM randomNumbers WHERE value2 = 1;");
            System.out.println("Anzahl ungerader Zahlen: "+rs.getInt("odd number"));

            rs.close();
            st.close();
            c.close();
        }
        catch (Exception e)
        {
            System.out.println("An Error was thrown:");
            System.out.println(e.getMessage());
        }
    }
}
