package com.listas.dto.inputs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

	@NotEmpty(message = "O campo Título é obrigatório!")
	@Length(message = "O campo Título deve ter no máximo 100 caracteres!", min = 1, max = 100)
	private String titulo;
	
	@NotNull(message = "A lista é obrigatória!")
	private Long listaId;
}
