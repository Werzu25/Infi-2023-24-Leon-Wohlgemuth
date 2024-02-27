import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chosenJoin = scanner.nextLine();
        ConnectorWrapper connectorWrapper = new ConnectorWrapper();
        System.out.println("[1] Inner join [2] Right Join [3] Left Join [4] Cross Join");
        switch (chosenJoin) {
            case "1":
                connectorWrapper.selectData("select * from tab1 join person p on tab1.id = p.idtab1 join tab2 t on p.idtab2 = t.id;");
                break;
            case "2":
                connectorWrapper.selectData("select * from tab1 right join person p on tab1.id = p.idtab1 right join tab2 t on p.idtab2 = t.id;");
                break;
            case "3":
                connectorWrapper.selectData("select * from tab1 left join person p on tab1.id = p.idtab1 left join tab2 t on p.idtab2 = t.id;");
                break;
            case "4":
                connectorWrapper.selectData("select * from tab1 cross join person cross join tab2;");
                break;
        }
    }
}