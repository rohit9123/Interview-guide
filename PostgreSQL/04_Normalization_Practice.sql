-- PRACTICE: Database Normalization
-- We have a "Bad" Table that violates Normal Forms.
-- Your goal is to split it into normalized tables (3NF).

-- SCENARIO: A Training Center manages courses and students.
-- Current "Mega Table":
DROP TABLE IF EXISTS bad_training_data;
CREATE TABLE bad_training_data (
    student_name VARCHAR(100),
    student_email VARCHAR(100), -- Unique to student
    course_title VARCHAR(100),
    instructor_name VARCHAR(100),
    instructor_email VARCHAR(100), -- Unique to instructor
    enrollment_date DATE
);

-- Insert Data (Notice redundancy)
INSERT INTO bad_training_data VALUES
('Alice', 'alice@test.com', 'Database 101', 'Dr. Bob', 'bob@univ.edu', '2024-01-01'),
('Alice', 'alice@test.com', 'Advanced Java', 'Prof. Carol', 'carol@univ.edu', '2024-01-15'),
('David', 'david@test.com', 'Database 101', 'Dr. Bob', 'bob@univ.edu', '2024-02-01');

SELECT * FROM bad_training_data;

/*
 * PROBLEMS:
 * 1. Redundancy: 'Dr. Bob' and 'bob@univ.edu' are repeated for every student in his course.
 * 2. Update Anomaly: If Dr. Bob changes email, we update multiple rows.
 * 3. Delete Anomaly: If we delete the last student in 'Advanced Java', we lose Prof. Carol's info.
 * 
 * INSTRUCTIONS:
 * 1. Create a `students` table (id, name, email).
 * 2. Create an `instructors` table (id, name, email).
 * 3. Create a `courses` table (id, title, instructor_id).
 * 4. Create an `enrollments` table (student_id, course_id, date).
 * 5. Write INSERT statements to migrate the data from `bad_training_data` to your new tables.
 */

-- TODO: Write your solution below

-- 1. Create Students Table
-- CREATE TABLE students ...

-- 2. Create Instructors Table
-- CREATE TABLE instructors ...

-- 3. Create Courses Table
-- CREATE TABLE courses ...

-- 4. Create Enrollments Table
-- CREATE TABLE enrollments ...

-- 5. Migration Queries (Hint: Use INSERT INTO ... SELECT DISTINCT ...)
