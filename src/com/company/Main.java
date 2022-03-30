package com.company;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        /**
         * Find en måde at vise SQL tabellen på hjemmeside.
         * Opret transaktionshistorik som txt filer
         */

        //mysql.connectToMySQL();

        User user = mysql.getUserFromSQL("danielguldberg@gmail.com", "1234", "Customers");
        System.out.println(user.getEmail());
        //slet indhold i tabel:
        //mysql.flushSQLTable("BankUsers_tbl");

        //opret ny user med unikt ID (og navn): BRUGES IKKE LÆNGERE DA SQL AUTO-INCREMENTER:
        //final int ID = mysql.findNextUID("BankUsers_tbl") + 1;

        //sæt denne tilbage:
        /*        final String name = "Line";
        final Double saldo = 22200.00;
        final int kontonummer = 123;
        final String password = "123";

        User user = new User(name, saldo, kontonummer, password);

        mysql.insertNewUserIntoSQL(user,"BankUsers_tbl");*/


        // System.out.println(mysql.countRowsInTable("BankUsers_tbl"));

        // System.out.println(User.countLinesInTxtFile("Persondata.txt"));

        //sæt denne tilbage:
        //User[] users = User.createUsersFromTxtFile("Persondata.txt");

/*        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i].getID());
        }*/

        //sæt denne tilbage:
        //mysql.insertNewUsersIntoSQL(users,"BankUsers_tbl");

        // mysql.printSQLUsers("Customers");

        //System.out.println("Next available unique ID: " + (mysql.findNextUID("BankUsers_tbl") + 1));


        //NYT INTERFACE HER
        // BankautomatInterface Bankinterface = new BankautomatInterface();


        //Bankinterface.main(null);


    }
}
