package com.address.book.controller;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.exception.UpdateRepositoryException;
import com.address.book.model.Contact;
import com.sun.deploy.net.HttpResponse;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
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

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public ResponseEntity<List<ContactDto>> getAllContacts() {
        try {
            return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.debug("Could not get all contacts: ", ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

	}

    @RequestMapping(value = "/add/contact", method = RequestMethod.POST)
    public ResponseEntity<List<ContactDto>> saveContact(@RequestBody ContactDto contactDto) {
        if (!StringUtils.isEmpty(contactDto.getName()) && !StringUtils.isEmpty(contactDto.getTelephoneNumber())) {
            try {
                return new ResponseEntity<>(addressBookService.saveContact(contactDto), HttpStatus.OK);
            } catch (Exception ex) {
                logger.debug("Could not save contact: ", ex);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete/contact/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<ContactDto>> deleteContact(@PathVariable("id") String id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                addressBookService.deleteContactById(id);
                return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
            }
            logger.debug("Contact ID is null or empty: ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            logger.debug("Canont delete contact: ", ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

