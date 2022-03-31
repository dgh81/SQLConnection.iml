package com.company;

public class Customer extends User{

    //TODO skal føjes til tabellen i sql:


    public Customer(String name, String email, String phone, String address, String password) {
        super.name = name;
        super.email = email;
        super.phone = phone;
        super.address = address;
        super.password = password;

    }
    public Customer() {

    }


}
