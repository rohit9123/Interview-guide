-- PRACTICE: Advanced Data Types (JSONB, Arrays, ENUM)

/* 
 * SCENARIO: E-Commerce Catalog
 * We need to store products where:
 * 1. `status` is limited to specific values (ENUM).
 * 2. `tags` is a list of keywords (ARRAY).
 * 3. `details` varies product by product (JSONB).
 * 
 * INSTRUCTIONS:
 * 1. Create the ENUM type.
 * 2. Create the table.
 * 3. Insert data.
 * 4. Query using specialized operators.
 */

-- 1. Setup Enum
DROP TYPE IF EXISTS product_status CASCADE;
CREATE TYPE product_status AS ENUM ('draft', 'active', 'archived');

-- 2. Setup Table
DROP TABLE IF EXISTS electronic_products;
CREATE TABLE electronic_products (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    tags TEXT[],
    specs JSONB,
    status product_status DEFAULT 'draft'
);

-- 3. Insert Data
INSERT INTO electronic_products (name, tags, specs, status) VALUES
(
    'Smartphone X', 
    ARRAY['electronics', 'mobile', 'sale'], 
    '{"brand": "TechCorp", "screen": "6inch", "battery": 4000, "colors": ["black", "blue"]}',
    'active'
),
(
    'Laptop Pro', 
    ARRAY['electronics', 'computer'], 
    '{"brand": "TechCorp", "cpu": "i7", "ram": "16GB"}',
    'active'
),
(
    'Budget Tablet', 
    ARRAY['electronics', 'tablet'], 
    '{"brand": "BudgetCo", "screen": "10inch"}',
    'draft'
);

SELECT * FROM electronic_products;

-----------------------------------------------------------
-- EXERCISE 1: ARRAY Queries
-----------------------------------------------------------
-- Find all products that have the tag 'mobile'
-- Tip: Use the ANY op OR the @> (contains) op
SELECT name, tags FROM electronic_products 
WHERE 'mobile' = ANY(tags);

-- Find products that have BOTH 'electronics' AND 'sale' tags
SELECT name, tags FROM electronic_products 
WHERE tags @> ARRAY['electronics', 'sale'];


-----------------------------------------------------------
-- EXERCISE 2: JSONB Queries
-----------------------------------------------------------
-- Find all products where the brand is 'TechCorp'
-- use ->> operator to get text
SELECT name, specs FROM electronic_products 
WHERE specs ->> 'brand' = 'TechCorp';

-- Find all products that have a 'battery' key in specs
-- use ? operator
SELECT name FROM electronic_products 
WHERE specs ? 'battery';

-----------------------------------------------------------
-- EXERCISE 3: ENUM Validation
-----------------------------------------------------------
-- Try to insert a row with an invalid status 'sold_out'
-- It should fail immediately.

-- UNCOMMENT TO TEST ERROR:
-- INSERT INTO electronic_products (name, status) VALUES ('Bad Item', 'sold_out');

-- Update status of 'Budget Tablet' to 'archived'
UPDATE electronic_products SET status = 'archived' WHERE name = 'Budget Tablet';
SELECT * FROM electronic_products;
