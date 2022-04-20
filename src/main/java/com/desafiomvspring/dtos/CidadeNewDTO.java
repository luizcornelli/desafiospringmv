package com.desafiomvspring.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.desafiomvspring.entities.Cidade;

public class CidadeNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório não pode ser VAZIO")
	private String nome;

	@NotNull(message  = "Preenchimento obrigatório não pode ser NULO ")
	private Long idEstado;

	public CidadeNewDTO() {
	}

	public CidadeNewDTO(Long id, String nome, Long idEstado) {
		this.id = id;
		this.nome = nome;
		this.idEstado = idEstado;
	}

	public CidadeNewDTO(Cidade cidade) {
		
		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.idEstado = cidade.getEstado().getId();
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

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

}
