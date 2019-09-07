# Welcome to the Address Book RESTFul API Application By Tyron Boyd

This application is Build using Java 8 with Maven, Spring Boot with MongoDB.

## Install

    mvn clean intstall -U

## Run the app

    spring-boot:run

## Run the tests

    mvn surefire:test

# REST API

## Get list Contacts

### Request

`GET /contacts/`

    curl -i -H 'Accept: application/json' http://localhost:8080/contacts/

### Response
    [
        {
            "id": "5d731b817362f924fceaf6fd",
            "name": "Barron Smith",
            "telephoneNumber": "+61 468 422 558"
        }
    ]
