package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UserLoggedInScreen extends JFrame {
    private JLabel NameLabel;
    private JLabel SaldoLabel;
    //private User user;

    public JLabel getNameLabel() {
        return NameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.NameLabel.setText(nameLabel);
    }

    public JLabel getSaldoLabel() {
        return SaldoLabel;
    }

    public void setSaldoLabel(String saldoLabel) {
        this.SaldoLabel.setText(saldoLabel);
    }

    public JLabel getTitleLabel() {
        return TitleLabel;
    }

    public void setTitleLabel(String titleLabel) {
        this.TitleLabel.setText(titleLabel);
    }

    private JLabel TitleLabel;
    private JPanel myPanel;
    private JButton WithdrawButton;
    private JButton TransferButton;
    private JButton ExitButton;
    private JButton WithdrawAllButton;
    private JTextField WithdrawAmountTxtField;
    private JButton OKButton;
    private JTextField TransferToAccounterNumberTxtField;
    private JButton OKTransferButton;
    private JTextField TransferAmountTxtField;
    private JLabel TransferAmountLabel;
    private JLabel WithdrawAmountLabel;
    private JLabel ToAccountNumberLabel;

    public UserLoggedInScreen(User user) {
        //System.out.println("test");
        UserLoggedInScreen myFrame = this; //new UserLoggedInScreen(user);
        myFrame.setContentPane(myPanel);
        //myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setSize(600,300);
        myFrame.setTitleLabel("User bank info");
        myFrame.setNameLabel("Name: " + user.getName());
        myFrame.setSaldoLabel("Saldo: " + user.getSaldo().toString());
        WithdrawAmountTxtField.setVisible(false);
        WithdrawAmountLabel.setVisible(false);
        TransferAmountLabel.setVisible(false);
        TransferAmountTxtField.setVisible(false);
        ToAccountNumberLabel.setVisible(false);
        TransferToAccounterNumberTxtField.setVisible(false);
        WithdrawAllButton.setVisible(false);
        OKButton.setVisible(false);
        OKTransferButton.setVisible(false);

        //myFrame.pack();
        myFrame.setVisible(true);

        // Fjern WithdrawButton ???
        WithdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == WithdrawButton) {
                    WithdrawAmountTxtField.setVisible(true);
                    WithdrawAmountLabel.setVisible(true);
                    WithdrawAllButton.setVisible(true);
                    OKButton.setVisible(true);
                    WithdrawAmountTxtField.setText("0");
                    //UserLoggedInScreen.super.pack();
                    myFrame.setSize(610,300);
                    UserLoggedInScreen.super.repaint();
                }
            }
        });
        WithdrawAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == WithdrawAllButton) {
                    WithdrawAmountTxtField.setVisible(true);
                    WithdrawAmountTxtField.setText(user.getSaldo().toString());
                    //UserLoggedInScreen.super.pack();
                    myFrame.setSize(610,300);
                    UserLoggedInScreen.super.repaint();
                }
            }
        });
        TransferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TransferButton) {
                    TransferAmountLabel.setVisible(true);
                    TransferAmountTxtField.setVisible(true);
                    ToAccountNumberLabel.setVisible(true);
                    TransferToAccounterNumberTxtField.setVisible(true);
                    OKTransferButton.setVisible(true);
                }
            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ExitButton) {
                    myFrame.dispose();
                }
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == OKButton) {
                    double saldo = user.getSaldo();
                    double withdrawAmount = Double.parseDouble(WithdrawAmountTxtField.getText());
                    double newSaldo = saldo - withdrawAmount;
                    if (newSaldo >= 0) {
                        user.setSaldo(newSaldo);
                        SaldoLabel.setText("Saldo: " + newSaldo);
                        mysql.updateUserInSQL(user);
                    } else {
                        JOptionPane.showMessageDialog(null,"You dont have enough balance. You can withdraw the total amount of " + user.getSaldo());
                    }
                }
            }
        });
        OKTransferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == OKTransferButton) {
                    //JOptionPane.showMessageDialog(null,"test transferOKButton");

                    //Test om modtagers kontonummer findes true/false:
                    //System.out.println(mysql.userAccountExists(Integer.valueOf(TransferToAccounterNumberTxtField.getText())));

                    // om penge nok:
                    if (Double.valueOf(TransferAmountTxtField.getText()) <= user.getSaldo() ) {
                        // System.out.println("der er penge nok");

                        User user1 = mysql.getUserFromSQL(Integer.valueOf(TransferToAccounterNumberTxtField.getText()), "BankUsers_tbl");
                        user.setSaldo(user.getSaldo()-Double.valueOf(TransferAmountTxtField.getText()));
                        user1.setSaldo(user1.getSaldo()+Double.valueOf(TransferAmountTxtField.getText()));
                        mysql.updateUserInSQL(user);
                        mysql.updateUserInSQL(user1);
                        setSaldoLabel(user.getSaldo().toString());

                        JOptionPane.showMessageDialog(null,TransferAmountTxtField.getText() + " blev overført til kontonummer " + TransferToAccounterNumberTxtField.getText());

                        try {
                            FileHandler.writeToFile("Transaction00001.txt", user.getKontonummer(),Double.valueOf(TransferAmountTxtField.getText()),user1.getKontonummer())
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        // Ryd form
                        TransferAmountTxtField.setText("");
                        TransferToAccounterNumberTxtField.setText("");

                        /**
                         * Opret tranksaktionshistorik - Nej opret generel historik over alt en bruger foretager sig
                         * Opret en txt/dat-template, find ud af hvilke felter kolonner der skal være
                         * Opret funktion eller lignende til at skabe historik-linje
                         * Sikket nemmere at skrive til historik-filen hver gang der gøres noget, i stedet for at samle sammen og skrive til fil ved Luk.
                         * Brug UUIDs?
                         *
                         */

                    } else {
                        System.out.println("Du har ikke penge nok. Prøv med et mindre beløb.");
                    }
                }
            }
        });
    }


    public static void main(String[] args) {

    }

}
