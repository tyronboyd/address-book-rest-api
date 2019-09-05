package com.address.book.service;

import com.address.book.exception.UpdateRepositoryException;
import com.address.book.model.Contact;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */
public interface AddressBookService {
    List<Contact> getAllContacts() throws UpdateRepositoryException;
    void deleteContactById(String contact) throws UpdateRepositoryException;
    List<Contact> saveContact(Contact contact) throws UpdateRepositoryException;
    Contact getContactById(String id) throws UpdateRepositoryException;
}
