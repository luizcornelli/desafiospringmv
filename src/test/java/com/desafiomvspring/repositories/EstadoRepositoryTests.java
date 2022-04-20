package com.desafiomvspring.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.desafiomvspring.entities.Estado;

@DataJpaTest
public class EstadoRepositoryTests {

	@Autowired
	private EstadoRepository estadoRepository;

	private long existingId;
	private long noExistingId;
	private long countTotalEstado;

	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		noExistingId = 10000L;
		countTotalEstado = 27;
	}

	@Test
	public void deleteShoulDeleteObjectWhenIdExists() {

		estadoRepository.deleteById(existingId);

		Optional<Estado> result = estadoRepository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAcessExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {

			estadoRepository.deleteById(noExistingId);
		});
	}

	@Test
	public void saveShouldPersistWhithAutoIncrementWhenIdISNull() {

		Estado estado = new Estado(28L, "Qualquer Estado");

		estado = estadoRepository.save(estado);
		Assertions.assertNotNull(estado.getId());
		Assertions.assertEquals(countTotalEstado + 1, estado.getId());

	}

	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {

		Optional<Estado> result = estadoRepository.findById(existingId);

		Assertions.assertTrue(result.isPresent());

	}

	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {

		Optional<Estado> result = estadoRepository.findById(noExistingId);

		Assertions.assertTrue(result.isEmpty());
	}
	
}
