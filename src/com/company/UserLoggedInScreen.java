package com.company;

import javax.swing.*;

public class UserLoggedInScreen extends JFrame {
    private JLabel nameLabel;
    private JLabel saldoLabel;
    //private User user;

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText(nameLabel);
    }

    public JLabel getSaldoLabel() {
        return saldoLabel;
    }

    public void setSaldoLabel(String saldoLabel) {
        this.saldoLabel.setText(saldoLabel);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel.setText(titleLabel);
    }

    private JLabel titleLabel;
    private JPanel myPanel;

    public UserLoggedInScreen(User user) {
        //System.out.println("test");
        UserLoggedInScreen myFrame = this; //new UserLoggedInScreen(user);
        myFrame.setContentPane(myPanel);
        //myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setSize(400,300);
        myFrame.setTitleLabel("User bank info");
        myFrame.setNameLabel("Name: " + user.getName());
        myFrame.setSaldoLabel("Saldo: " + user.getSaldo().toString());
        myFrame.setVisible(true);
    }


    public static void main(String[] args) {

    }

}
