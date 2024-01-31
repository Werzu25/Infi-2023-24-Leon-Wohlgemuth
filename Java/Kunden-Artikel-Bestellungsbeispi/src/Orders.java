import java.sql.SQLException;

public class Orders {
    public JDBConnector jdbConnector = null;
    int selectedItems = 0;

    public Orders(JDBConnector jdbConnector) {
        this.jdbConnector = jdbConnector;
    }

    public void insertDataByID(int customer_id, int product_id, int count) {
        try {
            jdbConnector.writeContent("insert into orders (customer_id, product_id, count) values (" + customer_id + "," + product_id + "," + count + ")");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void insertDataByName(int customerName, int productName, int count) {
        String[][] resultDataCustomers = null;
        String[][] resultDataProduct = null;
        String[] chosenCustomerData = null;
        int longestCustomerWord = 0;
        int longestProductWord = 0;
        try {
            resultDataCustomers = jdbConnector.getSelectContent("select * from customers where designation = '" + customerName + "';");
            resultDataProduct = jdbConnector.getSelectContent("select * from products where designation = '" + productName + "';");
            chosenCustomerData = new String[resultDataCustomers[0].length];
            if (resultDataCustomers.length > 2) {
                printData(resultDataCustomers, longestCustomerWord);
            }
            if (resultDataProduct.length > 2) {
                printData(resultDataCustomers, longestCustomerWord);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("The selected table is empty");
        }
    }

    public void showData() {
        String[][] resultData = null;
        int longestWord = 0;
        try {
            resultData = jdbConnector.getSelectContent("select * from orders");
            printData(resultData, longestWord);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            System.out.println("The selected table is empty");
        }
    }

    public void deleteTable() {
        try {
            jdbConnector.writeContent("delete from orders");
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
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
    }

    public void updateTable(int customer_id, int product_id, int count) {
        try {
            jdbConnector.writeContent("Update table customers set customer_id = " + customer_id + ", product_id = " + product_id + ", count = " + count + "where customer_id = " + customer_id + "& product_id = " + product_id);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }
}
