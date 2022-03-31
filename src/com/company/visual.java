package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class visual implements ActionListener {
    JFrame frame1 = new JFrame();
    JButton createUser = new JButton("create user");
    JButton deleteUser = new JButton("delete user");
    JTextField usernameoremail = new JTextField();
    JTextField password = new JTextField();
    JLabel titel = new JLabel("Login");
    public void look () {

        //buttons
        createUser.setBounds(100,100,100,25);
        createUser.setFocusable(false);
        createUser.addActionListener(this);

        deleteUser.setBounds(100,100,100,25);
        deleteUser.setFocusable(false);
        deleteUser.addActionListener(this);

        //field
        usernameoremail.setBounds(200,500,150,25);
        password.setBounds(200,550,150,25);

        frame1.add(createUser);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1000,1000);
        frame1.setLayout(null);
        frame1.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource()== createUser){
        mysql msql = mysql.getInstance();

        Employee user1 = new Employee("Karen Klippesen", "kk@klipmig.dk", "Klippevej 14 Hårstrup 4100", "11223344", "1111");

        user1 = msql.logUserIn(user1);
    }
    }
}
