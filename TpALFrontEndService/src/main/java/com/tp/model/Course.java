package com.tp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Course {
	private Integer id;
	private String name;
	private int coefficient;
	private int volumetp, volumetd, volumecours;
	private int credit;
	private int teacher;
	
	@JsonIgnore
	private Teacher createdBy;
}
