package service;

import com.address.book.dto.ContactDto;
import com.address.book.mapper.ContactDtoMapper;
import com.address.book.model.Contact;
import com.address.book.repository.ContactRepository;
import com.address.book.service.ContactServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by tyronboyd on 4/9/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @Spy
    @InjectMocks
    private ContactServiceImpl addressBookService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactDtoMapper contactDtoMapper;

    @Test
    public void testGetAlLContacts() throws Exception {
        when(contactDtoMapper.mapToDtoList(contactRepository.findAllByOrderByNameAsc())).thenReturn(getContactDtoList());
        List<ContactDto> contactDtos = addressBookService.getAllContacts();
        Assert.assertEquals(contactDtos.size(), 1);
    }

    @Test
    public void testSaveContact() throws Exception {
        when(contactDtoMapper.mapToDto(contactRepository.save(contactDtoMapper
                .mapFromDto(any(ContactDto.class))))).thenReturn(getContactDto());
        when(contactDtoMapper.mapToDtoList(contactRepository.findAllByOrderByNameAsc()))
                .thenReturn(getContactDtoList());
        ContactDto contactDtos = addressBookService.saveContact(getContactDto());
        Assert.assertEquals(contactDtos.getId(), getContact().getId());
    }

    private List<Contact> getContactList() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(getContact());
        return contactList;
    }

    private List<ContactDto> getContactDtoList() {
        List<ContactDto> contactList = new ArrayList<>();
        contactList.add(getContactDto());
        return contactList;
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setId("1234");
        contact.setName("John Smith");
        contact.setTelephoneNumber("+61 468 422 558");
        return contact;
    }

    private ContactDto getContactDto() {
        ContactDto contact = new ContactDto();
        contact.setId("1234");
        contact.setName("John Smith");
        contact.setTelephoneNumber("+61 468 422 558");
        return contact;
    }

}
