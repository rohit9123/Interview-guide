-- PRACTICE: Isolation Levels
-- Note: To see these effects, you need TWO separate connection sessions (Terminal A and Terminal B).
-- You cannot run this entire script at once in a single window.

-- SETUP (Run this once)
DROP TABLE IF EXISTS inventory;
CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    product VARCHAR(50),
    quantity INT
);
INSERT INTO inventory (product, quantity) VALUES ('Laptop', 10);


-----------------------------------------------------------
-- EXPERIMENT 1: DIRTY READ (Not possible in Postgres)
-----------------------------------------------------------
-- PostgreSQL treats READ UNCOMMITTED as READ COMMITTED.
-- You will confirm that you CANNOT see uncommitted data.

-- SESSION A:
BEGIN;
UPDATE inventory SET quantity = 5 WHERE product = 'Laptop';
-- (Do not commit yet)

-- SESSION B:
BEGIN;
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
-- Even with this level, Postgres gives you Read Committed behavior.
SELECT * FROM inventory; 
-- Expected: quantity = 10 (Not 5)
COMMIT;

-- SESSION A:
COMMIT;


-----------------------------------------------------------
-- EXPERIMENT 2: NON-REPEATABLE READ (Default Behavior)
-----------------------------------------------------------
-- Occurs in READ COMMITTED level.
-- Reading the same row twice gives different results.

-- SESSION A:
BEGIN;
SELECT * FROM inventory WHERE product = 'Laptop'; 
-- Returns 5 (assuming Exp 1 committed 5)
-- (Hold this open...)

-- SESSION B:
BEGIN;
UPDATE inventory SET quantity = 0 WHERE product = 'Laptop';
COMMIT;

-- SESSION A:
-- Run the same select again
SELECT * FROM inventory WHERE product = 'Laptop';
-- Returns 0! The value changed mid-transaction.
COMMIT;


-----------------------------------------------------------
-- EXPERIMENT 3: REPEATABLE READ (Solving Exp 2)
-----------------------------------------------------------
-- Prevents Non-Repeatable Reads.

-- Reset Data
UPDATE inventory SET quantity = 10 WHERE product = 'Laptop';

-- SESSION A:
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT * FROM inventory WHERE product = 'Laptop'; 
-- Returns 10. Snapshot taken here!

-- SESSION B:
BEGIN;
UPDATE inventory SET quantity = 20 WHERE product = 'Laptop';
COMMIT;

-- SESSION A:
SELECT * FROM inventory WHERE product = 'Laptop'; 
-- Returns 10! (Old value). We are seeing the snapshot.
COMMIT;


-----------------------------------------------------------
-- EXPERIMENT 4: SERIALIZATION FAILURE
-----------------------------------------------------------
-- What happens if we try to write in Repeatable Read after someone else changed it?

-- Reset Data
UPDATE inventory SET quantity = 10 WHERE product = 'Laptop';

-- SESSION A:
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT * FROM inventory;

-- SESSION B:
UPDATE inventory SET quantity = 15 WHERE product = 'Laptop';
-- (Implicit commit if autocommit is on, or run explicit COMMIT)

-- SESSION A:
-- Try to update the same row
UPDATE inventory SET quantity = quantity - 1 WHERE product = 'Laptop';
-- RESULT: ERROR: could not serialize access due to concurrent update
ROLLBACK;
