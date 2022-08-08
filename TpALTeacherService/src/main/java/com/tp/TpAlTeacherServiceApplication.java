package com.tp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.tp.dao.TeacherRepository;
import com.tp.entities.Teacher;

@EnableEurekaClient
@SpringBootApplication
public class TpAlTeacherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpAlTeacherServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(TeacherRepository teacherRepository, RepositoryRestConfiguration restConfiguration) {
		restConfiguration.exposeIdsFor(Teacher.class);
		return args->{
			Teacher haroun = new Teacher(null, "haroun", "haroun", "haroun", "haroun", "haroun");
			teacherRepository.save(haroun);
			teacherRepository.findAll().forEach(System.out::println);
		};
	}

}
