import java.sql.SQLException;

public class Customers {
    public JDBConnector jdbConnector = null;

    public Customers(JDBConnector jdbConnector) {
        this.jdbConnector = jdbConnector;
    }

    public void insertData(String name, String email) {
        try {
            jdbConnector.writeContent("insert into customers (name, email) values ('" + name + "','" + email + "')");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void showData() {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = jdbConnector.getSelectContent("select * from customers");
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
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("The selected table is empty");
        }
    }

    public void deleteTable() {
        try {
            jdbConnector.writeContent("delete from customers");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void updateTable(String name, String email, int customer_id) {
        try {
            jdbConnector.writeContent("Update customers set name = '" + name + "', email = '" + email + "' where customer_id = " + customer_id);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }
}
