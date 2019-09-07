package com.address.book.mapper;

import com.address.book.dto.AddressBookDto;
import com.address.book.exception.AddressBookMappingException;
import com.address.book.exception.ContactMappingException;
import com.address.book.model.AddressBook;
import com.address.book.model.Contact;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyronboyd on 6/9/19.
 */

@Component
public class AddressBookDtoMapper {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddressBookDtoMapper.class);

    @Autowired
    private ContactDtoMapper contactDtoMapper;

    /**
     * Maps a list of Address Book Entities to a List of Address Book DTOs
     * @param addressBookList
     * @return List<AddressBookDto>
     */
    public List<AddressBookDto> mapToDtoList(List<AddressBook> addressBookList) {
        List<AddressBookDto> addressBookDtoList = new ArrayList<>();
        addressBookList.stream().forEach(addressBook -> {
            if (!StringUtils.isEmpty(addressBook.getName()) && !StringUtils.isEmpty(addressBook.getId())) {
                AddressBookDto addressBookDto = new AddressBookDto();
                addressBookDto.setId(addressBook.getId());
                addressBookDto.setName(addressBook.getName());
                try {
                    if (!addressBook.getContactList().isEmpty()) {
                        addressBookDto.setContactList(contactDtoMapper.mapToDtoList(addressBook.getContactList()));
                    }
                } catch (ContactMappingException ex) {
                    logger.debug("Error mapping Contact List: ", ex);
                }
                addressBookDtoList.add(addressBookDto);
            }
        });
        return addressBookDtoList;
    }

    /**
     * Maps a list of Address Books DTOs to a List of Address Book Entities
     * @param addressBookDtoList
     * @return List<Addressbook>
     */
    public List<AddressBook> mapFromDtoList(List<AddressBookDto> addressBookDtoList) {
        List<AddressBook> addressBookList = new ArrayList<>();
        addressBookDtoList.stream().forEach(addressBookDto -> {
            if (!StringUtils.isEmpty(addressBookDto.getName())) {
                AddressBook addressBook = new AddressBook();
                addressBook.setName(addressBookDto.getName());
                try {
                    if (!addressBookDto.getContactList().isEmpty()) {
                        addressBook.setContactList(contactDtoMapper.mapFromDtoList(addressBookDto.getContactList()));
                    }
                } catch (ContactMappingException ex) {
                    logger.debug("Error mapping Contact List: ", ex);
                }
                addressBookList.add(addressBook);
            }
        });
        return addressBookList;
    }
}
