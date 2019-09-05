package com.address.book.service;

import com.address.book.exception.UpdateRepositoryException;
import com.address.book.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.address.book.repository.AddressBookRepository;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<Contact> getAllContacts() throws UpdateRepositoryException {
        List<Contact> contacts = this.addressBookRepository.findAllByOrderByNameAsc();
        if (contacts != null) {
            return contacts;
        }
        throw new UpdateRepositoryException("Unable to fetch Contacts List.");
    }

    public void deleteContactById(String id) throws UpdateRepositoryException {
        this.addressBookRepository.deleteById(id);
    }

    public List<Contact> saveContact(Contact contact) throws UpdateRepositoryException {
        Contact savedContact = this.addressBookRepository.save(contact);
        if (savedContact.equals(contact)) {
            return this.addressBookRepository.findAllByOrderByNameAsc();
        }
        throw new UpdateRepositoryException("Cannot save contact to Address Book.");
    }

    public Contact getContactById(String id) throws UpdateRepositoryException {
        return this.addressBookRepository.getContactById(id);
    }

}
