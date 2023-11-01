create table if not exists users(
    id bigserial primary key ,
    login varchar(20) unique not null ,
    password varchar(50) not null ,
    email varchar unique not null ,
    date_create timestamp with time zone default now()
    );