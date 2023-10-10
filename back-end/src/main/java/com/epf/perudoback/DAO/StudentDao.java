package com.epf.perudoback.DAO;

import com.epf.perudoback.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id= :courseId AND s.major.id = :majorId ")
    List<Student> findByMajorIdAndCourseId(int majorId, int courseId);
}
