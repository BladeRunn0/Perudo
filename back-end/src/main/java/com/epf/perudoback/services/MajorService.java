package com.epf.perudoback.services;

import com.epf.perudoback.DAO.MajorDao;
import com.epf.perudoback.models.Major;
import com.epf.perudoback.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MajorService {
    private final MajorDao majorDao;
    public List<Major> findAll() {
        Iterable<Major> it = majorDao.findAll();
        List <Major> majors = new ArrayList<>();
        it.forEach(majors::add);
        return majors;
    }

    public List<Student> getStudentsOfMajor(Long id) {
        return majorDao.getAllStudentsFromMajor(id);
    }
}
