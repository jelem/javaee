CREATE DATABASE bookshopdb;
CREATE TABLE books (
  id           BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title        VARCHAR(255)   NOT NULL,
  author       VARCHAR(255)   NOT NULL,
  description  TEXT,
  illustration BLOB,
  price        NUMERIC(15, 2) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;