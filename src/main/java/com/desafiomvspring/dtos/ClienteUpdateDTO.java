package com.desafiomvspring.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.desafiomvspring.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório não pode ser VAZIO")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório não pode ser NULL")
	private Character sexo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Preenchimento obrigatório não pode ser NULL")
	private Timestamp dataNascimento;

	@NotNull(message = "Preenchimento obrigatório não pode ser NULL")
	private Integer idade;

	@NotNull(message = "Preenchimento obrigatório não pode ser NULL")
	private Long idCidade;

	public ClienteUpdateDTO() {
	}

	public ClienteUpdateDTO(Long id, String nome, Character sexo, Timestamp dataNascimento, Integer idade, Long idCidade) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.idCidade = idCidade;
	}

	public ClienteUpdateDTO(Cliente cliente) {

		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sexo = cliente.getSexo();
		this.dataNascimento = cliente.getDataNascimento();
		this.idade = cliente.getIdade();
		this.idCidade = cliente.getCidade().getId();
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

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

}
