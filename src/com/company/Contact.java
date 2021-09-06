package com.company;

public class Contact {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;


    public Contact(String phoneNumber, String firstName, String lastName, String email, String address) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

}

