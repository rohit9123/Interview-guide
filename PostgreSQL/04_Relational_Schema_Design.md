# Relational Schema Design: Normalization

Normalization is the process of organizing data in a database to reduce redundancy and improve data integrity. The goal is to ensure that "every fact is represented once and only once."

## The Forms (1NF to BCNF)

### 1. First Normal Form (1NF)
**Rule**: Atomic values. No lists, arrays, or comma-separated values in a single cell. Every column must hold a single value.

**Violation**:
| Student | Subjects |
| :--- | :--- |
| Alice | Math, Science |

**Solution**:
| Student | Subject |
| :--- | :--- |
| Alice | Math |
| Alice | Science |

---

### 2. Second Normal Form (2NF)
**Rule**: Must be in 1NF AND **No Partial Dependencies**.
If a table has a composite Primary Key (A, B), all non-key attributes must depend on the *entire* key, not just part of it (A or B).

**Violation**:
Table `Student_Grades` (PK: StudentID, CourseID)
| StudentID | CourseID | Grade | Professor_Name |
| :--- | :--- | :--- | :--- |
| 1 | 101 | A | Dr. Smith |

*Problem*: `Professor_Name` depends only on `CourseID`, not `StudentID`. Redundant if multiple students take Course 101.

**Solution**: Split into two tables.
1. `Grades` (StudentID, CourseID, Grade)
2. `Courses` (CourseID, Professor_Name)

---

### 3. Third Normal Form (3NF)
**Rule**: Must be in 2NF AND **No Transitive Dependencies**.
Non-key attributes should not depend on other non-key attributes. "The key, the whole key, and nothing but the key."

**Violation**:
Table `Orders` (PK: OrderID)
| OrderID | CustomerID | CustomerCity |
| :--- | :--- | :--- |
| 501 | 1 | NY |

*Problem*: `CustomerCity` depends on `CustomerID`. If Customer 1 moves, we have to update all their orders.

**Solution**:
1. `Orders` (OrderID, CustomerID)
2. `Customers` (CustomerID, CustomerCity)

---

### 4. Boyce-Codd Normal Form (BCNF)
**Rule**: A stronger version of 3NF. For every dependency A -> B, A must be a Super Key.
(Rarely encountered in simple schemas, but happens when a table has multiple overlapping candidate keys).

---

## When to Denormalize?
Normalization is great for OLTP (Transactional/Write-heavy) systems to avoid anomalies.
However, for OLAP (Analytics/Read-heavy), we often **Denormalize** (allow redundancy) to avoid expensive JOINs.
*   **Example**: Storing `total_order_value` on the Order table instead of summing `order_items` every time.
