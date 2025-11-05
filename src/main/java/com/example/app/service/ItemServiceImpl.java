package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Item;
import com.example.app.domain.Location;
import com.example.app.mapper.ItemMapper;
import com.example.app.mapper.LocationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

	private final ItemMapper itemMapper;
	private final LocationMapper locationMapper;

	public List<Item> getAllItems() {
		return itemMapper.selectAll();

	}

	public Item getItemById(Integer id) {
		return itemMapper.selectbyId(id);
	}

	
	public void addItem(Item item) {
//	 item.setLocation(new Location()); これは
		itemMapper.insert(item);
	}
	
	public void editItem(Item item) {
		itemMapper.update(item);
	}
	
	public void deleteItem(Integer id) {
		itemMapper.delete(id);
	}

	public List<Location> getItemLocations(){
		return locationMapper.selectAll();
	}
	
}
