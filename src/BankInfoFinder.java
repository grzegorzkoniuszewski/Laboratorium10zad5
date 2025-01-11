import java.io.*;
import java.net.*;
import java.util.*;

public class BankInfoFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj trzy pierwsze cyfry numeru konta: ");
        String accountPrefix = scanner.nextLine();

        if (accountPrefix.length() != 3) {
            System.out.println("Wprowadź dokładnie trzy cyfry.");
            return;
        }

        try {
            String url = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";
            URL fileUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileUrl.openStream()));

            String line;
            boolean bankFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(accountPrefix)) {
                    String[] parts = line.split(" ");
                    String bankCode = parts[0];
                    String bankName = parts[1];

                    System.out.println("Numer konta należy do banku: " + bankName + " (Kod banku: " + bankCode + ")");
                    bankFound = true;
                    break;
                }
            }

            if (!bankFound) {
                System.out.println("Nie znaleziono banku dla podanych trzech cyfr.");
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Wystąpił błąd przy pobieraniu danych: " + e.getMessage());
        }
    }
}
