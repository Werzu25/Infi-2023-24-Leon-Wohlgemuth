import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        MySQLAccess mySQLAccess = new MySQLAccess();
        int odd = 0;
        int even = 0;
        mySQLAccess.initializeDatabase();
        for (int i = 0; i < 20; i++) {
            int randomNumber = random.nextInt(0,11);
            mySQLAccess.writeContent("insert into numbers (value, value2) VALUES ("+randomNumber+","+randomNumber%2+");");
        }
    }
}