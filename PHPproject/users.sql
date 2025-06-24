create database projectdb;

use projectdb;

create table users (
    id int auto_increment primary key,
    username varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(255) not null
);
