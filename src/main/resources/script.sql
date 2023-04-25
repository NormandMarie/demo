create table admin
(
    id       int auto_increment
        primary key,
    username varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table categories
(
    categoriesID int auto_increment
        primary key,
    name         varchar(255) null,
    constraint unique_name
        unique (name)
);

create table category
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table car
(
    id          int auto_increment
        primary key,
    name        varchar(255)   not null,
    detail_text text           null,
    photo       varchar(255)   null,
    price       decimal(10, 2) not null,
    category_id int            not null,
    constraint car_ibfk_1
        foreign key (category_id) references category (id)
);

create index category_id
    on car (category_id);

create table post
(
    id          int auto_increment
        primary key,
    title       varchar(255) not null,
    author      varchar(255) not null,
    content     text         not null,
    category_id int          null,
    constraint fk_post_category
        foreign key (category_id) references categories (categoriesID)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(50)  not null,
    password varchar(255) not null
);


