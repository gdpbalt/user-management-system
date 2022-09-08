-- liquibase formatted sql

-- changeset <gdp>:<create-refresh-token>
CREATE TABLE IF NOT EXISTS refresh_tokens
(
    id          bigint(11) NOT NULL AUTO_INCREMENT,
    user_id     bigint(11) NOT NULL,
    token       varchar(255) NOT NULL,
    expiry_date timestamp    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (token),
    CONSTRAINT refresh_tokens_user_fk FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- rollback drop table refresh_tokens;
