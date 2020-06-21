create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(500) not null,
    enabled boolean not null);

create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

insert into users (username, password, enabled)
values ('admin', '$2a$10$E7tZKAKc2KpiOlUgVmVUKO5nUSGdQptuJJG48Nw78niYIFRguWcXu', true);
insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
