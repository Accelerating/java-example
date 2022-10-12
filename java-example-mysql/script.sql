create table user_info
(
    id          bigint auto_increment
        primary key,
    nickname    varchar(64)  null,
    gender      int          null comment '0-female,1-male',
    age         int          null,
    email       varchar(256) null,
    create_time datetime     null
);

INSERT INTO user_info (nickname, gender, age, email, create_time) VALUES ('Jack', 1, 18, 'jack@email.com', '2022-10-12 16:05:08');
INSERT INTO user_info (nickname, gender, age, email, create_time) VALUES ('Rose', 0, 17, 'rose@email.com', '2022-10-12 16:05:33');
INSERT INTO user_info (nickname, gender, age, email, create_time) VALUES ('Alex', 1, 26, 'alex@email.com', '2022-10-12 16:08:00');
INSERT INTO user_info (nickname, gender, age, email, create_time) VALUES ('Mike', 1, 38, 'mike@email.com', '2022-10-12 16:08:36');
INSERT INTO user_info (nickname, gender, age, email, create_time) VALUES ('Paul', 1, 50, 'paul@email.com', '2022-10-12 16:10:11');