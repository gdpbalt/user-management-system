-- liquibase formatted sql

-- changeset <gdp>:<insert-in-roles>
INSERT INTO roles(id, name) VALUES(1, 'ADMIN');
INSERT INTO roles(id, name) VALUES(2, 'USER');

-- rollback delete from roles;
