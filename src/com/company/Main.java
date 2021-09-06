package com.company;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static PhoneBook phoneBook = new PhoneBook();
    static int numberOfContacts = 0;

    private static void displayMenu() {
        System.out.println("1. Add or edit contact");
        System.out.println("2. View all contacts");
        System.out.println("3. Find a contact by phone number");
        System.out.println("4. Find contacts by name");
        System.out.println("0. Exit\n");
        System.out.print("Select an option ");
    }

    static private void readInput() {
        System.out.print("Enter a phone number ");
        scanner.nextLine();
        String phoneNumber = scanner.nextLine();

        while (true) {
            if (phoneBook.validNumber(phoneNumber)) {
                System.out.println("Please enter a 10-digit valid number");
                phoneNumber = scanner.nextLine();
            } else break;
        }

        if (phoneBook.checkPhoneNumber(phoneNumber)) {
            System.out.println("This phone number already exists. Editing an existing entry.");

        } else {
            System.out.println("This phone number is new. Adding a new entry to the phone book.");
            numberOfContacts++;
        }

        String firstName = "";
        String lastName = "";
        String email;
        String address;

        while (!firstName.matches("[a-zA-Z]+")) {
            System.out.print("First name: ");
            firstName = scanner.nextLine();
        }
        while (!lastName.matches("[a-zA-Z]+")) {
            System.out.print("Last name: ");
            lastName = scanner.nextLine();
        }
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Address: ");
        address = scanner.nextLine();

        Contact contact = new Contact(phoneNumber, firstName, lastName, email, address);
        phoneBook.addContact(contact);

        System.out.println("Phone book was updated successfully");
        System.out.println("There are currently " + numberOfContacts + " contacts in the phone book\n");
        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }

    private static void searchByNumber() {
        System.out.print("Enter a phone number ");
        scanner.nextLine();
        String phoneNumber = scanner.nextLine();
        Map<String, Contact> map = phoneBook.getPhoneBook();

        while (true) {
            if (phoneBook.validNumber(phoneNumber)) {
                System.out.println("Please enter a 10-digit valid number");
                phoneNumber = scanner.nextLine();
            } else break;
        }
        if (!phoneBook.checkPhoneNumber(phoneNumber)) {
            System.out.println("The phone number could not be found in the address book.");
        } else {
            for (Map.Entry<String, Contact> listEntry : map.entrySet()) {
                if (listEntry.getKey().contains(phoneNumber)) {
                    System.out.println("Phone number: " + listEntry.getKey());
                    System.out.println("First name: " + listEntry.getValue().getFirstName());
                    System.out.println("Last name: " + listEntry.getValue().getLastName());
                    System.out.println("Email: " + listEntry.getValue().getEmail());
                    System.out.println("Address: " + listEntry.getValue().getAddress());
                }
            }
        }
        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }

    private static void searchByName() {

        System.out.print("Enter a name (first name, last name or both): ");
        scanner.nextLine();
        String nameToSearch = scanner.nextLine().toLowerCase(Locale.ROOT);
        Map<String, Contact> map = phoneBook.getPhoneBook();
        boolean found = false;
        for (Map.Entry<String, Contact> listEntry : map.entrySet()) {
            String fullName = listEntry.getValue().getFirstName() + " " + listEntry.getValue().getLastName();
            String reverseName = listEntry.getValue().getLastName() + " " + listEntry.getValue().getFirstName();
            if (listEntry.getValue().getFirstName().toLowerCase(Locale.ROOT).contains(nameToSearch)
                    || listEntry.getValue().getLastName().toLowerCase(Locale.ROOT).contains(nameToSearch)
                    || fullName.toLowerCase(Locale.ROOT).contains(nameToSearch)
                    || reverseName.toLowerCase(Locale.ROOT).contains(nameToSearch)) {
                found = true;
                System.out.print(listEntry.getKey() + "    ");
                System.out.println(listEntry.getValue().getFirstName() + " " +
                        listEntry.getValue().getLastName() + "    " +
                        listEntry.getValue().getEmail() + "    " +
                        listEntry.getValue().getAddress());
            }
        }
        if (!found) {
            System.out.println("No result.");
        }

        System.out.print("Press enter to continue");
        scanner.nextLine();
    }

    private static void viewPhoneBook() {

        Map<String, Contact> map = phoneBook.getPhoneBook();

        for (Map.Entry<String, Contact> listEntry : map.entrySet()) {
            System.out.print(listEntry.getKey() + "   ");
            System.out.println(listEntry.getValue().getFirstName() + " " +
                    listEntry.getValue().getLastName() + "   " +
                    listEntry.getValue().getEmail() + "   " +
                    listEntry.getValue().getAddress());
        }
    }

    public static void main(String[] args) {
        System.out.println("Phone Book");
        System.out.println("There are currently " + numberOfContacts + " contacts in the phone book\n");
        displayMenu();
        int key;
        do {
            try {
                key = scanner.nextInt();
                switch (key) {
                    case 1:
                        System.out.println("Add/edit contact\n");
                        readInput();
                        displayMenu();
                        break;
                    case 2:
                        System.out.println("View all contacts\n");
                        viewPhoneBook();
                        displayMenu();
                        break;
                    case 3:
                        System.out.println("Find a contact by phone number\n");
                        searchByNumber();
                        displayMenu();
                        break;
                    case 4:
                        System.out.println("Find contacts by name\n");
                        searchByName();
                        displayMenu();
                        break;
                    case 0:
                        System.out.println("Exit");
                        break;
                }
            } catch (Exception e) {
                break;
            }
        } while (key > 0 && key < 5);
    }
}
