package com.listas.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.listas.dto.inputs.ListaInput;
import com.listas.dto.outputs.ListaOutput;
import com.listas.entities.ListaEntity;

@Component
public class ListaConvert {

	@Autowired
	private ModelMapper model;

	public ListaOutput entityToOutput(ListaEntity listaEntity) {
		return model.map(listaEntity, ListaOutput.class);
	}

	public List<ListaOutput> entityToOutput(List<ListaEntity> listasEntity) {
		return listasEntity.stream().map(lista -> this.entityToOutput(lista)).collect(Collectors.toList());
	}

	public ListaEntity inputToEntity(ListaInput listaInput) {
		return model.map(listaInput, ListaEntity.class);
	}

	public void copyDataInputToEntity(ListaInput listaInput, ListaEntity listaEntity) {
		model.map(listaInput, listaEntity);
	}
}
