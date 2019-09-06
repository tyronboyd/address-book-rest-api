package com.address.book.service;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.exception.UpdateRepositoryException;
import com.address.book.mapper.ContactDtoMapper;
import com.address.book.model.Contact;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.address.book.repository.AddressBookRepository;

import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactDtoMapper contactDtoMapper;

    public List<ContactDto> getAllContacts() throws UpdateRepositoryException, ContactMappingException {
        List<ContactDto> contacts = contactDtoMapper.mapToDtoList(addressBookRepository.findAllByOrderByNameAsc());
        if (contacts != null) {
            return contacts;
        }
        throw new UpdateRepositoryException("Unable to fetch Contacts List.");
    }

    public void deleteContactById(String id) throws UpdateRepositoryException {
        this.addressBookRepository.deleteById(id);
    }

    public List<ContactDto> saveContact(ContactDto contactDto) throws UpdateRepositoryException, ContactMappingException {
        this.addressBookRepository.save(this.contactDtoMapper.mapFromDto(contactDto));
        return contactDtoMapper.mapToDtoList(this.addressBookRepository.findAllByOrderByNameAsc());
    }

    public ContactDto getContactById(String id) throws UpdateRepositoryException, ContactMappingException {
        return this.contactDtoMapper.mapToDto(this.addressBookRepository.getContactById(id));
    }

}
