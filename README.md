# System ERP

App for company that manufactures steel elements to orders manage.

-----------------------------

## Table of contents
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)


-----------------------------

## Technologies
Backend is created with:
* Spring boot 3.0.6
* Java 17
* JUnit
* Mockito
  
Frontend is created with Angular 9.5.0.

## Features

* Register / login users by JWT
* Customers and orders database - create, edit, delete
* Order include deadline, product, items, quantity, price
* Work progress by pieces of finished product
* Days to deadline

To Do:

* Data validation in backend
* Adding a material purchasing department
* Managing shipments of finished order items

## Setup

To run frontend:
* clone repository: https://github.com/PoteralaMateusz/Frontend-SystemERP
* in main directory open terminal and run:

```
$ npm install
$ npm start
```

To run backend:

* download jar from install directory
* where you save jar, open terminal and run:

```
$ java -jar SystemERP-0.0.1-SNAPSHOT.jar
```

After that go to site: http://localhost:4200/

Default user:
* login - Mateusz
* password - 123