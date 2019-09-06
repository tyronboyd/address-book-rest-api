package com.address.book.controller;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
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
	public ResponseEntity<List<ContactDto>> getAllContacts() throws UpdateRepositoryException, ContactMappingException {
		return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
	}

    @RequestMapping(value = "/add/contact", method = RequestMethod.POST)
    public ResponseEntity<List<ContactDto>> saveContact(@RequestBody ContactDto contactDto) throws UpdateRepositoryException,
    ContactMappingException {
        if (!StringUtils.isEmpty(contactDto.getName()) && !StringUtils.isEmpty(contactDto.getTelephoneNumber())) {
            return new ResponseEntity<>(addressBookService.saveContact(contactDto), HttpStatus.OK);
        }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete/contact/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<ContactDto>> deleteContact(@PathVariable("id") String id) throws UpdateRepositoryException,
    ContactMappingException{
        if (id != null) {
            addressBookService.deleteContactById(id);
            return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

