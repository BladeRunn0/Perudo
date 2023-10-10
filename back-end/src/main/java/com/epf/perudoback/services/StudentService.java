package com.epf.perudoback.services;

import com.epf.perudoback.DAO.StudentDao;
import com.epf.perudoback.DTO.StudentDto;
import com.epf.perudoback.DTO.StudentMapper;
import com.epf.perudoback.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;

    public List<Student> findAll() {
        Iterable<Student> it = studentDao.findAll();
        List <Student> users = new ArrayList<>();
        it.forEach(users::add);
        return users ;
    }

    public Student getById(Long id) {
        return studentDao.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        studentDao.deleteById(id);
    }

    @Transactional
    public void addStudent(StudentDto studentDto) {
        Student student;
        try {
            student = StudentMapper.fromDto(studentDto, null);
        } catch (IOException e) {
            throw new RuntimeException("Error with Student image", e);
        }

        studentDao.save(student);
    }

    @Transactional
    public void updateStudent(StudentDto studentDto, Long id) {
        studentDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student doesn't exist"));
        Student student;
        try {
            student = StudentMapper.fromDto(studentDto, id);
        } catch (IOException e) {
            throw new RuntimeException("Error with Student image", e);
        }
        studentDao.save(student);
    }

    public List<Student> searchByMajorAndCourse(int majorId, int courseId) {
        return studentDao.findByMajorIdAndCourseId(majorId, courseId);
    }
}
