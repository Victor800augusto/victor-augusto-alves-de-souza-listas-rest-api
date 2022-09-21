package com.listas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listas.entities.ItemEntity;
import com.listas.entities.ListaEntity;
import com.listas.exceptions.NotFoundBussinessException;
import com.listas.repositories.ItemRepository;
import com.listas.repositories.ListaRepository;

@Service
public class ListaService {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
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
		List<ItemEntity> itens = itemRepository.findAllByListaId(listaEntity.getId());
		if(!itens.isEmpty()) {
			itens.forEach(item -> {
				itemRepository.delete(item);
			});
		}
		listaRepository.delete(listaEntity);
	}
	
	//@Transactional
	//public void remove(UsuarioEntity usuarioEncontrado) {
	//	List<CertificadosUsuarioEntity> certificados = certificadosUsuarioService
	//			.buscaPorIdUsuario(usuarioEncontrado.getId());
	//	usuarioImagemService.deletarFotosAntigas(usuarioEncontrado.getFoto());
	//	if (certificados.size() > 0) {
	//		certificados.forEach(certificado -> {
	//			certificadosUsuarioService.remove(certificado);
	//			usuarioPdfService.deletarPdf(certificado.getUploadCertificado());
	//		});
	//	}
//
	//	usuarioRepository.delete(usuarioEncontrado);
//	}

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
