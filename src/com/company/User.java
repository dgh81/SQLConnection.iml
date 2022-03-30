package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class User {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    public User() {}
    public User(String name, String email, String phone, String address, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getUserSubClass(User user) {
        //TODO gør generisk og flyt ud af main:
        if(user.getClass().getName().equalsIgnoreCase("com.company.Customer")) {
            //System.out.println("This user is a Customer");
            return "customer";
        } else {
            //System.out.println("This user is an Employee");
            return "employee";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*public static User[] createUsersFromTxtFile(String filepath) throws FileNotFoundException {
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
    }*/

    /*public static int countLinesInTxtFile(String filepath) throws FileNotFoundException {
        int result = 0;
        File f = new File(filepath);
        Scanner scanner = new Scanner(f);
        scanner.useLocale(Locale.US);
        while (scanner.hasNextLine()) {
            result++;
            scanner.nextLine();
        }
        return result;
    }*/
}
