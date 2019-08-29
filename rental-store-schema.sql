create schema if not exists rental_store;
use rental_store;

create schema if not exists rental_store_test;
use rental_store_test;

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

select * from invoice;