# PostgreSQL Data Types

PostgreSQL supports rich data types beyond standard SQL (INT, VARCHAR). The most powerful ones are **JSONB**, **Arrays**, and **Enums**.

## 1. JSONB vs JSON
Postgres supports two JSON types:
-   **JSON**: Stored as exact text copy. Fast to ingest, slow to query (must re-parse every time). Preserves whitespace/ordering.
-   **JSONB (Binary)**: Stored in decomposed binary format. Slower to insert, **much faster to query**. Supports indexing (GIN). **Always use JSONB** unless you have a specific reason not to.

### Common JSONB Operators
-   `->` : Get element by key/index (returns JSON object/array)
-   `->>` : Get element as TEXT
-   `@>` : Contains (Top-level containment check)
-   `?` : Key existence check

## 2. Arrays
Postgres allows columns to be arrays of variable length. This avoids creating many-to-many join tables for simple lists.

-   Definition: `TEXT[]`, `INTEGER[]`
-   Access: `tags[1]` (Arrays are **1-indexed** in SQL!)
-   Operators:
    -   `@>` : Contains
    -   `&&` : Overlap (have elements in common)
    -   `unnest(arr)` : Expand array into rows

## 3. Enumerated Types (ENUM)
A static, ordered set of values. Useful for states (pending, active, archived).
-   Better data integrity than VARCHAR (can't insert "actve" typo).
-   More readable than INT codes (0, 1, 2).

```sql
CREATE TYPE status AS ENUM ('open', 'closed', 'archived');
```

## 4. UUID
Universally Unique Identifier. Better than SERIAL (Auto-increment integer) for distributed systems because simple integers are predictable and hard to merge from different databases.

---

## Example Table Definition
```sql
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name TEXT,
    attributes JSONB,       -- {"color": "red", "weight": 5}
    tags TEXT[],           -- ['sale', 'summer']
    status product_status   -- ENUM('active', 'discontinued')
);
```
