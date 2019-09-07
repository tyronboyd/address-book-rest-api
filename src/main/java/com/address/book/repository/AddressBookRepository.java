package com.address.book.repository;

import com.address.book.model.AddressBook;
import com.address.book.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */
public interface AddressBookRepository extends MongoRepository<AddressBook, String> {

    /**
     * Saves an AddressBook to MongoDB
     * @param contact
     * @return Contact
     */

    AddressBook save(AddressBook contact);

    /**
     * Deletes an AddressBook by the given ID in MongoDB
     * @param id
     */

    void deleteById(String id);

    /**
     * Finds all AddressBooks
     * @return List<Contact>
     */

    List<AddressBook> findAll();

    /**
     * Get Address Book By ID
     * @param id
     * @return Contact
     */

    AddressBook getAddressBookById(String id);

    /**
     * Find Address Book By Name
     * @param name
     * @return AddressBook
     */

    AddressBook findAddressBookByName(String name);

}
