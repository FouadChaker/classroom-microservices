package com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Teacher {
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	
	private String username;
	private String password;
}