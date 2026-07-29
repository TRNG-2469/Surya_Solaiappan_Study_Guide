-- DDL Script: Write your tables setup statements here
create table books(
book_id INT primary key,
title varchar(50),
author varchar(50));

create table members(
member_id INT primary key,
full_name varchar(50),
email varchar(50) unique);

create table loans(
member_id INT REFERENCES members(member_id),
book_id INT REFERENCES books(book_id),
loan_date DATE,
PRIMARY KEY (member_id, book_id, loan_date));

INSERT INTO books VALUES (101, 'The Hobbit', 'J.R.R. Tolkien'), 
(102, '1984', 'George Orwell');

INSERT INTO members VALUES (5001,
'John Doe', 'john@email.com'), 
(5002, 'Jane Smith', 'jane@email.com');

INSERT INTO loans (member_id, book_id, loan_date)
VALUES (9999, 101, '2026-07-12');

INSERT INTO members VALUES (
5003, 'Alex Jones', 'john@email.com');

