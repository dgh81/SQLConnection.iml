package com.company;
/**
 * tilføj *.jar til projektet
 * under project structure -> modules -> projektnavn -> Dependencies -> tryk add og peg på jar filen
 * under Database til højre i Intellij -> add database som mysql
 * Højreklik connection navn til højre i Intellij -> Properties -> Advanced set både connection og driver til "requireSSL" til NO
 */
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;

//TRUNCATE TABLE BankUsers_tbl; tømmer tabellen BankUsers_tbl

public class mysql {

    public static Connection connectToMySQL() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            String url = "jdbc:mysql://mysql85.unoeuro.com:3306/danielguldberg_dk_db";  // danielguldberg_dk_db_bank2
            Connection connection = DriverManager.getConnection(url, "danielguldberg_dk", "280781");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int countRowsInTable(String tablename) {
        try {
            Connection connection = connectToMySQL();
            Statement statement = connection.createStatement();
            int counter = 0;
            ResultSet resultsetIDs = statement.executeQuery("select count(*) from " + tablename);
            while (resultsetIDs.next()) {
                counter = counter + resultsetIDs.getInt(1);
            }
            connection.close();
            return counter;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Fjern understående?
     * SQL auto ID klarer det.
     * @param tablename
     * @return
     */
    public static int findNextUID(String tablename) {
        try {
            Connection connection = connectToMySQL();
            Statement statement = connection.createStatement();
            int counter = 0;
            ResultSet resultsetIDs = statement.executeQuery("select * from " + tablename); //'ID' i stedet for * ?
            while (resultsetIDs.next()) {
                if (resultsetIDs.getInt(1) > counter) {
                    counter = resultsetIDs.getInt(1);
                }
            }
            connection.close();
            return counter;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static User getUserFromSQL(int kontonummer, String tablename) {
        User loggedInUser = new User();
        try {
            Connection connection = connectToMySQL();
            Statement statement = connection.createStatement();
            String sql = "";
            sql = "SELECT `ID`, `Navn`, `Saldo`, `Kontonummer`, `Password` FROM `" + tablename + "` WHERE Kontonummer=" + kontonummer;
            ResultSet getCustomer = statement.executeQuery(sql);

            while (getCustomer.next()) {
                loggedInUser.setName(getCustomer.getString(2));
                loggedInUser.setSaldo(getCustomer.getDouble(3));
                loggedInUser.setKontonummer(getCustomer.getInt(4));
                loggedInUser.setPassword(getCustomer.getString(5));
            }
            connection.close();
            return loggedInUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void insertNewUserIntoSQL(User user, String tablename) {
        try {
            Connection connection = connectToMySQL();
            // Parametre via variable:
            //PreparedStatement addCustomer = connection.prepareStatement("INSERT INTO BankUsers_tbl(ID,Navn,Saldo) VALUES ('" + user.getID() + "', '" + user.getName() + "', '" + user.getSaldo() + "')");
            PreparedStatement addCustomer = connection.prepareStatement("INSERT INTO BankUsers_tbl(Navn,Saldo,Kontonummer,Password) VALUES ('" + user.getName() + "', '" + user.getSaldo()+ "', '" + user.getKontonummer()+ "', '" + user.getPassword() + "')");
            // Send addCustomer til execution:
            addCustomer.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertNewUsersIntoSQL(User[] user, String tablename) {
        try {
            Connection connection = connectToMySQL();
            // Parametre via variable:
            for (int i = 0; i < user.length; i++) {
                String sql = "";
                sql = "INSERT INTO " + tablename + "(Navn,Saldo,Kontonummer,Password) VALUES ('" + user[i].getName() + "', '" + user[i].getSaldo()+ "', '" + user[i].getKontonummer()+ "', '" + user[i].getPassword() + "')";

                //PreparedStatement addCustomer = connection.prepareStatement("INSERT INTO BankUsers_tbl(ID,Navn,Saldo) VALUES ('" + user[i].getID() + "', '" + user[i].getName() + "', '" + user[i].getSaldo() + "')");
                PreparedStatement addCustomer = connection.prepareStatement(sql);
                // Send addCustomer til execution:
                addCustomer.executeUpdate();
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flushSQLTable(String tablename) {
        try {
            Connection connection = connectToMySQL();
            PreparedStatement posted = connection.prepareStatement("TRUNCATE TABLE BankUsers_tbl");
            posted.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printSQLUsers(String tablename) {
        try {
            Connection connection = connectToMySQL();
            Statement statement = connection.createStatement();

            ResultSet resultsetIDs = statement.executeQuery("select * from " + tablename);
            while (resultsetIDs.next()) {
                //resultsetIDs.getString(2);
                System.out.print("ID:" + resultsetIDs.getString(1) + " Name: " + resultsetIDs.getString(2) + " " + resultsetIDs.getString(3) + " " + resultsetIDs.getString(4) + " " + resultsetIDs.getString(5));
                System.out.println();
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserInSQL(User user) {
//        UPDATE table_name
//        SET column1 = value1, column2 = value2, ...
//        WHERE condition;
        try {
            Connection connection = connectToMySQL();
            String sql = "UPDATE BankUsers_tbl SET Saldo = " +user.getSaldo() + " WHERE Kontonummer = " + user.getKontonummer() ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap buildKontonummerToPasswordHashmap(String tablename) {
        HashMap<Integer, String> KontonummerToPasswordHashmap = new HashMap();
        try {
            Connection connection = connectToMySQL();
            Statement statement = connection.createStatement();
            ResultSet resultsetIDs = statement.executeQuery("select * from " + tablename);
            while (resultsetIDs.next()) {
                KontonummerToPasswordHashmap.put(Integer.valueOf(resultsetIDs.getInt(4)), resultsetIDs.getString(5));
            }
            connection.close();
            return KontonummerToPasswordHashmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean userAccountExists(int kontonummer){
        boolean result = false;
        try {
            Connection connection = connectToMySQL();
            Statement statement = connection.createStatement();

            ResultSet resultsetIDs = statement.executeQuery("select * from BankUsers_tbl WHERE Kontonummer='" + kontonummer + "'");
            while (resultsetIDs.next()) {
                if (Integer.valueOf(resultsetIDs.getInt("Kontonummer")) == kontonummer) {
                    connection.close();
                    return true;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }
}


