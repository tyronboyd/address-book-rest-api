package com.address.book.service;

import com.address.book.dto.AddressBookDto;
import com.address.book.dto.ContactDto;
import com.address.book.exception.AddressBookMappingException;
import com.address.book.exception.ContactMappingException;
import com.address.book.exception.UpdateRepositoryException;
import com.address.book.exception.http.HttpBadRequestException;
import com.address.book.mapper.AddressBookDtoMapper;
import com.address.book.mapper.ContactDtoMapper;
import com.address.book.model.AddressBook;
import com.address.book.model.Contact;
import com.address.book.repository.AddressBookRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */

@Service
public class AddressBookServiceImpl implements AddressBookService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddressBookServiceImpl.class);

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private AddressBookDtoMapper addressBookDtoMapper;

    @Autowired
    private ContactDtoMapper contactDtoMapper;

    @Autowired
    private ContactService contactService;

    public List<AddressBookDto> getAllAddressBooks() throws UpdateRepositoryException, AddressBookMappingException {
        List<AddressBookDto> addressBookDtos = addressBookDtoMapper.mapToDtoList(addressBookRepository.findAll());
        if (!StringUtils.isEmpty(addressBookDtos)) {
            return addressBookDtos;
        }
        return new ArrayList<>();
    }

    public void deleteAddressBookById(String id) throws UpdateRepositoryException {
        this.addressBookRepository.deleteById(id);
    }

    public List<AddressBookDto> saveAddressBook(AddressBookDto addressBookDto) throws UpdateRepositoryException, AddressBookMappingException {
        List<AddressBookDto> addressBookDtoList = new ArrayList<>();
        addressBookDto.setName(addressBookDto.getName().trim());
        addressBookDtoList.add(addressBookDto);
        List<AddressBook> addressBookList  = this.addressBookDtoMapper.mapFromDtoList(addressBookDtoList);
        this.addressBookRepository.findAll().stream().forEach(addressBook -> {
            if (addressBook.getName().equals(addressBookDto.getName())) {
                throw new HttpBadRequestException("Duplicate Address Book name");
            }
        });
        addressBookList.stream().forEach(list -> this.addressBookRepository.save(list));
        return this.addressBookDtoMapper.mapToDtoList(this.addressBookRepository.findAll());
    }

    public List<AddressBookDto> saveContactToAddressBook(ContactDto contactDto, String addressBookName) {
        AddressBook addressBook = this.addressBookRepository.findAddressBookByName(addressBookName);
        try {
            if (addressBook == null) {
                throw new HttpBadRequestException("Address Book not found for: " + addressBookName);
            }
            ContactDto contact = this.contactService.saveContact(contactDto);
            addressBook.getContactList().add(this.contactDtoMapper.mapFromDto(contact));
            this.addressBookRepository.save(addressBook);
        } catch (ContactMappingException | UpdateRepositoryException ex) {
            logger.debug("Could not map contact from Dto");
            throw new HttpBadRequestException("Could not map contact from Dto");
        }
        return this.addressBookDtoMapper.mapToDtoList(this.addressBookRepository.findAll());
    }


}
