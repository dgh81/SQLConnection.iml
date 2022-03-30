package com.company;

public class Employee extends User{
    protected String socialIDNumber;
    public Employee(String socialIDNumber) {
        this.socialIDNumber = socialIDNumber;
    }

    public String getSocialIDNumber() {
        return socialIDNumber;
    }

    public void setSocialIDNumber(String socialIDNumber) {
        this.socialIDNumber = socialIDNumber;
    }
}
