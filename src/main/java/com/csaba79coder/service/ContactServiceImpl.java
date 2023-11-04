package com.csaba79coder.service;

import com.csaba79coder.model.Contact;
import com.csaba79coder.model.MobilePhone;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    private final MobilePhone mobilePhone;

    public ContactServiceImpl(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean addContact(Contact contact) {
        return mobilePhone.addNewContact(contact);
    }

    @Override
    public boolean removeContact(String name) {
        Contact existingContact = mobilePhone.queryContact(name);
        if (existingContact != null) {
            return mobilePhone.removeContact(existingContact);
        }
        return false;
    }

    @Override
    public boolean updateContact(String oldName, Contact newContact) {
        Contact existingContact = mobilePhone.queryContact(oldName);
        if (existingContact != null) {
            return mobilePhone.updateContact(existingContact, newContact);
        }
        return false;
    }

    @Override
    public Contact searchContact(String name) {
        return mobilePhone.queryContact(name);
    }

    @Override
    public List<Contact> listContacts() {
        return mobilePhone.getMyContacts();
    }

    @Override
    public void exit() {
        // Provide exit logic if needed
    }
}