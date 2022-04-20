package com.desafiomvspring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.desafiomvspring.dtos.CidadeDTO;
import com.desafiomvspring.dtos.CidadeNewDTO;
import com.desafiomvspring.dtos.CidadeUpdateDTO;
import com.desafiomvspring.entities.Cidade;
import com.desafiomvspring.entities.Estado;
import com.desafiomvspring.repositories.CidadeCustomRepository;
import com.desafiomvspring.repositories.CidadeRepository;
import com.desafiomvspring.services.exceptions.DatabaseException;
import com.desafiomvspring.services.exceptions.ResourceNotFoundException;

@Service
public class CidadeService {
	
	private static final Logger logger = LoggerFactory.getLogger(CidadeService.class);
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeCustomRepository cidadeCustomRepository;

	@Transactional(readOnly = true)
	public Page<CidadeDTO> findAllPaged(Pageable pageable) {
		
		logger.info("Listando todas as Cidades...");
		
		Page<Cidade> pageCidade = cidadeRepository.findAll(pageable);

		return pageCidade.map(cidade -> new CidadeDTO(cidade));
	}

	@Transactional
	public CidadeNewDTO insert(CidadeNewDTO cidadeNewDTO) {
		
		logger.info("Inserindo uma Cidade...");
		
		try {

			Cidade cidade = new Cidade();
			cidade.setNome(cidadeNewDTO.getNome());
			cidade.setEstado(new Estado(cidadeNewDTO.getIdEstado(), null));

			cidade = cidadeRepository.save(cidade);

			return new CidadeNewDTO(cidade);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException("Integrity violation");
		}

	}

	@Transactional
	public CidadeUpdateDTO update(Long id, CidadeUpdateDTO cidadeUpdateDTO) {
		
		logger.info("Alterando uma Cidade...");
		
		try {

			Cidade cidade = cidadeRepository.getOne(id);

			cidade.setNome(cidadeUpdateDTO.getNome());
			cidade.setEstado(new Estado(cidadeUpdateDTO.getIdEstado(), null));

			cidade = cidadeRepository.save(cidade);

			return new CidadeUpdateDTO(cidade);

		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Id Not found " + id);
		}
	}

	public void delete(Long id) {
		
		logger.info("Deletando uma Cidade...");
		
		try {

			cidadeRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFoundException("Id Not found " + id);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException("Integrity violation");
		}
	}

	@Transactional(readOnly = true)
	public CidadeDTO findById(Long id) {
		
		logger.info("Listando uma Cidade...");
		
		Optional<Cidade> optionalCidade = cidadeRepository.findById(id);

		Cidade cidade = optionalCidade.orElseThrow(() -> new ResourceNotFoundException("Entity Not found"));

		return new CidadeDTO(cidade);
	}

	@Transactional(readOnly = true)
	public List<CidadeDTO> findByParameters(String nomeCidade, String nomeEstado) {
		
		logger.info("Listando Cidades via parâmetros...");
		
		List<Cidade> listaCidades = cidadeCustomRepository.findAll(nomeCidade, nomeEstado);

		return listaCidades.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
	}

}
