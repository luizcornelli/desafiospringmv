package com.desafiomvspring.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafiomvspring.dtos.ClienteDTO;
import com.desafiomvspring.dtos.ClienteNewDTO;
import com.desafiomvspring.dtos.ClienteUpdateDTO;
import com.desafiomvspring.services.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pageable) {

		Page<ClienteDTO> pageClienteDTO = clienteService.findAllPaged(pageable);

		return ResponseEntity.ok().body(pageClienteDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {

		ClienteDTO clienteDTO = clienteService.findById(id);

		return ResponseEntity.ok().body(clienteDTO);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<ClienteDTO>> findAll(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "nome", required = false) String nome) {

		List<ClienteDTO> listaClienteDTO = clienteService.findByParameters(id, nome);

		return ResponseEntity.ok(listaClienteDTO);
	}

	@PostMapping
	public ResponseEntity<ClienteNewDTO> insert(@RequestBody @Valid ClienteNewDTO clienteNewDTO) {

		clienteNewDTO = clienteService.insert(clienteNewDTO);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(clienteNewDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(clienteNewDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteUpdateDTO> update(@PathVariable Long id,
			@RequestBody @Valid ClienteUpdateDTO clienteUpdateDTO) {

		clienteUpdateDTO = clienteService.update(id, clienteUpdateDTO);

		return ResponseEntity.ok().body(clienteUpdateDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		clienteService.delete(id);

		return ResponseEntity.noContent().build();
	}

}
