package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class User {
    private String name;
    private Double saldo;
    private int kontonummer;
    private String password;

    public int getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(int kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}
    public User(String name, Double saldo, int kontonummer, String password) {
        this.name = name;
        this.saldo = saldo;
        this.kontonummer = kontonummer;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public static User[] createUsersFromTxtFile(String filepath) throws FileNotFoundException {
        int numberOfUsersInFile = countLinesInTxtFile(filepath);

        User[] users = new User[numberOfUsersInFile];

        File f = new File(filepath);
        Scanner scanner = new Scanner(f);
        scanner.useLocale(Locale.US);

        for (int i = 0; i < numberOfUsersInFile; i++) {
            users[i] = new User();
            users[i].setName(scanner.next());
            users[i].setSaldo(scanner.nextDouble());
            users[i].setKontonummer(scanner.nextInt());
            users[i].setPassword(scanner.next());
            //users[i].setID(mysql.findNextUID("BankUsers_tbl")+1+i);

        }
        return users;
    }

    public static int countLinesInTxtFile(String filepath) throws FileNotFoundException {
        int result = 0;
        File f = new File(filepath);
        Scanner scanner = new Scanner(f);
        scanner.useLocale(Locale.US);
        while (scanner.hasNextLine()) {
            result++;
            scanner.nextLine();
        }
        return result;
    }
}
