select name from loans l
inner join members m on l.member_id = m.id
inner join books b on l.book_id = b.book_id;

SELECT name 
FROM members 
WHERE id IN (
    SELECT member_id 
    FROM loans
);

select name, return_date from members left join loans on member_id=id
where loan_date is NULL;

SELECT m.name
FROM members m
WHERE NOT EXISTS (
    SELECT *
    FROM loans l 
    WHERE l.member_id = m.id
);
