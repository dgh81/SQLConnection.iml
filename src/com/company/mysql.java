package com.company;
/**
 * tilføj *.jar til projektet
 * under project structure -> modules -> projektnavn -> Dependencies -> tryk add og peg på jar filen
 * under Database til højre i Intellij -> add database som mysql
 * Højreklik connection navn til højre i Intellij -> Properties -> Advanced set både connection og driver til "requireSSL" til NO
 */

import java.sql.*;

    //TODO sørg for at alle sql kald bruger PreparedStatement.

public class mysql {
    private static Connection connection;

    static {
        try {
            connection = connectToMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static volatile mysql instance;
    private mysql(){
    }

    public static mysql getInstance () {
        mysql result = instance;
        if (result == null) {
            synchronized (mysql.class) {
                result = instance;
                if (result == null) {
                    instance = result = new mysql();
                }
            }
        }
        return result;
    }

    //Skal denne være en Singleton klasse for sig selv?
    public static Connection connectToMySQL() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String url = "jdbc:mysql://mysql85.unoeuro.com:3306/danielguldberg_dk_db";  // danielguldberg_dk_db
            Connection connectionInternal = DriverManager.getConnection(url, "danielguldberg_dk", "280781");
            return connectionInternal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * FUNCTIONS
      */

    public <E> E logUserIn(E element) {
        User loggedInUser = (User) element;
        try {
            Statement statement = connection.createStatement();
            String sql = "";

            sql = "SELECT * FROM " + loggedInUser.getUserSubClass(loggedInUser) + "s" + " WHERE Email='"
                    + loggedInUser.getEmail() + "' AND Password='"
                    + loggedInUser.getPassword() + "'";
            ResultSet getUser = statement.executeQuery(sql);
            if (getUser != null) {
                System.out.println(loggedInUser.getEmail() + " loggede på :-)");
                //TODO Overvej at flytte til separat funktion CreateCustomerInSQL(String name, etc.)
                while (getUser.next()) {
                    loggedInUser.setName(getUser.getString(2));
                    loggedInUser.setEmail(getUser.getString(3));
                    loggedInUser.setPhone(getUser.getString(4));
                    loggedInUser.setAddress(getUser.getString(5));
                    loggedInUser.setPassword(getUser.getString(6));
                    //loggedInCustomer.setGender(getCustomer.getString(7));
                }
            }
            return (E) loggedInUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // TODO Husk error handling - fx ved duplicate email
    public <E> void createNewUserIntoSQL(E element) {
        try {
            User user = (User) element;
            PreparedStatement addUser = connection.
                    prepareStatement("INSERT INTO " + user.getUserSubClass(user) + "s" + " (Name,Email,Phone,Address,Password) VALUES ('"
                            + user.getName()
                            + "', '" + user.getEmail()
                            + "', '" + user.getPhone()
                            + "', '" + user.getAddress()
                            + "', '" + user.getPassword() + "')");
            addUser.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public <E> void deleteUser(E element) {
        try {
            User user = (User) element;
            PreparedStatement deleteCustomer = connection.
                    prepareStatement("DELETE FROM " + user.getUserSubClass(user) + "s " + " WHERE Email = '" + user.getEmail() + "'");
            deleteCustomer.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public <E> void editUser(E element, String newEmail) {
        try {
            User user = (User) element;
            PreparedStatement editEmployee = connection.
                    prepareStatement("UPDATE " + user.getUserSubClass(user) + "s " + "SET email = '" + newEmail + "' WHERE Email = '" + user.getEmail() + "'");
            editEmployee.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void flushSQLTable(String tablename) {
        try {
            PreparedStatement posted = connection.prepareStatement("TRUNCATE TABLE" + tablename);
            posted.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printSQLTable(String tablename) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultsetIDs = statement.executeQuery("SELECT * FROM " + tablename);
            while (resultsetIDs.next()) {
                System.out.print("ID:" + resultsetIDs.getString(1) + " "
                        + resultsetIDs.getString(2) + " "
                        + resultsetIDs.getString(3) + " "
                        + resultsetIDs.getString(4) + " "
                        + resultsetIDs.getString(5) + " "
                        + resultsetIDs.getString(6));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printOpeningHoursTable(String tablename) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultsetIDs = statement.executeQuery("SELECT * FROM " + tablename + " ORDER BY dayIndex");
            while (resultsetIDs.next()) {
                System.out.print("ID:" + resultsetIDs.getString(1) + " "
                        + resultsetIDs.getString(2) + " "
                        + resultsetIDs.getString(3));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}