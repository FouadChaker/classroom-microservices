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

import com.tp.model.Teacher;

@FeignClient(name="teacher-service")
public interface TeacherServiceClient {
	@RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public Iterable<Teacher> listTeachers();

    @GetMapping(value = "/teachers/{id}")
    public Teacher get(@PathVariable int id);

    @GetMapping(value = "/teachers/{username}/{password}")
    public Teacher verifyAccount(@PathVariable String username, @PathVariable String password);

    @PostMapping(value = "/teachers")
    public ResponseEntity<Void> add(@RequestBody Teacher teacher);
    
    @DeleteMapping (value = "/teachers/{id}")
    public void delete(@PathVariable int id);
    
    @PutMapping (value = "/teachers")
    public void updateTeacher(@RequestBody Teacher teacher);
}
