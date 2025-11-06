package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Item {

	private Integer id;
	
	@NotBlank
	@Size(max=50)
	private String name;
	
	@NotNull
	@Min(0)
	private Integer amount;
	
	@Valid
	private Location location;//Locationをネストしてる
	

	private String note;
	
	private LocalDateTime registered;
	private LocalDateTime updated;
	
}
