package mapper;

import com.address.book.dto.ContactDto;
import com.address.book.exception.ContactMappingException;
import com.address.book.mapper.ContactDtoMapper;
import com.address.book.model.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyronboyd on 7/9/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class ContactDtoMapperTest {

    @Autowired
    private ContactDtoMapper contactDtoMapper;

    @Before
    public void setUp() {
        contactDtoMapper = new ContactDtoMapper();
    }

    @Test
    public void testMapToDto() throws Exception {
        Contact contact = new Contact();
        contact.setName("Tyron Boyd");
        contact.setId("id-1234");
        contact.setTelephoneNumber("1234");
        ContactDto dto = contactDtoMapper.mapToDto(contact);
        Assert.assertEquals(dto.getId(), contact.getId());
        Assert.assertEquals(dto.getName(), contact.getName());
        Assert.assertEquals(dto.getTelephoneNumber(), contact.getTelephoneNumber());
    }

    @Test(expected = ContactMappingException.class)
    public void testMapToDtoError() throws Exception {
        Contact contact = new Contact();
        contact.setId("id-1234");
        contact.setTelephoneNumber("1234");
        ContactDto dto = contactDtoMapper.mapToDto(contact);
    }

    @Test
    public void testMapFromDto() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Tyron Boyd");
        contactDto.setId("id-1234");
        contactDto.setTelephoneNumber("1234");
        Contact contact = contactDtoMapper.mapFromDto(contactDto);
        Assert.assertEquals(contactDto.getId(), contact.getId());
        Assert.assertEquals(contactDto.getName(), contact.getName());
        Assert.assertEquals(contactDto.getTelephoneNumber(), contact.getTelephoneNumber());
    }

    @Test(expected = ContactMappingException.class)
    public void testMapFromDtoError() throws Exception {
        ContactDto contactDto = new ContactDto();
        contactDto.setId("id-1234");
        contactDto.setTelephoneNumber("1234");
        Contact contact = contactDtoMapper.mapFromDto(contactDto);
    }

    @Test
    public void testMapFromDtoList() throws Exception {
        List<ContactDto> contactDtoList = new ArrayList<>();
        ContactDto contactDto = new ContactDto();
        contactDto.setName("Tyron Boyd");
        contactDto.setId("id-1234");
        contactDto.setTelephoneNumber("1234");
        contactDtoList.add(contactDto);
        List<Contact> contactList = contactDtoMapper.mapFromDtoList(contactDtoList);
        Assert.assertEquals(contactList.size(), 1);
        Assert.assertEquals(contactList.get(0).getTelephoneNumber(), contactDto.getTelephoneNumber());
        Assert.assertEquals(contactList.get(0).getName(), contactDto.getName());
        Assert.assertEquals(contactList.get(0).getId(), contactDto.getId());
    }

    @Test
    public void testMapToDtoList() throws Exception {
        List<Contact> contactList = new ArrayList<>();
        Contact contact = new Contact();
        contact.setName("Tyron Boyd");
        contact.setId("id-1234");
        contact.setTelephoneNumber("1234");
        contactList.add(contact);
        List<ContactDto> contactDtoList = contactDtoMapper.mapToDtoList(contactList);
        Assert.assertEquals(contactList.size(), 1);
        Assert.assertEquals(contactDtoList.get(0).getTelephoneNumber(), contact.getTelephoneNumber());
        Assert.assertEquals(contactDtoList.get(0).getName(), contact.getName());
        Assert.assertEquals(contactDtoList.get(0).getId(), contact.getId());
    }
}
