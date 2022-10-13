create table user_info
(
    id          bigint auto_increment
        primary key,
    nickname    varchar(64)  null,
    gender      int          null comment '0-female,1-male',
    age         int          null,
    email       varchar(256) null,
    role_id     bigint       null,
    create_time datetime     null
);

INSERT INTO user_info (id, nickname, gender, age, email, role_id, create_time) VALUES (1, 'Admin', 1, 20, 'admin@email.com', 1, '2022-10-12 16:05:08');
INSERT INTO user_info (id, nickname, gender, age, email, role_id, create_time) VALUES (2, 'Rose', 0, 17, 'rose@email.com', 2, '2022-10-12 16:05:33');
INSERT INTO user_info (id, nickname, gender, age, email, role_id, create_time) VALUES (3, 'Alex', 1, 26, 'alex@email.com', 2, '2022-10-12 16:08:00');
INSERT INTO user_info (id, nickname, gender, age, email, role_id, create_time) VALUES (4, 'Mike', 1, 38, 'mike@email.com', 3, '2022-10-12 16:08:36');
INSERT INTO user_info (id, nickname, gender, age, email, role_id, create_time) VALUES (5, 'Paul', 1, 50, 'paul@email.com', 3, '2022-10-12 16:10:11');
INSERT INTO user_info (id, nickname, gender, age, email, role_id, create_time) VALUES (6, 'Jack', 1, 30, 'jack@email.com', 3, '2022-10-12 08:56:31');


create table role_info
(
    id          bigint auto_increment
        primary key,
    role_name   varchar(64) null,
    description text        null
);

INSERT INTO role_info (id, role_name, description) VALUES (1, 'admin', 'all permission');
INSERT INTO role_info (id, role_name, description) VALUES (2, 'vip', 'create and update');
INSERT INTO role_info (id, role_name, description) VALUES (3, 'guest', 'read only');


create table permission_info
(
    id              bigint auto_increment
        primary key,
    permission_name varchar(64) null,
    description     text        null
);

INSERT INTO permission_info (id, permission_name, description) VALUES (1, 'create', 'create resource');
INSERT INTO permission_info (id, permission_name, description) VALUES (2, 'read', 'query resource');
INSERT INTO permission_info (id, permission_name, description) VALUES (3, 'update', 'update resource');
INSERT INTO permission_info (id, permission_name, description) VALUES (4, 'delete', 'delete resource');


create table role_permission
(
    id            bigint auto_increment
        primary key,
    role_id       bigint null,
    permission_id bigint null
);

INSERT INTO role_permission (id, role_id, permission_id) VALUES (1, 1, 1);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (2, 1, 2);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (3, 1, 3);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (4, 1, 4);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (5, 2, 1);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (6, 2, 2);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (7, 2, 3);
INSERT INTO role_permission (id, role_id, permission_id) VALUES (8, 3, 2);