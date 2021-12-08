package com.company;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        mysql.connectToMySQL();
        //slet indhold i tabel:
        //mysql.flushSQLTable("BankUsers_tbl");

        //opret ny user med unikt ID (og navn): BRUGES IKKE LÆNGERE DA SQL AUTO-INCREMENTER:
        //final int ID = mysql.findNextUID("BankUsers_tbl") + 1;
        final String name = "Line";
        final Double saldo = 22200.00;
        final int kontonummer = 123456;
        final String password = "1234";

        User user = new User(name, saldo, kontonummer, password);

        mysql.insertNewUserIntoSQL(user,"BankUsers_tbl");


        // System.out.println(mysql.countRowsInTable("BankUsers_tbl"));

        // System.out.println(User.countLinesInTxtFile("Persondata.txt"));

        User[] users = User.createUsersFromTxtFile("Persondata.txt");

/*        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i].getID());
        }*/

        mysql.insertNewUsersIntoSQL(users,"BankUsers_tbl");

        mysql.printSQLUsers("BankUsers_tbl");

        //System.out.println("Next available unique ID: " + (mysql.findNextUID("BankUsers_tbl") + 1));

        BankautomatInterface Bankinterface = new BankautomatInterface();
        Bankinterface.main(null);
/*        if (Bankinterface.getUser() != null) {
            System.out.println(Bankinterface.getUser().getName());
        }*/

    }
}
