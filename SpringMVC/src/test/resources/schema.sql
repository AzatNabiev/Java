DROP TABLE IF EXISTS first_name;
DROP TABLE IF EXISTS last_name;
DROP TABLE IF EXISTS age;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS password;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR (20),
    last_name    VARCHAR (20),
    age          INTEGER ,
    email        VARCHAR(40),
    password     VARCHAR(100),
    role         INT
);
