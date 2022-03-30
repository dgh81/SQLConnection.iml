package com.company;

public class Customer extends User{

    //TODO skal føjes til tabellen i sql:
    protected String gender;

    public Customer(String gender) {
        this.gender = gender;
    }

}
