package com.tp.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tp.model.Course;

@FeignClient(name="course-service")
public interface CourseServiceClient {
    
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public Iterable<Course> all() ;
    
    @RequestMapping(value = "/courses/byteacher/{id}")
    public Iterable<Course> getCoursesByTeacherId(@PathVariable int id) ;

    @GetMapping(value = "/courses/{id}")
    public Course get(@PathVariable int id) ;
    @PostMapping(value = "/courses")
    public ResponseEntity<Void> add(@RequestBody Course course) ;
    
    @DeleteMapping (value = "/courses/{id}")
    public void delete(@PathVariable int id);
    
    @PutMapping (value = "/courses")
    public void update(@RequestBody Course course) ;
    
}
