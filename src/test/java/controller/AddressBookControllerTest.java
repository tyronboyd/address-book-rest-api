package controller;

import com.address.book.controller.AddressBookController;
import com.address.book.dto.ContactDto;
import com.address.book.model.Contact;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.StringWriter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tyronboyd on 4/9/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(addressBookController).build();
    }

    @Test
    public void testSaveContact() throws Exception {
        mockMvc.perform(post("/add/contact").content(asJson(getContact()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private ContactDto getContact() {
        ContactDto contact = new ContactDto();
        contact.setId("1234");
        contact.setName("John Smith");
        contact.setTelephoneNumber("+61 468 422 558");
        return contact;
    }

    private String asJson(Object param) throws Exception {
        JsonFactory factory = new JsonFactory();
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writer);
        generator.setCodec(new ObjectMapper());
        generator.writeObject(param);
        return writer.toString();
    }

}

