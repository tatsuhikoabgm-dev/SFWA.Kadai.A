package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Location;
import com.example.app.mapper.LocationMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ItemController {
	
	public final LocationMapper locationMapper;

	@GetMapping("/items")
	public String showItems(Model model) {
		
		model.addAttribute("items",locationMapper.selectAll());
		List<Location> items = locationMapper.selectAll();
		System.out.println(items);
		
		return "list";
	}
	
	
}
