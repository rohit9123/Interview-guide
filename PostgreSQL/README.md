# PostgreSQL Mastery Roadmap

**Goal**: Go beyond CRUD to understand database internals, performance tuning, and advanced SQL.

## Phase 1: Relational Foundations
- [x] ** ACID Properties**: Atomicity, Consistency, Isolation, Durability
- [x] **Isolation Levels**: Read Uncommitted, Read Committed, Repeatable Read, Serializable
- [x] **Data Types**: JSONB vs JSON, HSTORE, Arrays, Enums
- [ ] **Relational Schema Design**: Normalization (1NF, 2NF, 3NF, BCNF)

## Phase 2: Querying & Advanced SQL
- [ ] **Window Functions**: ROW_NUMBER, RANK, LEAD, LAG, OVER
- [ ] **Common Table Expressions (CTEs)**: Recursive CTEs
- [ ] **Joins**: Nested Loop, Hash Join, Merge Join
- [ ] **Stored Procedures & Functions (PL/pgSQL)**
- [ ] **Triggers & Views** (Regular vs Materialized Views)

## Phase 3: Indexing Strategies
- [ ] **B-Tree Indexes**: When to use and how they work
- [ ] **GIN & GiST Indexes**: Full-text search and Geometric data
- [ ] **Partial & Expression Indexes**
- [ ] **Covering Indexes (INCLUDE clause)**
- [ ] **Index Maintenance**: Bloat, Reindexing

## Phase 4: Performance Tuning
- [ ] **Explain Analyze**: Reading the Query Plan
- [ ] **MVCC Internals**: Multi-Version Concurrency Control
- [ ] **Vacuuming & Autovacuum**: Handling dead tuples
- [ ] **WAL (Write Ahead Logging)**
- [ ] **Connection Pooling**: PgBouncer vs App-side pooling

## Phase 5: Scaling & Reliability
- [ ] **Partitioning**: List, Range, Hash partitioning
- [ ] **Replication**: Physical vs Logical, Synchronous vs Asynchronous
- [ ] **Backup & Recovery**: pg_dump, WAL archiving, PITR (Point-In-Time Recovery)
- [ ] **High Availability**: Patroni, Keepalived
