package com.desafiomvspring.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.desafiomvspring.entities.Cidade;

@Repository
public class CidadeCustomRepository {

	@Autowired
	private EntityManager entityManager;

	public List<Cidade> findAll(String nomeCidade, String nomeEstado) {

		String query = "SELECT c FROM Cidade c " + "JOIN c.estado e ";
		String conditional = "WHERE ";

		if (nomeCidade != null && !nomeCidade.isEmpty()) {

			query += conditional + "(LOWER(c.nome) = :nomeCidade)";
			conditional = " AND ";
		}
		if (nomeEstado != null && !nomeEstado.isEmpty()) {

			query += conditional + "(LOWER(e.nome) = :nomeEstado)";
			conditional = " AND ";
		}

		TypedQuery<Cidade> entityManagerQuery = entityManager.createQuery(query, Cidade.class);

		if (nomeCidade != null && !nomeCidade.isEmpty()) {

			entityManagerQuery.setParameter("nomeCidade", nomeCidade.toLowerCase());
		}
		if (nomeEstado != null && !nomeEstado.isEmpty()) {

			entityManagerQuery.setParameter("nomeEstado", nomeEstado.toLowerCase());
		}

		return entityManagerQuery.getResultList();
	}

}
