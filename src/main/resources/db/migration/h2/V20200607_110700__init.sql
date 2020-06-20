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

create table users(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);