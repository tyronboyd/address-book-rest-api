package com.address.book.service;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.exception.UpdateRepositoryException;
import com.address.book.model.Contact;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */
public interface AddressBookService {

    /**
     * Gets all Contacts
     * @return List<ContactDto>
     */

    List<ContactDto> getAllContacts() throws UpdateRepositoryException, ContactMappingException;

    /**
     * Deletes a Contact by ID
     * @param id
     */

    void deleteContactById(String id) throws UpdateRepositoryException, ContactMappingException;

    /**
     * Saves a Contact
     * @param contact
     * @return List<ContactDto>
     */

    List<ContactDto> saveContact(ContactDto contact) throws UpdateRepositoryException, ContactMappingException;

    /**
     * Get Contacts by ID
     * @param id
     * @return ContactDto
     */

    ContactDto getContactById(String id) throws UpdateRepositoryException, ContactMappingException;
}
