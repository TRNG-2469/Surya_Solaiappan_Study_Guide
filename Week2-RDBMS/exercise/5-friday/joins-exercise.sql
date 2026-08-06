select * from BOOKS;
select * from members;
select * from loans;

select name, title, loan_date from loans l
inner join members m on l.member_id = m.id
inner join books b on l.book_id = b.book_id
where return_date IS null and membership_status = 'active';

select name, return_date from members left join loans on member_id=id;

select name, return_date from members left join loans on member_id=id
where loan_date is NULL;

select name, title, author, loan_date from loans l
inner join members m on l.member_id = m.id
inner join books b on l.book_id = b.book_id;

select name, email, title, return_date from loans 
inner join books using(book_id)
inner join members ON id=member_id
where loan_date < '2026-07-01' and return_date IS null;

