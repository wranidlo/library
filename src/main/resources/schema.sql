create schema jpa_users;

DROP TABLE IF EXISTS jpa_users;
CREATE TABLE jpa_users.USER (
                                         id INT AUTO_INCREMENT  PRIMARY KEY,
                                         firstName VARCHAR(250),
                                         lastName VARCHAR(250),
                                         accountType VARCHAR(250)
);