package com.address.book.model;
import org.springframework.data.annotation.Id;

/**
 * Created by tyronboyd on 4/9/19.
 */
public class Contact {

    @Id
    private String id;
    private String name;
    private String telephoneNumber;

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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
