package com.epf.perudoback.services;

import com.epf.perudoback.DAO.CourseDao;
import com.epf.perudoback.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseService {
    private final CourseDao courseDao;

    public List<Course> findAll() {
        return courseDao.findAll();
    }
}
