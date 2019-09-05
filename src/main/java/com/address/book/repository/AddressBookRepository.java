package com.address.book.repository;

import com.address.book.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */
public interface AddressBookRepository extends MongoRepository<Contact, String> {
    Contact save(Contact contact);
    void deleteById(String id);
    List<Contact> findAllByOrderByNameAsc();
    Contact getContactById(String id);
}
