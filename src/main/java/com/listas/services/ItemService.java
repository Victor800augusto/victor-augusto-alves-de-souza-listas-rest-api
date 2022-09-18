package com.listas.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listas.entities.ItemEntity;
import com.listas.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public ItemEntity cria(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
}
