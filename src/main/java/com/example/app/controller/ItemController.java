package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Item;
import com.example.app.service.ItemServiceImpl;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ItemController {
	
	private final ItemServiceImpl itemServiceImpl;

	@GetMapping("/items")
	public String showItems(Model model) {
		
		model.addAttribute("items",itemServiceImpl.getAllItems());
		System.out.println(itemServiceImpl.getAllItems());
		
		return "list";
	}
	
	@GetMapping("/items/add")
	public String showAddForm(Model model) {
		model.addAttribute("inputForm",new Item());
		model.addAttribute("locations",itemServiceImpl.getItemLocations());
		System.out.println(itemServiceImpl.getItemLocations());
		return "save";
	}
	
	@PostMapping("/items/add")
	public String addItem(@ModelAttribute Item item) {
		System.out.println(item);
		itemServiceImpl.addItem(item);
		return "redirect:/items";
	}
	
	
}
