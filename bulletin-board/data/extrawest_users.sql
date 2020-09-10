create table users
(
    id       bigint auto_increment
        primary key,
    username varchar(255) null,
    email    varchar(50)  null,
    phone    varchar(50)  null,
    password varchar(255) null,
    constraint UK6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (1, 'admin', 'admin@extrawes-test.com', '+1 713.652.5151', '$2a$10$343nY8Vmn1lHMqSaCeLlWeF.1YlUe/EQb4Bjjtdu4XsKzXkOhT2.6');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (2, 'alexandr137', 'alex137@gmail.com', '+1 727.502.8200', '$2a$10$0DTjSww4miJ22TFMOWJvjulF.un0IdbmHyoCD2D5W2dRwc1WkKvIu');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (3, 'sergey12', 'sergey.petrov@yahoo.com', null, '$2a$10$z56YfJRBSIYDb1GMY7YrVOT30ub3U9QM.uDZaG/vCUDxBJn9ecW5C');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (4, 'denis.ivanov', 'deniv.ivanov175@yahoo.com', '+1 803.212.4978', '$2a$10$xpNhM5Se0NGp7HmoShzaye.YRP1k2V4mu08xjC5quc5kTGzDyhtu2');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (5, 'nick', 'nick@gmail.de', null, '$2a$10$XvD/rkzuLgVrPMXf/cwYAeLU5avCQ8.a14K1xOkV5RGt6EfXMB/lm');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (6, 'andrey98', 'andrey98@gmail.com', '+1 202.677.4046', '$2a$10$1jtoZWGS3Jxla7DVfS6speMnYEKSwvsURxxTTNzxA6KLQ.gjS0QFC');