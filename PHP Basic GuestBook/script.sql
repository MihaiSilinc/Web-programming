DROP TABLE Users
DROP TABLE Administrators
DROP TABLE GuestBook

CREATE TABLE Users(
    id int primary key,
    username varchar(50),
    password varchar(20)
);

CREATE TABLE Administrators(
    id int primary key,
    username varchar(50),
    password varchar(20)
);


CREATE TABLE GuestBook(
    id int primary key IDENTITY (1,1),
    author varchar(255),
    title varchar(25),
    comment varchar(25),
    posted_on varchar(16)
);