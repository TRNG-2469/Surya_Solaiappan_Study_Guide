select * from orders;
select * from customers;
select * from products;
select * from order_items;

select COUNT(ORDER_ID), AVG(amount) from ORDERS;

select customer_id, name, count(order_id) as total_orders
from ORDERS
join CUSTOMERS using(customer_id)
group by customer_id, name
order by total_orders DESC;

select customer_id, name from
(Select customer_id from orders 
group by customer_id
having SUM(AMOUNT) > 150) A join CUSTOMERS
using (customer_id);

select min(price), max(price), avg(price), COUNT(distinct name) 
from PRODUCTS;

select name, sum(quantity) as quantity from products 
join order_items using
(product_id) 
group by name
order by quantity desc
limit 3;


