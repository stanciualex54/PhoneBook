package com.company;

import java.util.*;

public class PhoneBook {

    Map<String, Contact> phoneBook = new HashMap<>();

    public void addContact(Contact contact) {
        this.phoneBook.put(contact.getPhoneNumber(), contact);
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        return phoneBook.containsKey(phoneNumber);
    }

    public boolean validNumber(String phoneNumber) {
        return !phoneNumber.matches("^0[\\s\\-?\\d]+$") || phoneNumber.length() != 10;
    }

    public Map<String, Contact> getPhoneBook() {
        return phoneBook;
    }
}
