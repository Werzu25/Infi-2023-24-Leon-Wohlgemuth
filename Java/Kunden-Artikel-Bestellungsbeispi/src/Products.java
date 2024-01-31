import java.sql.SQLException;

public class Products {
    public JDBConnector jdbConnector = null;

    public Products(JDBConnector jdbConnector) {
        this.jdbConnector = jdbConnector;
    }

    public void insertData(String designation, double price) {
        try {
            jdbConnector.writeContent("insert into products (designation, price) values ('" + designation + "'," + price + ")");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void showData() {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = jdbConnector.getSelectContent("select * from products where title like '%"+useriput+"'%");
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

    /*
    public void deleteData(int) {

    }
     */
    public void deleteTable() {
        try {
            jdbConnector.writeContent("delete from products");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void updateTable(String designation, double price, int product_id) {
        try {
            jdbConnector.writeContent("Update table customers set designation = '" + designation + "', price = " + price + "where product_id = " + product_id);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }
}
