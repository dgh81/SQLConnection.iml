package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {

    public static void writeToFile(String filePath, int fraKontonummer, double amount, int tilKontonummer) throws IOException {

        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        //writer.format("");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        writer.println(dtf.format(now) + " " + fraKontonummer + " " + amount + " " + tilKontonummer);

        //PrintWriter pw = new PrintWriter(new FileOutputStream(new File("persons.txt"),true));
        writer.close();
    }

    public static int[] readFromFile() throws FileNotFoundException {
        // Find metode til at finde antal linjer:
        int[] linesFromFile = new int[10];
        File f = new File("c:\\temp\\test.txt");
        Scanner scanner = new Scanner(f); // brug stien/file-objektet i stedet for System.in
        int i = 0;
        while (scanner.hasNextInt()) {
            linesFromFile[i] = Integer.valueOf(scanner.nextLine());
            i++;
        }
        return linesFromFile;
    }

    public static void readDiffDataTypesFromTxtFile() throws FileNotFoundException {
        int[] i = new int[3];
        Double[] d = new Double[3];
        String[] s = new String[3];

        File f = new File("C:\\temp\\Persondata.txt");
        Scanner scanner = new Scanner(f);
        scanner.useLocale(Locale.US);

        for (int j = 0; j < 3; j++) {
            s[j] = scanner.next();
            d[j] = scanner.nextDouble();
            System.out.println(s[j]);
            System.out.println(d[j]);
        }
    }

}
