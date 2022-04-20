package com.desafiomvspring.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.desafiomvspring.entities.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório não pode ser VAZIO")
	private String nome;

	private EstadoDTO estadoDTO;

	public CidadeDTO() {
	}

	public CidadeDTO(Long id, String nome, EstadoDTO estadoDTO) {
		this.id = id;
		this.nome = nome;
		this.estadoDTO = estadoDTO;
	}

	public CidadeDTO(Cidade cidade) {

		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.estadoDTO = new EstadoDTO(cidade.getEstado());
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

	public EstadoDTO getEstado() {
		return estadoDTO;
	}

	public void setEstado(EstadoDTO estadoDTO) {
		this.estadoDTO = estadoDTO;
	}

}
