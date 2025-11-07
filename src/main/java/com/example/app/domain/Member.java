package com.example.app.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Member {

	private Integer id;
	
	@NotBlank
	@Max(20)
	private String name;
	
	@NotBlank
	@Size(max=20)
	private String loginId;
	
	@NotBlank
	private String loginPass;
	
}
