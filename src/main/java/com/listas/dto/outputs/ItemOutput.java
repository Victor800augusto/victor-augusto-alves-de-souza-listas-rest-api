package com.listas.dto.outputs;

import com.listas.entities.ListaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOutput {

	private Long id;
	private String titulo;
	private Boolean concluido;
	private ListaEntity lista;
}
