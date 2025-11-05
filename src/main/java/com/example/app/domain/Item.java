package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Item {

	private Integer id;
	private String name;
	private Integer amount;
	private Location location;//Locationをネストしてる
	private String note;
	private LocalDateTime registered;
	private LocalDateTime updated;
	
}
