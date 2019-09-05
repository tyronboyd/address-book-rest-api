package com.address.book.controller;

import com.address.book.exception.UpdateRepositoryException;
import com.address.book.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.address.book.service.AddressBookService;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */

@RestController
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getAllContacts() throws UpdateRepositoryException {
		return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
	}

    @RequestMapping(value = "/add/contact", method = RequestMethod.POST)
    public ResponseEntity<List<Contact>> saveContact(@RequestBody Contact contact) throws UpdateRepositoryException {
        if (StringUtils.isEmpty(contact.getName()) && StringUtils.isEmpty(contact.getTelephoneNumber())) {
            return new ResponseEntity<>(addressBookService.saveContact(contact), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete/contact", method = RequestMethod.PUT)
    public ResponseEntity<List<Contact>> deleteContact(@RequestParam("id") String id) throws UpdateRepositoryException {
        if (id != null) {
            addressBookService.deleteContactById(id);
            return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

