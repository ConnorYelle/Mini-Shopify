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

## Milestone 2 Contributions
### Tommy Milestone 2 Contribution
- Implemented Home Page, allowing adding store
- Added a Owner dashboard allowing the owner to edit their store, such as changing the name of the store, description
- Added Owner dashboard tests
- changed in-memory H2 database to persistent so store don't disappear when restarting SpringBoot
- added search button
- Fix bugs in dashboard and products
### Owen Milestone 2 Contributions
- Implemented Product page, allowing adding of products, viewing of products
- Fixed bugs to allow products to persist to each store
- Fixed bug with database to allow for id generation
- Fixed tests for Product Controller
- Added methods to Product Controller
### Jonas Milestone 2 Contributions
- Implemented Store page which shows the the store and its products
- Made the DB schema
- Added a test for SupaBase


## Contributors
- [Connor Yelle 101260641](https://github.com/ConnorYelle)
- [Tommy Phang 101211316](https://github.com/tphang46)
- [Owen McKibbon 101269794](https://github.com/OwenMcKibbon1)
- [Umniyah Mohammed 101158792](https://github.com/UMNIYAH)
- [Jonas Andaya 101224271](https://github.com/jonasandaya)

