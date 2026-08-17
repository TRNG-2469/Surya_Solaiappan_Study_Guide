-- Advanced SQL Objects: Views, Triggers, and Stored Procedures (PostgreSQL)
 
-- PART A: schema setup
DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS customer_audit_log CASCADE;
 
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
 
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id) ON DELETE CASCADE,
    order_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL, -- 'PENDING', 'COMPLETED', 'ARCHIVED'
    total_amount DECIMAL(10, 2) DEFAULT 0.00
);
 
CREATE TABLE customer_audit_log (
    log_id SERIAL PRIMARY KEY,
    customer_id INT,
    old_name VARCHAR(100),
    new_name VARCHAR(100),
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
 
-- seed data
INSERT INTO customers (full_name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com');
 
-- 5002/5004 are COMPLETED on purpose, for testing the archive proc later
INSERT INTO orders (order_id, customer_id, order_date, status, total_amount) VALUES
(5001, 1, '2026-07-01', 'PENDING', 250.00),
(5002, 1, '2026-07-10', 'COMPLETED', 120.00),
(5003, 2, '2026-07-12', 'PENDING', 45.00),
(5004, 2, '2026-06-15', 'COMPLETED', 300.00);
 
 
-- TASK 1: view for pending orders, saves everyone repeating the join
DROP VIEW IF EXISTS v_active_orders;
 
CREATE VIEW v_active_orders AS
SELECT
    o.order_id,
    o.order_date,
    o.total_amount,
    c.full_name,
    c.email
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
WHERE o.status = 'PENDING';
 
-- should return order 5001 (John) and 5003 (Jane)
SELECT * FROM v_active_orders;
 
 
-- TASK 2: trigger to log customer name changes
CREATE OR REPLACE FUNCTION fn_log_customer_name_change()
RETURNS TRIGGER AS $$
BEGIN
    -- IS DISTINCT FROM handles NULLs, unlike a plain !=
    IF NEW.full_name IS DISTINCT FROM OLD.full_name THEN
        INSERT INTO customer_audit_log (customer_id, old_name, new_name)
        VALUES (OLD.customer_id, OLD.full_name, NEW.full_name);
    END IF;
 
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
 
DROP TRIGGER IF EXISTS trg_log_customer_name_change ON customers;
 
CREATE TRIGGER trg_log_customer_name_change
AFTER UPDATE ON customers
FOR EACH ROW
EXECUTE FUNCTION fn_log_customer_name_change();
 
-- rename John Doe to trigger the log
UPDATE customers
SET full_name = 'Johnathan Doe'
WHERE full_name = 'John Doe';
 
-- should show one row now: John Doe -> Johnathan Doe
SELECT * FROM customer_audit_log;
 
-- email-only change, should NOT log
UPDATE customers
SET email = 'johnathan.doe@example.com'
WHERE full_name = 'Johnathan Doe';
 
SELECT * FROM customer_audit_log; -- still just 1 row
 
 
-- TASK 3: procedure to archive old completed orders
CREATE OR REPLACE PROCEDURE pr_archive_old_orders(cutoff_date DATE)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE orders
    SET status = 'ARCHIVED'
    WHERE status = 'COMPLETED'
      AND order_date < cutoff_date;
 
    COMMIT; -- only works via top-level CALL, not inside another transaction
END;
$$;
 
-- archive anything completed before July 5th
CALL pr_archive_old_orders('2026-07-05');
 
-- 5004 (06-15) should flip to ARCHIVED, 5002 (07-10) stays COMPLETED
SELECT order_id, order_date, status FROM orders ORDER BY order_id;