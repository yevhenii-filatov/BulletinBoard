create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id) references roles (id),
    constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id) references users (id)
);

INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (4, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (5, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (6, 3);