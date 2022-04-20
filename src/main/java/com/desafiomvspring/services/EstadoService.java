package com.desafiomvspring.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiomvspring.dtos.EstadoDTO;
import com.desafiomvspring.entities.Estado;
import com.desafiomvspring.repositories.EstadoRepository;
import com.desafiomvspring.services.exceptions.DatabaseException;
import com.desafiomvspring.services.exceptions.ResourceNotFoundException;

@Service
public class EstadoService {
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoService.class);

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional(readOnly = true)
	public Page<EstadoDTO> findAllPaged(Pageable pageable) {
		
		logger.info("Listando todos os Estados...");
		
		Page<Estado> pageEstado = estadoRepository.findAll(pageable);

		return pageEstado.map(estado -> new EstadoDTO(estado));
	}

	@Transactional
	public EstadoDTO insert(EstadoDTO estadoDTO) {
		
		logger.info("Inserindo um Estado...");
		
		Estado estado = new Estado();
		estado.setNome(estadoDTO.getNome());

		estado = estadoRepository.save(estado);

		return new EstadoDTO(estado);
	}

	@Transactional
	public EstadoDTO update(Long id, EstadoDTO estadoDTO) {
		
		logger.info("Alterando um Estado...");
		
		try {

			Estado estado = estadoRepository.getOne(id);
			estado.setNome(estadoDTO.getNome());

			estado = estadoRepository.save(estado);

			return new EstadoDTO(estado);

		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Id Not found " + id);
		}

	}

	public void delete(Long id) {
		
		logger.info("Deletando um Estado...");
		
		try {

			estadoRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e)  {

			throw new ResourceNotFoundException("Id Not found " + id);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException("Integrity violation");
		}

	}

	@Transactional(readOnly = true)
	public EstadoDTO findById(Long id) {
		
		logger.info("Listando um Estado...");
		
		Optional<Estado> optionalEstado = estadoRepository.findById(id);

		Estado estado = optionalEstado.orElseThrow(() -> new ResourceNotFoundException("Entity Not found"));

		return new EstadoDTO(estado);
	}

}
