# ACID Properties

**ACID** stands for **Atomicity**, **Consistency**, **Isolation**, and **Durability**. These are the standard properties that guarantee reliable processing of database transactions.

In PostgreSQL, every SQL statement executes in a transaction. If you don't explicitly start one, Postgres wraps the statement in a single-statement transaction.

---

## 1. Atomicity (All or Nothing)
Atomicity guarantees that a transaction is treated as a single unit. Either **all** the queries in the transaction execute successfully, or **none** of them do. If a failure occurs halfway (e.g., power failure, constraint violation), the database rolls back to the state before the transaction started.

**Keyword**: `COMMIT`, `ROLLBACK`

**Example**: Transferring money.
-   Deduct $100 from Alice.
-   Add $100 to Bob.
-   *If the second step fails, the first step must be undone.*

---

## 2. Consistency (Rules Validation)
Consistency ensures that a transaction takes the database from one valid state to another valid state. The database must satisfy all defined rules:
-   Constraints (Primary Key, Foreign Key, Unique, Check, Not Null)
-   Triggers
-   Cascades

If a transaction violates any of these, the entire transaction is rolled back.

**Example**:
-   A table has a constraint `CHECK (balance >= 0)`.
-   If a transaction tries to deduct money such that `balance` becomes -50, the database rejects the change to maintain consistency.

---

## 3. Isolation (Concurrency Control)
Isolation ensures that concurrently executing transactions do not interfere with each other. The intermediate state of a transaction should generally not be visible to other transactions until it is committed.

PostgreSQL uses **MVCC (Multi-Version Concurrency Control)** to achieve this without locking the entire table for reads.

**Levels**:
-   Read Uncommitted (Postgres treats this as Read Committed)
-   Read Committed (Default)
-   Repeatable Read
-   Serializable

*We will cover Isolation Levels in depth in the next topic.*

---

## 4. Durability (Permanence)
Durability guarantees that once a transaction has been committed, it remains committed even in the case of a system failure (e.g., power outage, crash).

PostgreSQL achieves this using **WAL (Write-Ahead Logging)**. Changes are written to a log file on the disk *before* they are written to the main data files. If the system crashes, Postgres can replay the log to restore the data.

---

## Summary Table

| Property | Meaning | Mechanism in Postgres |
| :--- | :--- | :--- |
| **Atomicity** | All or nothing. | Transaction Management (`BEGIN`, `ROLLBACK`) |
| **Consistency** | Valid state to valid state. | Constraints, Data Types, Triggers |
| **Isolation** | Independent execution. | MVCC, Locks, Transaction Isolation Levels |
| **Durability** | Committed data is saved. | WAL (Write-Ahead Logs) |
