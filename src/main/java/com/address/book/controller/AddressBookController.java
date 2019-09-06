package com.address.book.controller;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.exception.UpdateRepositoryException;
import com.address.book.exception.http.HttpBadRequestException;
import com.address.book.exception.http.HttpNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new HttpBadRequestException("Could not get all contacts");
        }
	}

    @RequestMapping(value = "/add/contact", method = RequestMethod.POST)
    public ResponseEntity<List<ContactDto>> saveContact(@RequestBody ContactDto contactDto) {
        if (!StringUtils.isEmpty(contactDto.getName()) && !StringUtils.isEmpty(contactDto.getTelephoneNumber())) {
            try {
                return new ResponseEntity<>(addressBookService.saveContact(contactDto), HttpStatus.OK);
            } catch (Exception ex) {
                logger.debug("Could not save contact: ", ex);
                throw new HttpBadRequestException("Could not save contact");
            }
        }
        throw new HttpBadRequestException("Could not save contact");
    }

    @RequestMapping(value = "/delete/contact/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<ContactDto>> deleteContact(@PathVariable("id") String id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                addressBookService.deleteContactById(id);
                return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
            }
            logger.debug("Contact ID is null or empty: ");
            throw new HttpNotFoundException("Contact ID is null or empty");
        } catch (Exception ex) {
            logger.debug("Could not delete contact: ", ex);
            throw new HttpBadRequestException("Could not delete contact");
        }
    }
}

