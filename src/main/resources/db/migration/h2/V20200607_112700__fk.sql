alter table customers_addresses
    add constraint UK_9jc324obdu44atlhb0iifw47u unique (addresses_id);

alter table customers_addresses
    add constraint FKr08vqw1090ujf8lg64q046ju2
    foreign key (addresses_id) references addresses;

alter table customers_addresses
    add constraint FKpsc9hohm5l7tcwi8yjho5ef9o
    foreign key (customer_id) references customers;
