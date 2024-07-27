DROP SCHEMA IF EXISTS customer CASCADE;

CREATE SCHEMA customer;

CREATE TABLE customer.customers (
    id uuid primary key,
    username varchar(255) COLLATE pg_catalog."default"  not null,
    first_name varchar(255) COLLATE pg_catalog."default" not null,
    last_name varchar(255) COLLATE pg_catalog."default" not null
);

