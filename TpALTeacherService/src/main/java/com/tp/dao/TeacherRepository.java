package com.tp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    Teacher findById(int id);
	Teacher findByUsername(String username);
	
}
