package com.address.book.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyronboyd on 7/9/19.
 */
public class AddressBookDto {

    private String id;
    private String name;
    private List<ContactDto> contactList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContactDto> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactDto> contactList) {
        this.contactList = contactList;
    }
}
