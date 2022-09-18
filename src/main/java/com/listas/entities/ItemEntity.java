package com.listas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.listas.exceptions.BadRequestBussinessException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_items")
@Getter
@Setter
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, name = "titulo")
	private String titulo;
	
	@Column(name = "concluido")
	private Boolean concluido;
	
	@JoinColumn(name = "lista_id")
	@ManyToOne
	private ListaEntity lista;
	
	public void setTitulo(String titulo) {
		if (titulo == null || "".equals(titulo)) {
			throw new BadRequestBussinessException("O campo título é obrigatório!");
		}

		if (titulo.length() > 1000) {
			throw new BadRequestBussinessException("O campo título deve ter no máximo 100 caracteres!");
		}

		this.titulo = titulo;
	}
}
