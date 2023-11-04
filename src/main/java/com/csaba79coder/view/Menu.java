package com.csaba79coder.view;

import com.csaba79coder.model.Contact;
import com.csaba79coder.model.MobilePhone;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner= new Scanner(System.in);
    private static final MobilePhone mobilePhone = new MobilePhone("0744 123 456");


    public static void load() {

        boolean quit = false;

        startPhone();
        printActions();

        while (!quit) {
            System.out.println("\nEnter an action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0 -> {
                    System.out.println("Good bye!\nShutting down ...");
                    quit = true;
                }
                case 1 -> mobilePhone.printContacts();
                case 2 -> addNewContact();
                case 3 -> updateContact();
                case 4 -> removeContact();
                case 5 -> queryContact();
                case 6 -> printActions();
            }
        }
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

    private static void printActions() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("""
                0 - to shutdown
                1 - to print contacts
                2 - to add a new contact
                3 - to update an existing contact
                4 - to remove an existing contact
                5 - query if an existing contact exists
                6 - to print a list of available actions.""");
        System.out.println("Choose your action: ");
    }

    private static void addNewContact() {
        String name = scanInput("Enter existing contact name: ");
        String phoneNumber = scanInput("Enter existing contact phone number: ");
        Contact currentContact = Contact.createContact(name, phoneNumber);
        if (mobilePhone.addNewContact(currentContact)) {
            System.out.println("New contact added: " + name + ", phone = " + phoneNumber);
        } else {
            System.out.println("Cannot add, " + name + " already on file.");
        }
    }

    private static void updateContact() {
        String name;
        Contact existingContact;

        do {
            name = scanInput("Enter existing contact name: ");
            existingContact = mobilePhone.queryContact(name);
            if (existingContact == null) {
                System.out.println("Contact not found. Please try again.");
            }
        } while (existingContact == null);

        String newName = scanInput("Enter new contact name: ");
        String newPhoneNumber = scanInput("Enter new contact phone number: ");
        Contact updatedContact = Contact.createContact(newName, newPhoneNumber);

        if (mobilePhone.updateContact(existingContact, updatedContact)) {
            System.out.println("Successfully updated record.");
        } else {
            System.out.println("Error updating record.");
        }
    }

    private static void queryContact() {
        String name = scanInput("Enter existing contact name: ");
        Contact existingContact = mobilePhone.queryContact(name);
        handleContactResult(existingContact);
        System.out.println("Name: " + existingContact.getName() + " phone number is " + existingContact.getPhoneNumber());
    }

    private static void removeContact() {
        String name = scanInput("Enter existing contact name: ");
        Contact existingContact = mobilePhone.queryContact(name);
        handleContactResult(existingContact);
        if (mobilePhone.removeContact(existingContact)) {
            System.out.println("Successfully deleted.");
        } else {
            System.out.println("Error deleting contact.");
        }
    }

    private static String scanInput(String message) {
        System.out.println(message);
        return Menu.scanner.nextLine();
    }

    private static void handleContactResult(Contact existingContact) {
        if (existingContact == null) {
            System.out.println("Contact not found.");
        }
    }
}
