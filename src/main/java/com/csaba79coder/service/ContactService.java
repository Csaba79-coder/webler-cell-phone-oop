package com.csaba79coder.service;

import com.csaba79coder.model.Contact;

import java.util.List;

public interface ContactService {

    boolean addContact(Contact contact);
    boolean removeContact(String name);
    boolean updateContact(String oldName, Contact newContact);
    Contact searchContact(String name);
    List<Contact> listContacts();
    void exit();
}
