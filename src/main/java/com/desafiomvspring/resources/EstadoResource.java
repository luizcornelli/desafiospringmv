package com.desafiomvspring.resources;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafiomvspring.dtos.EstadoDTO;
import com.desafiomvspring.services.EstadoService;

@RestController
@RequestMapping(value = "estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public ResponseEntity<Page<EstadoDTO>> findAll(Pageable pageable) {

		Page<EstadoDTO> pageEstadoDTO = estadoService.findAllPaged(pageable);

		return ResponseEntity.ok().body(pageEstadoDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {

		EstadoDTO estadoDTO = estadoService.findById(id);

		return ResponseEntity.ok().body(estadoDTO);
	}

	@PostMapping
	public ResponseEntity<EstadoDTO> insert(@RequestBody @Valid EstadoDTO estadoDTO) {

		estadoDTO = estadoService.insert(estadoDTO);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estadoDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(estadoDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> update(@PathVariable Long id, @RequestBody @Valid EstadoDTO estadoDTO) {

		estadoDTO = estadoService.update(id, estadoDTO);

		return ResponseEntity.ok().body(estadoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		estadoService.delete(id);

		return ResponseEntity.noContent().build();
	}

}
