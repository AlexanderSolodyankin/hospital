alter table if exists  users
    add active boolean default false;
alter table if exists  users
    rename column password to user_password;