package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Item;

@Mapper
public interface ItemMapper {

	List<Item> selectAll();
	Item selectbyId(@Param("id") Integer id);
	void insert(Item item);
	void update(Item item);
	void delete(int id);
	
}
