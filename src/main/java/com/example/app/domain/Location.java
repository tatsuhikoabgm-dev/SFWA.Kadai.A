package com.example.app.domain;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Location {
	
	@NotNull
	private Integer id;

	private String name;
	
	
}
