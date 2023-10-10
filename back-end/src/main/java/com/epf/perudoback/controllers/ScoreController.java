package com.epf.perudoback.controllers;

import com.epf.perudoback.models.Course;
import com.epf.perudoback.models.Score;
import com.epf.perudoback.services.CourseService;
import com.epf.perudoback.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("scores")
@RestController
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping("")
    public List<Score> getAllScores() {
        return scoreService.findAll();
    }
}
