create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(20) null
);

INSERT INTO extrawest.roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO extrawest.roles (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO extrawest.roles (id, name) VALUES (3, 'ROLE_USER');