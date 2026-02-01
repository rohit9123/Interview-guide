-- PRACTICE: ACID Properties in Action

/*
 * SCENARIO: Banking System
 * We have a `accounts` table. 
 * We want to transfer money from Alice to Bob.
 * 
 * INSTRUCTIONS:
 * 1. Create the `accounts` table with constraints.
 * 2. Insert initial data.
 * 3. Demonstrate ATOMICITY by simulating a failure.
 * 4. Demonstrate CONSISTENCY by violating a constraint.
 */

-- Setup (Run this block first)
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(10,2) CHECK (balance >= 0) -- Consistency Rule: Balance cannot be negative
);

INSERT INTO accounts (name, balance) VALUES 
('Alice', 1000.00),
('Bob', 500.00);

SELECT * FROM accounts;

-------------------------------------------------------------
-- EXERCISE 1: ATOMICITY (The "All or Nothing" Rule)
-------------------------------------------------------------
-- Try to transfer $200 from Alice to Bob, but imagine power fails 
-- (or division by zero error occurs) after the deduction but before the addition.

BEGIN;

-- Step 1: Deduct from Alice
UPDATE accounts SET balance = balance - 200 WHERE name = 'Alice';

-- Step 2: Simulate an error (Uncomment ONE of the lines below to see effect)
-- SELECT 1/0; -- This will cause a runtime error
-- ROLLBACK;    -- This simulates a manual cancellation

-- Step 3: Add to Bob (This won't be reached if error occurs above)
UPDATE accounts SET balance = balance + 200 WHERE name = 'Bob';

COMMIT; -- If error happened, this commit does nothing, whole transaction rolls back.

-- CHECK RESULT: 
-- If successful: Alice=800, Bob=700
-- If failed/rollback: Alice=1000, Bob=500 (Clean state, no partial update)
SELECT * FROM accounts;


-------------------------------------------------------------
-- EXERCISE 2: CONSISTENCY (The "Rules" Rule)
-------------------------------------------------------------
-- Try to transfer $1200 from Alice to Bob.
-- Alice only has $1000. This should violate the CHECK (balance >= 0) constraint.
-- The transaction should fail completely.

BEGIN;

-- Step 1: Deduct $1200 from Alice
-- This statement will throw a Constraint Violation Error
UPDATE accounts SET balance = balance - 1200 WHERE name = 'Alice';

-- Step 2: Add to Bob
-- Postgres will likely abort the transaction immediately after Step 1 fails.
-- Any further commands are ignored until ROLLBACK.
UPDATE accounts SET balance = balance + 1200 WHERE name = 'Bob';

COMMIT; -- Will fail because transaction is aborted.

-- CHECK RESULT: Alice should still have her original balance.
SELECT * FROM accounts;
