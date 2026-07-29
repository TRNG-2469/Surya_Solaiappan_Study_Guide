create table CUSTOMERS(
customer_id INT primary key,
full_name VARCHAR(100) not null,
email_address VARCHAR(100) unique,
age SMALLINT CHECK (age > 0),
credit_balance DECIMAL(10,2),
registered_at TIMESTAMPTZ NOT NULL DEFAULT now());

SELECT column_name, data_type, character_maximum_length
FROM information_schema.columns
WHERE table_name = 'customers';