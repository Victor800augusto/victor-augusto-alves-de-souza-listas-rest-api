package com.listas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.listas.converts.ItemConvert;
import com.listas.converts.ListaConvert;
import com.listas.dto.inputs.ListaInput;
import com.listas.dto.outputs.ItemOutput;
import com.listas.dto.outputs.ListaOutput;
import com.listas.entities.ItemEntity;
import com.listas.entities.ListaEntity;
import com.listas.services.ItemService;
import com.listas.services.ListaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Lista")
@RestController
@RequestMapping("/api/listas")
@CrossOrigin(origins = "*")
public class ListaController {

	@Autowired
	private ListaService listaService;

	@Autowired
	private ListaConvert listaConvert;
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemConvert itemConvert;

	@Operation(summary = "Cadastra lista",description = "Cadastra uma nova lista")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ListaOutput criaLista(@Parameter(description = "Representação de uma lista") @Valid @RequestBody ListaInput lista)  {
		ListaEntity listaEntity = listaConvert.inputToEntity(lista);
		ListaEntity listaCriada = listaService.cria(listaEntity);
		return listaConvert.entityToOutput(listaCriada);
	}
	
	@Operation(summary = "Altera lista", description = "Altera dados de uma lista")
	@PutMapping("/{id}")
	public ListaOutput alteraLista(@Parameter(description = "Id da lista", example = "1") 
	@PathVariable Long id,@Parameter(description = "Representação de uma lista")
	@Valid @RequestBody ListaInput listaInput) {
		ListaEntity listaEntity = listaService.buscaPeloId(id);
		listaConvert.copyDataInputToEntity(listaInput, listaEntity);
		ListaEntity livroAlterado = listaService.atualiza(listaEntity);
		return listaConvert.entityToOutput(livroAlterado);
	}

	@Operation(summary = "Busca lista por id",description = "Busca lista pelo seu id")
	@GetMapping("/{id}")
	public ListaOutput buscaAutorPorId(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		ListaEntity listaEntity = listaService.buscaPeloId(id);
		return listaConvert.entityToOutput(listaEntity);
	}
	
	@Operation(summary = "Remove lista", description = "Remove lista pelo seu id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeLista(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		ListaEntity listaEntity = listaService.buscaPeloId(id);
		listaService.remove(listaEntity);
	}

	@Operation(summary = "Lista as listas",description = "Lista todas as listas cadastradas")
	@GetMapping
	public List<ListaOutput> listaListas() {
		List<ListaEntity> listaTodos = listaService.listaTodos();
		return listaConvert.entityToOutput(listaTodos);
	}

	@Operation(summary = "Lista itens da lista",description = "Lista itens de uma lista pelo id da lista")
	@GetMapping("/{idLista}/itens")
	public List<ItemOutput> listaItens(@Parameter(description = "Id da lista", example = "1") @PathVariable Long idLista) {
		List<ItemEntity> listaTodos = itemService.listaItensPelaLista(idLista);
		return itemConvert.entityToOutput(listaTodos);
	}
}
