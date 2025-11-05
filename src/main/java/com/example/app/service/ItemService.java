package com.example.app.service;

import java.util.List;

import com.example.app.domain.Item;
import com.example.app.domain.Location;

public interface ItemService {

	public List<Item> getAllItems();

	public Item getItemById(Integer id);

	public void addItem(Item item);
	
	public void editItem(Item item);
	
	public void deleteItem(Integer id);

	public List<Location> getItemLocations();
	
}
