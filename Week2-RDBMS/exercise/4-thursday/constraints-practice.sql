drop table loans;
drop table books;
drop table members;

CREATE TABLE books(
book_id SERIAL PRIMARY KEY,
title varchar(255) NOT NULL,
published_year INT CHECK (published_year
BETWEEN 1450 AND 2026
));  

create table members(
member_id int primary key,
membership_status varchar(20) default 'ACTIVE',
check (membership_status in ('ACTIVE', 'SUSPENDED', 'EXPIRED')));

create table loans(
book_id int references books(book_id) on delete cascade,
member_id int references members(member_id) on delete restrict);

insert into books (title, published_year)
values ('Clifford', 2005);

insert into books (title, published_year)
values ('Clifford', 1200);
-- SQL Error [23514]: ERROR: new row for relation "books" violates check constraint "books_published_year_check"
--  Detail: Failing row contains (2, Clifford, 1200). --
  
insert into members (member_id)
values (10);

select * from members where member_id = 10;

update  members set membership_status = 'BLOCKED' where member_id = 10;
 -- SQL Error [23514]: ERROR: new row for relation "members" violates check constraint "members_membership_status_check"
 --  Detail: Failing row contains (10, BLOCKED).

INSERT INTO loans (book_id, member_id) VALUES (1, 10);

SELECT * FROM loans;

DELETE FROM members WHERE member_id = 10;
-- SQL Error [23001]: ERROR: update or delete on table "members" violates RESTRICT setting of foreign key constraint "loans_member_id_fkey" on table "loans"
--   Detail: Key (member_id)=(10) is referenced from table "loans".

DELETE FROM books WHERE book_id = 1;

SELECT * FROM loans;