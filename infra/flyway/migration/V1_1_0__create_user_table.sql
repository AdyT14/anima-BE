CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(64),
    email varchar(64) UNIQUE ,
    password varchar(128)
);
