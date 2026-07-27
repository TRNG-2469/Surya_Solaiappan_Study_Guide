-- Create the table first

select * from employee;
insert into employee (first_name, salary, department)
values
    ('Sara', 165000, 'Sales'),
    ('Tom', 72000, 'Marketing');

-- DQL --------------------------------------------------

-- All columns and rows
select * from employee;

-- one column
select first_name from employee;

-- alias the column name
select first_name as "First Name" from employee;

-- multiple columns
select first_name as "First Name", salary as "Base Salary" from employee;

-- filter rows
select first_name as "First Name", salary as "Base Salary" from employee
where department = 'Sales' and salary > 100000;