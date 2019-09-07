package com.address.book.controller;

import com.address.book.dto.AddressBookDto;
import com.address.book.dto.ContactDto;
import com.address.book.exception.http.HttpBadRequestException;
import com.address.book.exception.http.HttpNotFoundException;
import com.address.book.service.AddressBookService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */

@RestController
public class AddressBookController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = "/address/books", method = RequestMethod.GET)
	public ResponseEntity<List<AddressBookDto>> getAllContacts() {
        try {
            return new ResponseEntity<>(addressBookService.getAllAddressBooks(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.debug("Could not get all address books: ", ex);
            throw new HttpBadRequestException("Could not get all address books");
        }
	}

    @RequestMapping(value = "/add/address/book", method = RequestMethod.POST)
    public ResponseEntity<List<AddressBookDto>> saveContact(@RequestBody AddressBookDto addressBookDto) {
        if (!StringUtils.isEmpty(addressBookDto.getName())) {
            try {
                return new ResponseEntity<>(addressBookService.saveAddressBook(addressBookDto), HttpStatus.OK);
            } catch (Exception ex) {
                logger.debug("Could not save address book: ", ex);
                throw new HttpBadRequestException(((HttpBadRequestException)ex).getErrMessage());
            }
        }
        throw new HttpBadRequestException("Could not save address book");
    }

    @RequestMapping(value = "/delete/address/book/{id}", method = RequestMethod.POST)
    public ResponseEntity<List<AddressBookDto>> deleteContact(@PathVariable("id") String id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                addressBookService.deleteAddressBookById(id);
                return new ResponseEntity<>(addressBookService.getAllAddressBooks(), HttpStatus.OK);
            }
            logger.debug("Address Book ID is null or empty: ");
            throw new HttpNotFoundException("Address Book ID is null or empty");
        } catch (Exception ex) {
            logger.debug("Could not delete Address Book: ", ex);
            throw new HttpBadRequestException("Could not delete Address Book");
        }
    }

    @RequestMapping(value = "/add/address/book/contact/{name}", method = RequestMethod.POST)
    public ResponseEntity<List<AddressBookDto>> addContactToAddressBook(
            @RequestBody ContactDto contactDto, @PathVariable("name") String name) {
        try {
            if (!StringUtils.isEmpty(name)) {
                List<AddressBookDto> addressBookDto = addressBookService.saveContactToAddressBook(contactDto, name);
                return new ResponseEntity<>(addressBookDto, HttpStatus.OK);
            }
            logger.debug("Address Book Name is null or empty: ");
            throw new HttpNotFoundException("Address Book ID is null or empty");
        } catch (Exception ex) {
            logger.debug("Could not add Contact to Address Book: ", ex);
            throw new HttpBadRequestException(((HttpBadRequestException) ex).getErrMessage());
        }
    }

    @RequestMapping(value = "/address/book/contacts/distinct", method = RequestMethod.GET)
    public ResponseEntity<List<ContactDto>> getDistinctAddressBookContacts() {
        try {
            List<ContactDto> contactDtoList = addressBookService.filterAddressBookDistinctByName();
            return new ResponseEntity<>(contactDtoList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.debug("Could not add Contact to Address Book: ", ex);
            throw new HttpBadRequestException("Could not get distinct contacts");
        }
    }
}

