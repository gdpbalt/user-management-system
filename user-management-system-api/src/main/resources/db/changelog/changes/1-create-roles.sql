-- liquibase formatted sql

-- changeset <gdp>:<create-roles>
CREATE TABLE IF NOT EXISTS roles
(
    id   bigint(11)   NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

-- rollback drop table roles;
