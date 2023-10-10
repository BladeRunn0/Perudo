package com.epf.perudoback.controllers;

import com.epf.perudoback.models.Major;
import com.epf.perudoback.models.Student;
import com.epf.perudoback.services.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("majors")
@RestController
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping("")
    public List<Major> findAll() {
        return majorService.findAll();
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsOfMajor(@PathVariable Long id) {
        return majorService.getStudentsOfMajor(id);
    }
}
