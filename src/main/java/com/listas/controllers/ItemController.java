package com.listas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.listas.converts.ItemConvert;
import com.listas.dto.inputs.ItemInput;
import com.listas.dto.outputs.ItemOutput;
import com.listas.entities.ItemEntity;
import com.listas.services.ItemService;
import com.listas.services.ListaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Item")
@RestController
@RequestMapping("/api/itens")
@CrossOrigin(origins = "*")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemConvert itemConvert;
	
	@Autowired
	private ListaService listaService;
	
	@Operation(summary = "Cadastra item",description = "Cadastra um novo item")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ItemOutput criaItem(@Valid @RequestBody ItemInput item) {
		ItemEntity itemEntity = itemConvert.inputToEntity(item);
		convertListas(item, itemEntity);
		itemEntity.setConcluido(false);
		ItemEntity itemCriado = itemService.cria(itemEntity);
		return itemConvert.entityToOutput(itemCriado);
	}
	
	@Operation(summary = "Altera item",description = "Altera um item")
	@PutMapping("/{id}")
	public ItemOutput alteraLivro(@PathVariable Long id, @Valid @RequestBody ItemInput itemInput) {
		ItemEntity itemEntity = itemService.buscaPeloId(id);
		itemConvert.copyDataInputToEntity(itemInput, itemEntity);

		convertListas(itemInput, itemEntity);

		ItemEntity itemAlterado = itemService.atualiza(itemEntity);
		return itemConvert.entityToOutput(itemAlterado);
	}
	
	@Operation(summary = "Remove item",description = "Remove um item")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeLivro(@PathVariable Long id) {
		ItemEntity itemEntity = itemService.buscaPeloId(id);
		itemService.remove(itemEntity);
	}
	
	@Operation(summary = "Marca item como concluido",description = "Marca um item como concluido")
	@PutMapping("/{id}/item-concluido")
	public ItemOutput marcaItemComoConcluido(@PathVariable Long id) {
		ItemEntity itemEntity = itemService.buscaPeloId(id);
		itemEntity.setConcluido(true);
		ItemEntity itemAlterado = itemService.atualiza(itemEntity);
		return itemConvert.entityToOutput(itemAlterado);
	}

	@Operation(summary = "Marca item como não concluido",description = "Marca um item como não concluido")
	@PutMapping("/{id}/item-nao-concluido")
	public ItemOutput marcaItemComoNaoConcluido(@PathVariable Long id) {
		ItemEntity itemEntity = itemService.buscaPeloId(id);
		itemEntity.setConcluido(false);
		ItemEntity itemAlterado = itemService.atualiza(itemEntity);
		return itemConvert.entityToOutput(itemAlterado);
	}
	
	private void convertListas(ItemInput itemInput, ItemEntity itemEntity) {
		itemEntity.setLista(listaService.buscaPeloId(itemInput.getListaId()));
	}

}
