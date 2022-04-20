package com.desafiomvspring.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.desafiomvspring.entities.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório não pode ser VAZIO")
	private String nome;

	public EstadoDTO() {
	}

	public EstadoDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
