CREATE TABLE books
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  title       VARCHAR(250) NOT NULL,
  author      VARCHAR(250) NOT NULL,
  date        VARCHAR(250) NULL,
  description TEXT         NULL,
  picture     BLOB         NULL
);