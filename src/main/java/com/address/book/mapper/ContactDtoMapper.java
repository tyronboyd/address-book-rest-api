package com.address.book.mapper;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.model.Contact;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyronboyd on 6/9/19.
 */

@Component
public class ContactDtoMapper {

    /**
     * Maps a list of Contact Entities to a List of Contact DTOs
     * @param contacts
     * @return List<ContactDto>
     */
    public List<ContactDto> mapToDtoList(List<Contact> contacts) throws ContactMappingException {
        List<ContactDto> contactDtoList = new ArrayList<>();
        contacts.stream().forEach(contact -> {
            if (!StringUtils.isEmpty(contact.getName()) && !StringUtils.isEmpty(contact.getId()) &&
                    !StringUtils.isEmpty(contact.getTelephoneNumber())) {
                ContactDto contactDto = new ContactDto();
                contactDto.setId(contact.getId());
                contactDto.setName(contact.getName());
                contactDto.setTelephoneNumber(contact.getTelephoneNumber());
                contactDtoList.add(contactDto);
            }
        });
        return contactDtoList;
    }

    /**
     * Maps a list of Contacts DTOs to a List of Contact Entities
     * @param contactDtoList
     * @return List<Contact>
     */
    public List<Contact> mapFromDtoList(List<ContactDto> contactDtoList) throws ContactMappingException {
        List<Contact> contactList = new ArrayList<>();
        contactDtoList.stream().forEach(contactDto -> {
            if (!StringUtils.isEmpty(contactDto.getName()) && !StringUtils.isEmpty(contactDto.getTelephoneNumber())) {
                Contact contact = new Contact();
                contact.setId(contactDto.getId());
                contact.setName(contactDto.getName());
                contact.setTelephoneNumber(contactDto.getTelephoneNumber());
                contactList.add(contact);
            }
        });
        return contactList;
    }

    /**
     * Maps a Contact Entity to a Contact DTO
     * @param contact
     * @return ContactDto
     */
    public ContactDto mapToDto(Contact contact) throws ContactMappingException {
        if (!StringUtils.isEmpty(contact.getName()) && !StringUtils.isEmpty(contact.getId()) &&
                !StringUtils.isEmpty(contact.getTelephoneNumber())) {
            ContactDto contactDto = new ContactDto();
            contactDto.setId(contact.getId());
            contactDto.setName(contact.getName());
            contactDto.setTelephoneNumber(contact.getTelephoneNumber());
            return contactDto;
        }
        throw new ContactMappingException("Could not map contact to Dto.");
    }

    /**
     * Maps a Contact DTO to a Contact Entity
     * @param contactDto
     * @return Contact
     */
    public Contact mapFromDto(ContactDto contactDto) throws ContactMappingException {
        if (!StringUtils.isEmpty(contactDto.getName()) && !StringUtils.isEmpty(contactDto.getTelephoneNumber())) {
            Contact contact = new Contact();
            contact.setId(contactDto.getId());
            contact.setName(contactDto.getName());
            contact.setTelephoneNumber(contactDto.getTelephoneNumber());
            return contact;
        }
        throw new ContactMappingException("Could not map contact to Dto.");
    }


}
