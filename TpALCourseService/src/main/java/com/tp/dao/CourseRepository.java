package com.tp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findById(int id);
	List<Course> findByTeacher(int teacherId);
}
