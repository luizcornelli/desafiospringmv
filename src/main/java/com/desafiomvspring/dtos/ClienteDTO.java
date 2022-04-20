package com.desafiomvspring.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import com.desafiomvspring.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Character sexo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Timestamp dataNascimento;
	private Integer idade;

	private CidadeDTO cidadeDTO;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, Character sexo, Timestamp dataNascimento, Integer idade,
			CidadeDTO cidadeDTO) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidadeDTO = cidadeDTO;
	}

	public ClienteDTO(Cliente cliente) {

		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sexo = cliente.getSexo();
		this.dataNascimento = cliente.getDataNascimento();
		this.idade = cliente.getIdade();
		this.cidadeDTO = new CidadeDTO(cliente.getCidade());
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

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Timestamp getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Timestamp dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public CidadeDTO getCidadeDTO() {
		return cidadeDTO;
	}

	public void setCidadeDTO(CidadeDTO cidadeDTO) {
		this.cidadeDTO = cidadeDTO;
	}

}
