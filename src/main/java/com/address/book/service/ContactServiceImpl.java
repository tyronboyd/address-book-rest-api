package com.address.book.service;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.exception.UpdateRepositoryException;
import com.address.book.mapper.ContactDtoMapper;
import com.address.book.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyronboyd on 4/9/19.
 */

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactDtoMapper contactDtoMapper;

    public List<ContactDto> getAllContacts() throws UpdateRepositoryException, ContactMappingException {
        List<ContactDto> contacts = contactDtoMapper.mapToDtoList(contactRepository.findAllByOrderByNameAsc());
        if (!StringUtils.isEmpty(contacts)) {
            return contacts;
        }
        return new ArrayList<>();
    }

    public void deleteContactById(String id) throws UpdateRepositoryException {
        this.contactRepository.deleteById(id);
    }

    public ContactDto saveContact(ContactDto contactDto) throws UpdateRepositoryException, ContactMappingException {
        return contactDtoMapper.mapToDto(this.contactRepository.save(this.contactDtoMapper.mapFromDto(contactDto)));
    }

    public ContactDto getContactById(String id) throws UpdateRepositoryException, ContactMappingException {
        return this.contactDtoMapper.mapToDto(this.contactRepository.getContactById(id));
    }

}
