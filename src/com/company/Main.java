package com.company;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        mysql msql = mysql.getInstance();

        Employee user1 = new Employee("Karen Klippesen", "kk@klipmig.dk", "Klippevej 14 Hårstrup 4100", "11223344", "1111");

        user1 = msql.logUserIn(user1);

        //User user2 = msql.getUserFromSQL("ragnar@gmail.com", "4321", "Employees");

        System.out.println(user1.getName() + " is of user subclass type " + user1.getUserSubClass(user1));
        //System.out.println(user2.getName() + " is of user subclass type " + user2.getUserSubClass(user2));

        //Customer customer = new Customer("Test","Testesen@test.dk","65659845","testvej 1","test");


/*        Employee employee = new Employee("Test","Testesen@test.dk","65659845","testvej 1","test");
        msql.insertNewEmployeeIntoSQL(employee);*/

         //msql.deleteEmployee(user1, user1.getEmail());
        //msql.createNewUserIntoSQL(customer);
        //msql.editUser(customer, "testtest@test.test");
        //msql.deleteUser(customer);


        //msql.editEmployee("ragnar@vikings.dk", "ragnar@gmail.com");
        //msql.editCustomer("ff@fuglefie.dk", "ff@gmail.com");

        msql.printSQLTable("Customers");
        msql.printSQLTable("Employees");


    visual f = new visual();
    f.look();

    }
}
