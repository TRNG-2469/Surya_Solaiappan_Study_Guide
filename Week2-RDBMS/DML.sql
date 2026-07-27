-- DML insert update and delete, need table first
create table customers(
customer_id  int primary key,
name varchar(50) not null,
membership_tier varchar(20) default 'BRONZE'
);

insert into customers(customer_id, name,membership_tier)
values 	(1, 'Alice', 'silver');

select * from customers;

insert into customers VALUES
(2, 'Mark', 'platinum'),
(3, 'bob', 'silver');

insert into customers(customer_id, name)
values (4, 'Jane');

update customers 
set membership_tier ='silver',
 name = 'bobb'
where customer_id = 4;

select * from customers;

delete from customers where customer_id=1;