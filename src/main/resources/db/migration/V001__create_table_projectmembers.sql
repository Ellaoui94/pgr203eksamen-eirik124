create table projectmembers (
    id serial primary key,
    first_name varchar(100) not null,
    last_name varchar(100),
    email varchar(100) unique
);