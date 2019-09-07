# Welcome to the Address Book RESTFul API Application By Tyron Boyd

This application is Build using Java 8 with Maven, Spring Boot with MongoDB.

## How it works

This application has the ability to add multiple Address Books into mongo db addressBook table
and Contacts into an Address Book. You can add Contacts into a particular Address Book by passing the Address Book name as a parameter
and the Contact details in the request body.
In a real-world web application, we would probably use the UI to pass the ID instead of the Address Book name.
I thought this would be easier to test!

The application also allows the user to add Contacts into a contacts table Within the AddressBook database.
JUnit tests have been written for the Contacts, I ran out of time and was unable to cover everything! :).

Please see below for the endpoints available.

## Dependencies

    MongoDB, Maven, Java 8

## Install MAC

    brew install mongodb

## Install Windows

    https://www.mongodb.com/dr/fastdl.mongodb.org/win32/mongodb-win32-x86_64-2012plus-4.2.0-signed.msi/download

## Install

    mvn clean intstall -U

## Run the app

    spring-boot:run

## Run the tests

    mvn surefire:test

# REST API

## Get Contacts List

### Request

`GET /contacts/`

    curl -i -H 'Accept: application/json' http://localhost:8080/contacts/

### Response
    [
        {
            "id": "5d731b817362f924fceaf6fd",
            "name": "Barron Smith",
            "telephoneNumber": "+61 468 000 000"
        }
    ]

## Save Contact

### Request

`POST /add/contact`

    curl -i --header "Content-Type: application/json" --request POST --data '{"name": "Barron Smith", "telephoneNumber": "+61 468 422 558"}' http://localhost:8080/add/contact/

### Response
    [
        {
            "id": "5d731b817362f924fceaf6fd",
            "name": "Barron Smith",
            "telephoneNumber": "+61 468 000 000"
        }
    ]

## Delete Contact

### Request

`POST /delete/contact/{id}`

    curl -i --header "Content-Type: application/json" --request POST http://localhost:8080/delete/contact/5d731b817362f924fceaf6fd

### Response
    []

## Add Address Book

### Request

`POST /add/address/book`

    curl -i --header "Content-Type: application/json" --request POST --data '{"name": "AddressBook2"}' http://localhost:8080/add/address/book

### Response
    [
        {
            "id": "5d73584b7362f9841eb9b7e0",
            "name": "AddressBook2",
            "contactList": []
        }
    ]

## Get Address Books

### Request

`GET /address/books`

    curl -i --header "Content-Type: application/json" http://localhost:8080/address/books

### Response
    [
        {
            "id": "5d73584b7362f9841eb9b7e0",
            "name": "AddressBook2",
            "contactList": []
        }
    ]

## Delete Address Book by ID

### Request

`POST /delete/address/book/{id}`

    curl -i --header "Content-Type: application/json" http://localhost:8080/delete/address/book/5d73584b7362f9841eb9b7e0

### Response
    []

## Add Contact to Address Book by Name

### Request

`POST /add/address/book/contact/{name}`

    curl -i --header "Content-Type: application/json" --request POST --data '{"name": "Barron Smith", "telephoneNumber": "+61 468 422 558"}' http://localhost:8080/add/address/book/contact/AddressBook2

### Response
    [
        {
            "id": "5d73584b7362f9841eb9b7e0",
            "name": "AddressBook2",
            "contactList": [
                {
                    "id": "5d7359eb7362f9841eb9b7e1",
                    "name": "Barron Smith",
                    "telephoneNumber": "+61 468 422 558"
                }
            ]
        }
    ]

## Get all Contacts in all Address Books Distinct by Name

### Request

`GET /address/book/contacts/distinct`

    curl -i --header "Content-Type: application/json" http://localhost:8080/address/book/contacts/distinct

### Response
    [
        {
            "id": "5d7359eb7362f9841eb9b7e1",
            "name": "Barron Smith",
            "telephoneNumber": "+61 468 000 000"
        },
        {
            "id": "5d7354cf7362f98326be5816",
            "name": "Tyron Boyd",
            "telephoneNumber": "+61 456 789 123"
        }
    ]
