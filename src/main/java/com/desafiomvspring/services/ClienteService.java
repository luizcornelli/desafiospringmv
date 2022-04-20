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

import com.desafiomvspring.dtos.ClienteDTO;
import com.desafiomvspring.dtos.ClienteNewDTO;
import com.desafiomvspring.dtos.ClienteUpdateDTO;
import com.desafiomvspring.entities.Cidade;
import com.desafiomvspring.entities.Cliente;
import com.desafiomvspring.repositories.ClienteCustomRepository;
import com.desafiomvspring.repositories.ClienteRepository;
import com.desafiomvspring.services.exceptions.DatabaseException;
import com.desafiomvspring.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteCustomRepository clienteCustomRepository;

	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(Pageable pageable) {
		
		logger.info("Listando todos os Clientes...");
		
		Page<Cliente> pageCliente = clienteRepository.findAll(pageable);

		return pageCliente.map(cliente -> new ClienteDTO(cliente));
	}

	@Transactional
	public ClienteNewDTO insert(ClienteNewDTO clienteNewDTO) {

		logger.info("Inserindo um Cliente...");
		
		Cliente cliente = new Cliente();
		cliente.setNome(clienteNewDTO.getNome());
		cliente.setSexo(clienteNewDTO.getSexo());
		cliente.setDataNascimento(clienteNewDTO.getDataNascimento());
		cliente.setIdade(clienteNewDTO.getIdade());
		cliente.setCidade(new Cidade(clienteNewDTO.getIdCidade(), null, null));

		cliente = clienteRepository.save(cliente);

		return new ClienteNewDTO(cliente);
	}

	@Transactional
	public ClienteUpdateDTO update(Long id, ClienteUpdateDTO clienteUpdateDTO) {
		
		logger.info("Alterando um Cliente...");
		
		try {

			Cliente cliente = clienteRepository.getOne(id);

			cliente.setNome(clienteUpdateDTO.getNome());
			cliente.setSexo(clienteUpdateDTO.getSexo());
			cliente.setDataNascimento(clienteUpdateDTO.getDataNascimento());
			cliente.setIdade(clienteUpdateDTO.getIdade());
			cliente.setCidade(new Cidade(clienteUpdateDTO.getIdCidade(), null, null));

			return new ClienteUpdateDTO(cliente);

		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Id Not found " + id);
		}
	}

	public void delete(Long id) {
		
		logger.info("Deletando um Cliente...");
		
		try {

			clienteRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e)  {

			throw new ResourceNotFoundException("Id Not found " + id);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException("Integrity violation");
		}
	}

	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		
		logger.info("Listando um Cliente...");
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);

		Cliente cliente = optionalCliente.orElseThrow(() -> new ResourceNotFoundException("Entity Not found"));

		return new ClienteDTO(cliente);
	}

	@Transactional(readOnly = true)
	public List<ClienteDTO> findByParameters(Long id, String nome) {
		
		logger.info("Listando um Cliente via parâmetros...");
		
		List<Cliente> listaClientes = clienteCustomRepository.findAll(id, nome);

		return listaClientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());

	}

}
