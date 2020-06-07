create table addresses (
    id binary not null,
    city varchar(255),
    country varchar(255),
    street varchar(255),
    zip_code varchar(255),
    primary key (id));

create table customers (
    customer_type varchar(31) not null,
    id binary not null,
    name varchar(255),
    tax_id varchar(255),
    vat_number varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    primary key (id));

create table customers_addresses (
    customer_id binary not null,
    addresses_id binary not null);

