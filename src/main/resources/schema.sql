create schema jpa_users;

DROP TABLE IF EXISTS jpa_users.USER;
CREATE TABLE jpa_users.USER (
                                         id INT IDENTITY PRIMARY KEY,
                                         firstName VARCHAR(250),
                                         lastName VARCHAR(250),
                                         accountType VARCHAR(250)
);

create schema jpa_books;

DROP TABLE IF EXISTS jpa_books.BOOK;
CREATE TABLE jpa_books.BOOK (
                                id INT IDENTITY PRIMARY KEY,
                                title VARCHAR(250),
                                author VARCHAR(250),
                                borrowedBy INT
);