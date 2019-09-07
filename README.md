# Welcome to the Address Book RESTFul API Application By Tyron Boyd

This application is Build using Java 8 with Maven, Spring Boot with MongoDB.

Dependencies: MongoDB, Maven, Java 8

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

`POST /add-contact/`

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

`POST /add-contact/`

    curl -i --header "Content-Type: application/json" --request POST http://localhost:8080/delete/contact/5d731b817362f924fceaf6fd

### Response
    []

# Contact tyronboyd@gmail.com