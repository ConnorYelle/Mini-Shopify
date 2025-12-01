# Mini-Shopify
Mini-Shopify Project for SYSC4806 Fall 2025

## About
The **Online Shop Management System** is a web application where merchants can create and manage virtual shops, upload products, and track inventory. Customers can search for shops, browse products, and simulate purchases. The system enforces inventory constraints to prevent over-purchasing.

## Table of Contents
- [About](#about)
- [Website Link](#website-link)
- [Usage](#usage)
- [Sprints](#sprints)
- [Milestone 2 Contributions](#milestone-2-contributions)
- [Contributors](#contributors)

## Website Link
https://team28-gsdzgjcha5f5aufu.canadacentral-01.azurewebsites.net/

## Usage
### Local
To run the project locally:
### Clone the repository
git clone https://github.com/ConnorYelle/Mini-Shopify.git

### Package the application
mvn package

### Execute the jar file
cd target
java -jar Mini-Shopify-1.0-SNAPSHOT.jar

## UML Diagram
This UML diagram illustrates the main entities (Store, Product, Customer, Order) and their relationships, along with the repositories and controllers.

![UML Diagram](docs/uml/mini-shopify-uml.png)

## Database Schema
![Schema](docs/db-schema/db-schema.png)

## Sprints
- Early prototype - November 3rd, 2025
- Alpha Release - November 17, 2025
- Final demo - December 1, 2025

## Milestone 3 Contributions
### Tommy Milestone 3 Contribution

### Owen Milestone 3 Contributions

### Jonas Milestone 3 Contributions
- Implemented the invoice generator for an order.
- Updated tests in Order and OrderController to reflect the changes.
- Connected my invoice genereator to the checkout.

### Connor Milestone 3 Contributions
- Implemented the customer and merchant homepages
- Linked buttons from the homepages to each different functionality of our website
- Added tests for both homepages

### Umniyah's Milestone 3 Contribution


## Contributors
- [Connor Yelle 101260641](https://github.com/ConnorYelle)
- [Tommy Phang 101211316](https://github.com/tphang46)
- [Owen McKibbon 101269794](https://github.com/OwenMcKibbon1)
- [Umniyah Mohammed 101158792](https://github.com/UMNIYAH)
- [Jonas Andaya 101224271](https://github.com/jonasandaya)

