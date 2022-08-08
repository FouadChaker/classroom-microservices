package com.tp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.model.Course;
import com.tp.model.Teacher;
import com.tp.restclients.CourseServiceClient;
import com.tp.restclients.TeacherServiceClient;

@Controller
public class FrontEndController {
	
	@Autowired
	private CourseServiceClient courseClient;
	@Autowired
	private TeacherServiceClient teacherClient;
	
	@RequestMapping("/")
	public String home(Model model) {
		Iterable<Course> courseList = courseClient.all();
		courseList.forEach(course -> course.setCreatedBy(teacherClient.get(course.getTeacher())));
		model.addAttribute("courseList", courseList);
		
		return "index";
	}
	
	@RequestMapping("/insert")
	public String home2(Model model) {
		courseClient.add(new Course(null, "al",5,5,5,5,5, 1, null));
		
		return "redirect:/";
	}
	
	@RequestMapping("/opensubscribe")
	public String opensubscribe(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "subscribe";
	}
	
	@RequestMapping("/subscribe")
	public String subscribe(@ModelAttribute("teacher") Teacher teacher) {
		teacherClient.add(teacher);
		
		return "redirect:/";
	}
	
	@RequestMapping("/openlogin")
	public String openlogin(
			@RequestParam(value = "message", required = false) String message,
	        Model model )
	{
		model.addAttribute("message", message);
		model.addAttribute("teacher", new Teacher());
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute("teacher") Teacher teacher, HttpSession session) {
		Teacher connectedTeacher = teacherClient.verifyAccount(teacher.getUsername(), teacher.getPassword());
		
		if(connectedTeacher == null)
			return "redirect:/openlogin?message=try%20again";
		
		else {
			session.setAttribute("connectedTeacher", connectedTeacher);
			return "redirect:/teacherPage";
		}
	}
	
	@RequestMapping("/teacherPage")
	public String teacherPage(Model model) {
		Iterable<Course> courseList = courseClient.all();
		courseList.forEach(course -> course.setCreatedBy(teacherClient.get(course.getTeacher())));
		
		model.addAttribute("courseList", courseList);
		
		return "teacherPage";
	}
	
	@RequestMapping("/openmyprofile")
	public String openmyprofile(Model model, HttpSession session) {
		Teacher connectedTeacher = (Teacher)session.getAttribute("connectedTeacher");
		Teacher teacher = new Teacher(
				connectedTeacher.getId(),
				connectedTeacher.getFirstname(),
				connectedTeacher.getLastname(),
				connectedTeacher.getEmail(),
				connectedTeacher.getUsername(),
				connectedTeacher.getPassword()
				);
		model.addAttribute("teacher", teacher);
		return "myprofile";
	}
	
	@RequestMapping("/myprofile")
	public String myprofile(@ModelAttribute("teacher") Teacher teacher, HttpSession session) {
		teacherClient.updateTeacher(teacher);

		session.setAttribute("connectedTeacher", teacher);
		
		return "teacherPage";
	}
	
	@RequestMapping("/mycourses")
	public String mycourses(Model model, HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("connectedTeacher");

		Iterable<Course> courseList = courseClient.getCoursesByTeacherId(teacher.getId());
		courseList.forEach(course -> course.setCreatedBy(teacher));
		model.addAttribute("courseList", courseList);
		
		return "mycourses";
	}
	
	@RequestMapping("/addcourse")
	public String addcourse(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		
		return "addcourse";
	}
	
	@RequestMapping(value = "/savecourse", method = RequestMethod.POST)
	public String savecourse(@ModelAttribute("course") Course course, HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("connectedTeacher");
		course.setTeacher(teacher.getId());
		courseClient.update(course);
		
		return "redirect:/teacherPage";
	}
	
	@RequestMapping("/editcourse/{id}")
	public String editcourse(@PathVariable(name = "id") int id, Model model) {
		Course course = courseClient.get(id);
		model.addAttribute("course", course);
		
		return "editcourse";
	}
	
	@RequestMapping("/deletecourse/{id}")
	public String deletecourse(@PathVariable(name = "id") int id) {
		courseClient.delete(id);
		return "redirect:/teacherPage";		
	}
	
	
}
