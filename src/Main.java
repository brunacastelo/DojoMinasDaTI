import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static String timeIsOpen = "10:00:00";
    private static String timeIsClose = "16:00:00";
    private static int quant = 0;

    public static void main(String[] args) {
        readFile();
    }

    private static void readFile() {
        try {
            FileReader arq = new FileReader("bank.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha != null) {
                countTimesOfBank(linha);

                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        System.out.println("Quantidade de pessoas que entraram no banco: " + quant);
    }

    public static int countTimesOfBank(String date) {
        LocalTime localTime = validaEntrada(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime bankOpen = LocalTime.parse(timeIsOpen, formatter);
        LocalTime bankClose = LocalTime.parse(timeIsClose, formatter);

        if (localTime.isAfter(bankOpen) && localTime.isBefore(bankClose)) {
            return ++quant;
        }
        return 0;
    }

    public static LocalTime validaEntrada(String date) {

        date = date.substring(date.indexOf("[") + 1);
        date = date.substring(0, date.indexOf("]"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalTime.parse(date, formatter);
    }
}
