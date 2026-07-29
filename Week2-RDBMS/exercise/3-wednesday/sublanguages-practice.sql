select * from books;
-- DDL Statement here
alter table BOOKS
add category varchar(30);

-- DML Statement here
insert into MEMBERS VALUES(5004, 'Jason Carpenter', 
'jason@email.com');

-- DQL Statement here
select * from BOOKS where AUTHOR = 'J.R.R. Tolkien';

-- TCL Script here
begin;
insert into members values(
5003, 'bob carpenter', 'bob@email.com');
savepoint insert_check;
insert into members values(5005,
'jack carpenter', 'jack@email.com');
rollback to savepoint insert_check;
commit;

-- DCL Statement here
CREATE ROLE guest_reader;
grant select on books to guest_reader;
