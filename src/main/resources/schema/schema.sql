DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
    name VARCHAR(50) NOT NULL COMMENT '이름'
);
