SELECT setval(pg_get_serial_sequence('students', 'id'), coalesce(max(id)+1, 1), false) FROM students;
SELECT setval(pg_get_serial_sequence('student_course', 'id'), coalesce(max(id)+1, 1), false) FROM student_course;
SELECT setval(pg_get_serial_sequence('majors', 'id'), coalesce(max(id)+1, 1), false) FROM majors;
SELECT setval(pg_get_serial_sequence('courses', 'id'), coalesce(max(id)+1, 1), false) FROM courses;