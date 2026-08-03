create or replace FUNCTION MY_VARIABLE(inout myvariable
varchar(50))
returns varchar(50) as $$
begin
	myvariable:='hello';
end;
$$ language plpgsql;
CREATE TABLE inventory (
    item_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE inventory_audit_log (
    log_id SERIAL PRIMARY KEY,
    item_id INT NOT NULL,
    old_quantity INT,
    new_quantity INT,
    old_price DECIMAL(10,2),
    new_price DECIMAL(10,2),
    action_type VARCHAR(10) DEFAULT 'UPDATE',
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_inventory_update_func()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO inventory_audit_log (
        item_id,
        old_quantity,
        new_quantity,
        old_price,
        new_price,
        changed_at
    )
    VALUES (
        OLD.item_id,
        OLD.quantity,
        NEW.quantity,
        OLD.price,
        NEW.price,
        CURRENT_TIMESTAMP
    );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_inventory_update
AFTER UPDATE ON inventory
FOR EACH ROW
EXECUTE FUNCTION log_inventory_update_func();

INSERT INTO inventory (item_id, name, price, quantity)
VALUES (101, ' Keyboard', 79.99, 25);

UPDATE inventory
SET price = 69.99, quantity = 20
WHERE item_id = 101;

SELECT * FROM inventory_audit_log;