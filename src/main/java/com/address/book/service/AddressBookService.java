package com.address.book.service;

import com.address.book.dto.AddressBookDto;
import com.address.book.dto.ContactDto;
import com.address.book.exception.AddressBookMappingException;
import com.address.book.exception.UpdateRepositoryException;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */
public interface AddressBookService {

    /**
     * Gets all Address Books
     * @return List<AddressBookDto>
     */

    List<AddressBookDto> getAllAddressBooks() throws UpdateRepositoryException, AddressBookMappingException;

    /**
     * Deletes a Address Book by ID
     * @param id
     */

    void deleteAddressBookById(String id) throws UpdateRepositoryException, AddressBookMappingException;

    /**
     * Saves a Address Book
     * @param addressBookDto
     * @return List<AddressBookDto>
     */

    List<AddressBookDto> saveAddressBook(AddressBookDto addressBookDto) throws UpdateRepositoryException, AddressBookMappingException ;

    /**
     * Saves a Address Book Contact to a particular Address Book
     * @param contactDto
     * @param addressBookName
     * @return List<AddressBookDto>
     */

    List<AddressBookDto> saveContactToAddressBook(ContactDto contactDto, String addressBookName) throws
            UpdateRepositoryException, AddressBookMappingException;

    /**
     * Filters a list of Address Books distinct by name
     * @return List<ContactDto>
     */

    List<ContactDto> filterAddressBookDistinctByName() throws AddressBookMappingException;
}
