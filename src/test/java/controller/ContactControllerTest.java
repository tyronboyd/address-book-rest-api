package controller;

import com.address.book.controller.ContactController;
import com.address.book.dto.ContactDto;
import com.address.book.exception.http.HttpBadRequestException;
import com.address.book.service.ContactService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.StringWriter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tyronboyd on 4/9/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testSaveContact() throws Exception {
        mockMvc.perform(post("/add/contact").content(asJson(getContact()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveContactNull() throws Exception {
        mockMvc.perform(post("/add/contact").content(asJson(new ContactDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    public void testSaveContactEmpty() throws Exception {
        mockMvc.perform(post("/add/contact").content(asJson(getContactEmpty()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400));
    }


    @Test
    public void testDeleteContact() throws Exception {
        mockMvc.perform(post("/delete/contact/{id}", "123").content(asJson(getContact()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllContacts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getContentAsString(), "[]");
    }

    @Test
    public void testGetAllContactsExceptionHandling() throws Exception {
        when(this.contactService.getAllContacts()).thenThrow(new RuntimeException());
        MvcResult mvcResult = mockMvc.perform(get("/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400)).andReturn();

        String errMsg = ((HttpBadRequestException)mvcResult.getResolvedException()).getErrMessage();
        Assert.assertEquals(errMsg, "Could not get all contacts");
    }


    private ContactDto getContact() {
        ContactDto contact = new ContactDto();
        contact.setId("1234");
        contact.setName("John Smith");
        contact.setTelephoneNumber("+61 468 422 558");
        return contact;
    }

    private ContactDto getContactEmpty() {
        ContactDto contact = new ContactDto();
        contact.setName("");
        contact.setTelephoneNumber("");
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

