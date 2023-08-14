create table if not exists public.users(
  id bigserial primary key ,
  login varchar unique not null ,
  email varchar unique not null ,
  password varchar  not null ,
  active varchar ,
  date_create timestamp with time zone default now(),
  code_activities varchar unique
);

create table if not exists public.roles(
    id bigserial primary key ,
    role_title varchar unique not null
);

create table if not exists public.m2m_user_role(
    user_id bigint references users(id),
    role_id bigint references  roles(id),
    unique (user_id, role_id)
);

