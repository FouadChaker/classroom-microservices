package com.tp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tp.dao.CourseRepository;
import com.tp.entities.Course;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public Iterable<Course> all() {
        Iterable<Course> courses = courseRepository.findAll();

        return courses;
    }
    
    @RequestMapping(value = "/courses/byteacher/{id}")
    public Iterable<Course> getCoursesByTeacherId(@PathVariable int id) {
        Iterable<Course> courses = courseRepository.findByTeacher(id);

        return courses;
    }

    @GetMapping(value = "/courses/{id}")
    public Course get(@PathVariable int id) {
        
        Course course = courseRepository.findById(id);

        return course;
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<Void> add(@RequestBody Course course) {
        Course courseAdded =  courseRepository.save(course);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(courseAdded.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping (value = "/courses/{id}")
    public void delete(@PathVariable int id) {

        courseRepository.deleteById(id);
    }
    
    @PutMapping (value = "/courses")
    public void update(@RequestBody Course course) {
        courseRepository.save(course);
    }
    
}
