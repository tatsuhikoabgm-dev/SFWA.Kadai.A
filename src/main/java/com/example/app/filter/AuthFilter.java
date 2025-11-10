package com.example.app.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
																	HttpServletResponse response,
																	FilterChain filterChain) 
																			throws ServletException ,IOException {
		
		if(request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("/");
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
