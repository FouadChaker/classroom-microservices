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

import com.tp.dao.TeacherRepository;
import com.tp.entities.Teacher;

@RestController
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;
    
    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public Iterable<Teacher> listeProduits() {
        Iterable<Teacher> teachers = teacherRepository.findAll();
        teachers.forEach(t -> t.setPassword(null));

        return teachers;
    }

    @GetMapping(value = "/teachers/{id}")
    public Teacher afficherUnProduit(@PathVariable int id) {
        
        Teacher teacher = teacherRepository.findById(id);
        teacher.setPassword(null);

        return teacher;
    }

    @GetMapping(value = "/teachers/{username}/{password}")
    public Teacher verifierCompte(@PathVariable String username, @PathVariable String password) {
        
        Teacher teacher = teacherRepository.findByUsername(username);
        if(teacher == null)
        	return null;
        else if (teacher.getPassword().equals(password))
	        return teacher;
        return null;
    }

    @PostMapping(value = "/teachers")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Teacher teacher) {
        Teacher teacherAdded =  teacherRepository.save(teacher);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teacherAdded.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping (value = "/teachers/{id}")
    public void supprimerProduit(@PathVariable int id) {

        Teacher teacher = teacherRepository.findById(id);
        if(teacher != null)
        	teacherRepository.delete(teacher);
    }
    
    @PutMapping (value = "/teachers")
    public void updateTeacher(@RequestBody Teacher teacher) {
    	teacher.setPassword(teacherRepository.findById(teacher.getId()).get().getPassword());
    	
        teacherRepository.save(teacher);
    }
    
}
