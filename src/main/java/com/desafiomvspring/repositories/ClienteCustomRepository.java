package com.desafiomvspring.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.desafiomvspring.entities.Cliente;

@Repository
public class ClienteCustomRepository {

	@Autowired
	private EntityManager entityManager;

	public List<Cliente> findAll(Long id, String nome) {

		String query = "SELECT c FROM Cliente c ";
		String conditional = "WHERE ";

		if (id != null) {

			query += conditional + "c.id = :id";
			conditional = " AND ";
		}
		if (nome != null && !nome.isEmpty()) {

			query += conditional + "(LOWER(c.nome) = :nome)";
			conditional = " AND ";
		}

		TypedQuery<Cliente> entityManagerQuery = entityManager.createQuery(query, Cliente.class);

		if (id != null) {

			entityManagerQuery.setParameter("id", id);
		}
		if (nome != null && !nome.isEmpty()) {

			entityManagerQuery.setParameter("nome", nome.toLowerCase());
		}

		return entityManagerQuery.getResultList();
	}

}
