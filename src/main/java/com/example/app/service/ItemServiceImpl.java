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

	@Override
	public List<Item> getAllItems() {
		return itemMapper.selectAll();

	}
	
	@Override
	public Item getItemById(Integer id) {
		return itemMapper.selectbyId(id);
	}

	@Override
	public void addItem(Item item) {
//	 item.setLocation(new Location()); これは
		itemMapper.insert(item);
	}
	
	@Override
	public void editItem(Item item) {
		itemMapper.update(item);
	}
	
	@Override
	public void deleteItem(Integer id) {
		itemMapper.delete(id);
	}

	@Override
	public List<Location> getItemLocations(){
		return locationMapper.selectAll();
	}
	
}
