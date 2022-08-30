-- liquibase formatted sql

-- changeset <gdp>:<create-users-roles>
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id bigint(11) NOT NULL,
    role_id bigint(11) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT `users_roles_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `users_roles_role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- rollback drop table users_roles;
