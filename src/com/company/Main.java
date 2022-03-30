package com.company;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        mysql msql = mysql.getInstance();

        User user1 = msql.getUserFromSQL("danielguldberg@gmail.com", "1234", "Customers");
        System.out.println(user1.getEmail());

        User user2 = msql.getUserFromSQL("ragnar@vikings.dk", "4321", "Employees");
        System.out.println(user2.getEmail());

/*        //TODO gør generisk og flyt ud af main:
        if(user.getClass().getName().equalsIgnoreCase("com.company.Customer")) {
            System.out.println("This user is a Customer");
        } else {
            System.out.println("This user is an Employee");
        }*/
        System.out.println(user1.getName() + " is of user subclass type " + user1.getUserSubClass(user1));
        System.out.println(user2.getName() + " is of user subclass type " + user2.getUserSubClass(user2));

        msql.printSQLTable("Customers");
        msql.printSQLTable("Employees");

/*
        User user = mysql.getUserFromSQL("danielguldberg@gmail.com", "1234", "Customers");
        System.out.println(user.getEmail());*/

    }
}
