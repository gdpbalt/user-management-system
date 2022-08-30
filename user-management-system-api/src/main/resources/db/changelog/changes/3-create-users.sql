-- liquibase formatted sql

-- changeset <gdp>:<create-users>
CREATE TABLE IF NOT EXISTS users
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    name       varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    status     varchar(255) NOT NULL,
    created_at timestamp    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

-- rollback drop table users;
