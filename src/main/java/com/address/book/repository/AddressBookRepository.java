package com.address.book.repository;

import com.address.book.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */
public interface AddressBookRepository extends MongoRepository<Contact, String> {

    /**
     * Saves a Contact to MongoDB
     * @param contact
     * @return Contact
     */

    Contact save(Contact contact);

    /**
     * Deletes a Contact by the given ID in MongoDB
     * @param id
     */

    void deleteById(String id);

    /**
     * Finds all contacts ordered by name descending in MongoDB
     * @return List<Contact>
     */

    List<Contact> findAllByOrderByNameAsc();

    /**
     * Maps Template list to TemplateTable which is used as a template search result
     * @param id
     * @return Contact
     */

    Contact getContactById(String id);

}
