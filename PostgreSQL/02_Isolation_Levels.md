# Transaction Isolation Levels

Isolation ensures that concurrent transactions don't interfere with each other. The SQL standard defines 4 levels of isolation, each addressing specific **read phenomena** (anomalies).

## Common Concurrency Anomalies

1.  **Dirty Read**: Reading uncommitted data from another transaction. (If that transaction rolls back, you read data that never existed).
2.  **Non-Repeatable Read**: Reading the same row twice in a transaction and getting different results (because someone else updated it).
3.  **Phantom Read**: running the same query twice (e.g., `COUNT(*)`) and getting a different set of rows (because someone else inserted/deleted rows).
4.  **Serialization Anomaly**: The result of concurrent transactions is not consistent with *any* serial order of execution.

---

## PostgreSQL Isolation Levels

PostgreSQL is built on **MVCC (Multi-Version Concurrency Control)**, so it is "stricter" than the standard requires.

| Isolation Level | Dirty Read | Non-Repeatable Read | Phantom Read | Serialization Anomaly |
| :--- | :--- | :--- | :--- | :--- |
| **Read Uncommitted** | Used to be allowed | Possible | Possible | Possible |
| **Read Committed** | **Impossible** (PG Default) | Possible | Possible | Possible |
| **Repeatable Read** | Impossible | **Impossible** | **Impossible** ( Unique to PG) | Possible |
| **Serializable** | Impossible | Impossible | Impossible | **Impossible** |

> **Note on Read Uncommitted**: In PostgreSQL, `READ UNCOMMITTED` behaves exactly like `READ COMMITTED`. Postgres never allows Dirty Reads.

> **Note on Repeatable Read**: The SQL standard allows Phantom Reads in `REPEATABLE READ`. However, PostgreSQL's implementation prevents Phantom Reads here too.

---

## 1. Read Committed (Default)
-   **Concept**: You only see data that was committed at the *start of your query*.
-   **Behavior**: If you run the same query twice in one transaction, and someone else commits a change in between, you **will** see the new data.
-   **Use Case**: Standard web apps where seeing fresh data is good.

## 2. Repeatable Read
-   **Concept**: You only see data that was committed at the *start of your transaction*.
-   **Snapshot**: Think of it as taking a photo of the database when you say `BEGIN`. You work with that photo.
-   **Behavior**: Even if someone else deletes a row you are looking at, you still see it.
-   **Error**: If you try to update a row that someone else changed since your snapshot, Postgres throws: `ERROR: could not serialize access due to concurrent update`.

## 3. Serializable
-   **Concept**: Strict consistency. Guarantees that the result is exactly as if transactions were executed one after another (serially).
-   **Behavior**: Uses "Predicate Locks" to monitor not just rows, but "gaps" where rows might appear.
-   **Cost**: High performance overhead. Often requires retry logic in your application because serialization failures are common.

---

## Setting the Level
```sql
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
-- Your queries...
COMMIT;
```
