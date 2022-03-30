package com.company;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        User user = mysql.getUserFromSQL("danielguldberg@gmail.com", "1234", "Customers");
        System.out.println(user.getEmail());

    }
}
