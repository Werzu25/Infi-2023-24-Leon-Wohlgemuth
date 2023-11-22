import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        URL networkURL = null;
        Scanner scanner = null;
        try {
            networkURL = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
            scanner = new Scanner(networkURL.openStream());
        } catch (IOException e) {
            System.out.println("URL is wrong");
        }
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.contains("time")) {
                String datum = currentLine.substring(currentLine.indexOf("=")+2,currentLine.indexOf(">")-1);
                String[] datumEU = datum.split("-");
                System.out.println(datumEU[2]+"."+datumEU[1]+"."+datumEU[0]);
            }
            if (currentLine.contains("<Cube")) {
                while (!currentLine.contains("</Cube>") && !currentLine.contains("<Cube time") && !currentLine.contains("<Cube>")) {
                    System.out.println(currentLine);
                }
            }
        }
    }
}