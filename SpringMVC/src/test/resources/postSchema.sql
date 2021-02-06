DROP TABLE IF EXISTS content;
DROP TABLE IF EXISTS user_id;

CREATE TABLE user_content
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR (256),
    user_id INT
);