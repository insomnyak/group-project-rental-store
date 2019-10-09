# Rental Store REST Web Service Group Project

This project involves creating a simple database-backed REST web service for a rental store using Agile development techniques in a team setting. You are responsible for designing and documenting the REST API and implementing the controller, service layer, DAO, Java data objects, and unit tests for the application based on an existing database structure.

## Structure
Your solution must have the following structural elements:

* Your solution must be in an IntelliJ project called ```U1M6Summative```.
* Your project must be built using Spring Boot and Spring MVC. Initialize your project using ```start.spring.io```.
* Your solution must include a DAO, the utilized JdbcTemplates, and prepared statements.
* Your REST API must be documented with Swagger.
* Your REST API must accept and return data in JSON format where appropriate.
* You must implement ControllerAdvice to handle exceptions and return proper HTTP status codes and data when exceptions occur.

## Methodology

* You must manage your work in Pivotal Tracker.
* You must create stories and epics.
* You must estimate your work using story points.
* You must use a test-driven development approach (inluding Red/Green/Refactor) for your code.
* You must use JUnit for unit and integration tests.
* Your design should include a service layer and view model.
* Your unit test suite should use mock objects where appropriate.
* You should use JSR303 for input validation.

## Requirements/Features

* Your REST API must allow the end user to:
    * Perform standard CRUD operations for Customers
    * Perform standard CRUD operations for Items
    * Create and delete Invoices, including the associated Invoice Items
    * Find Invoices by Customer

You must use the following database structure:

```sql
create schema if not exists rental_store;
use rental_store;

create table if not exists customer (
	customer_id int(11) not null auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(75) not null,
    company varchar(50) not null,
    phone varchar(50) not null
);

create table if not exists item (
	item_id int(11) not null auto_increment primary key,
    name varchar(50) not null,
    description varchar(255),
    daily_rate decimal(8,2) not null
);

create table if not exists invoice (
	invoice_id int(11) not null auto_increment primary key,
    customer_id int(11) not null,
    order_date date not null,
    pickup_date date not null,
    return_date date not null,
    late_fee decimal(8,2) not null
);

create table if not exists invoice_item (
	invoice_item_id int(11) not null auto_increment primary key,
    invoice_id int(11) not null,
    item_id int(11) not null,
    quantity int(11) not null,
    unit_rate decimal(8,2) not null,
    discount decimal(8,2) not null
);

/* Foreign Keys: invoice */
alter table invoice add constraint fk_invoice_customer foreign key (customer_id) references customer(customer_id);

/* Foreign Keys: invoice_item */
alter table invoice_item add constraint fk_invoice_item_invoice foreign key (invoice_id) references invoice(invoice_id);
alter table invoice_item add constraint fk_invoice_item_item foreign key (item_id) references item(item_id);
```
