package com.example.app.advice;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	@ModelAttribute("userInfo")
	public String addUserInfo(HttpSession session) {
		
		return (String)session.getAttribute("userInfo");
		
		
	}

}
