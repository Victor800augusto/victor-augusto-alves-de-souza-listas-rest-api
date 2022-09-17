package com.listas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listas.entities.ListaEntity;
import com.listas.exceptions.NotFoundBussinessException;
import com.listas.repositories.ListaRepository;

@Service
public class ListaService {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Transactional
	public ListaEntity cria(ListaEntity listaEntity) {
		return listaRepository.save(listaEntity);
	}

	@Transactional
	public ListaEntity atualiza(ListaEntity listaEntity) {
		return listaRepository.save(listaEntity);
	}
	
	@Transactional
	public void remove(ListaEntity listaEntity) {
		listaRepository.delete(listaEntity);
	}

	public List<ListaEntity> listaTodos() {
		return listaRepository.findAll();
	}

	public ListaEntity buscaPeloId(Long id) {
		Optional<ListaEntity> encontrou = listaRepository.findById(id);
		if (encontrou.isPresent()) {
			return encontrou.get();
		} else {
			throw new NotFoundBussinessException("Lista " + id + " não encontrada");
		}
	}

}
