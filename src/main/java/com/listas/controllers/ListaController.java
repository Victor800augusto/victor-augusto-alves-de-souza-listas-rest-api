package com.listas.controllers;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.listas.converts.ListaConvert;
import com.listas.dto.inputs.ListaInput;
import com.listas.dto.outputs.ListaOutput;
import com.listas.entities.ListaEntity;
import com.listas.services.ListaService;


@RestController
@RequestMapping("/api/listas")
@CrossOrigin(origins = "*")
public class ListaController {

	@Autowired
	private ListaService listaService;

	@Autowired
	private ListaConvert listaConvert;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ListaOutput criaLista(@Valid @RequestBody ListaInput lista) throws URISyntaxException {
		ListaEntity listaEntity = listaConvert.inputToEntity(lista);
		ListaEntity listaCriada = listaService.cria(listaEntity);
		return listaConvert.entityToOutput(listaCriada);
	}
}
