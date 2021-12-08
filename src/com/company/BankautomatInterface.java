package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

/**
 * Alt form skal laves til kode...
 * Layered panes virker ikke i intellij, så find måde at kode det fra bunden uden IDE...
 *
 */

public class BankautomatInterface extends JFrame {
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton loginButton;
    private JPanel myPanel;
    //private JFrame frame;



    private User user = null;
    
    HashMap hashmapKontonummerToPassword = mysql.buildKontonummerToPasswordHashmap("BankUsers_tbl");

    public BankautomatInterface() {


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lav msgbox:
                // JOptionPane.showMessageDialog(null,"test: " + e.getActionCommand());
                // JOptionPane.showMessageDialog(null,"test: jeg virker !");
                // try login:
                user = Login();
                if (user != null) {
                    JOptionPane.showMessageDialog(null,"Login succesfuld");

                    /**
                     * Forsøg på at lukke BankautomatInterface bag os? Intet herunder virker:
                     */
                    // BankautomatInterface.super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    // BankautomatInterface.super.rootPane.dispatchEvent(new WindowEvent(BankautomatInterface.getWindows()[0], WindowEvent.WINDOW_CLOSING));
                    // BankautomatInterface.super.dispose();
                    //System.out.println(super.toString());

                    // Virker KUN når kode køres fra BankautomatInterface.main ???????:
                    BankautomatInterface.super.getWindows()[0].dispose();
                    BankautomatInterface.getWindows()[0].dispose();
                    BankautomatInterface.super.dispose();

                    //frame.dispose();

                    // Lukker ALLE vinduer :(
                    //System.exit(0);


                    // Kald form UserLoggedInScreen:
                    UserLoggedInScreen myScreen = new UserLoggedInScreen(user);


                } // else try again
            }
        });
    }
    public User Login() {

        // passwordField2.getPassword() er kontonummer
        // passwordField1.getPassword() er password

        // Konverter kontonummer fra char til String:
        char[] charKontonummer = passwordField2.getPassword();
        String kontonummer = "";
        for (int j = 0; j < charKontonummer.length; j++) {
            kontonummer = kontonummer + charKontonummer[j];
        }

        // Konverter password fra char til String:
        char[] charPassword = passwordField1.getPassword();
        String password = "";
        for (int j = 0; j < charPassword.length; j++) {
            password = password + charPassword[j];
        }

        // Konverter password fra String til Integer og tjek:
        //System.out.println(hm.get(Integer.parseInt(kontonummer)));
        // Tjek kontonummer i hm:
        //System.out.println(passwordField1.getPassword());

        if (hashmapKontonummerToPassword.get(Integer.parseInt(kontonummer)).toString().equals(password)) {
            System.out.println("Succes!");
            User loggedInUser = mysql.getUserFromSQL(Integer.parseInt(kontonummer),"BankUsers_tbl");
            System.out.println("User: \"" + loggedInUser.getName() + "\" is now logged in.");
            return loggedInUser;
        } else {
            System.out.println("Fejl i password! Prøv igen.");
        }

        return null;
    }

    public static void main(String[] args) {
        JFrame myFrame = new JFrame("BankAutomat");
        myFrame.setContentPane(new BankautomatInterface().myPanel);
        //myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setSize(400,300);
        myFrame.setVisible(true);

        //myFrame.getContentPane().getSize();
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public void setPasswordField2(JPasswordField passwordField2) {
        this.passwordField2 = passwordField2;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
