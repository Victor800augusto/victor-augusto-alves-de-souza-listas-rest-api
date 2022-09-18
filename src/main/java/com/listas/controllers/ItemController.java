package com.listas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.listas.converts.ItemConvert;
import com.listas.dto.inputs.ItemInput;
import com.listas.dto.outputs.ItemOutput;
import com.listas.entities.ItemEntity;
import com.listas.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Item")
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemConvert itemConvert;
	
	@Operation(summary = "Cadastra item",description = "Cadastra um novo item")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ItemOutput criaItem(@Valid @RequestBody ItemInput item) {
		ItemEntity itemEntity = itemConvert.inputToEntity(item);
		ItemEntity itemCriado = itemService.cria(itemEntity);
		return itemConvert.entityToOutput(itemCriado);
	}

}