package com.listas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listas.entities.ItemEntity;
import com.listas.exceptions.NotFoundBussinessException;
import com.listas.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public ItemEntity cria(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	
	@Transactional
	public ItemEntity atualiza(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	
	@Transactional
	public void remove(ItemEntity itemEntity) {
		itemRepository.delete(itemEntity);
	}
	
	public ItemEntity buscaPeloId(Long id) {
		Optional<ItemEntity> encontrou = itemRepository.findById(id);
		if (encontrou.isPresent()) {
			return encontrou.get();
		} else {
			throw new NotFoundBussinessException("Item " + id + " não encontrado");
		}
	}
	
	public List<ItemEntity> listaItensPelaLista(Long idLista) {
		return itemRepository.findAllByListaId(idLista);
	}
}
