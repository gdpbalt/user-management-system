-- liquibase formatted sql

-- changeset <gdp>:<create-users>
CREATE TABLE IF NOT EXISTS users
(
    id         bigint(11)   NOT NULL AUTO_INCREMENT,
    name       varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    role_id    bigint(11)   NOT NULL,
    status     varchar(255) NOT NULL,
    created_at timestamp    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name),
    CONSTRAINT users_role_fk FOREIGN KEY (role_id) REFERENCES roles (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- rollback drop table users;
