# E-Commerce Backend API

A complete E-Commerce Backend application built using Spring Boot.

## Features

* User Registration & Authentication
* JWT Authentication & Authorization
* Role-Based Access Control
* Product Management
* Category Management
* Shopping Cart
* Order Creation
* Order Items Management
* Stock Quantity Update
* MySQL Database Integration
* RESTful APIs
* Validation & Exception Handling

## Technologies Used

* Java
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* JWT
* MySQL
* Maven
* REST API

## Database Design

Main Entities:

* Users
* Products
* Categories
* Orders
* Order Items

Relationships:

* User → Orders (One-to-Many)
* Order → Order Items (One-to-Many)
* Product → Order Items (One-to-Many)
* Category → Products (One-to-Many)

## API Endpoints

### Authentication

* POST /auth/register
* POST /auth/login

### Products

* GET /products
* GET /products/{id}
* POST /products
* PUT /products/{id}
* DELETE /products/{id}

### Orders

* POST /orders
* GET /orders
* GET /orders/{id}

## Security

This project uses JWT Authentication to secure APIs and manage user access.

## Future Improvements

* Shopping Cart API
* Payment Integration
* Product Images Upload
* Order Tracking
* Docker Deployment

## Author

Ziad Ahmed

GitHub:
https://github.com/ziadahmedcs
