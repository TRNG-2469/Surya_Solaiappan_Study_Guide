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

-- Seed initial data
INSERT INTO customers (full_name, email) VALUES 
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com');

INSERT INTO orders (order_id, customer_id, order_date, status, total_amount) VALUES
(5001, 1, '2026-07-01', 'PENDING', 250.00),
(5002, 1, '2026-07-10', 'COMPLETED', 120.00),
(5003, 2, '2026-07-12', 'PENDING', 45.00),
(5004, 2, '2026-06-15', 'COMPLETED', 300.00);
