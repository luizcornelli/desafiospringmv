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

import com.desafiomvspring.dtos.CidadeDTO;
import com.desafiomvspring.dtos.CidadeNewDTO;
import com.desafiomvspring.dtos.CidadeUpdateDTO;
import com.desafiomvspring.services.CidadeService;

@RestController
@RequestMapping(value = "cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<Page<CidadeDTO>> findAll(Pageable pageable) {

		Page<CidadeDTO> pageCidadeDTO = cidadeService.findAllPaged(pageable);

		return ResponseEntity.ok().body(pageCidadeDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {

		CidadeDTO cidadeDTO = cidadeService.findById(id);

		return ResponseEntity.ok().body(cidadeDTO);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<CidadeDTO>> findAll(
			@RequestParam(value = "nomeCidade", required = false) String nomeCidade,
			@RequestParam(value = "nomeEstado", required = false) String nomeEstado) {

		List<CidadeDTO> listaCidadeDTO = cidadeService.findByParameters(nomeCidade, nomeEstado);

		return ResponseEntity.ok(listaCidadeDTO);
	}

	@PostMapping
	public ResponseEntity<CidadeNewDTO> insert(@RequestBody @Valid CidadeNewDTO cidadeNewDTO) {

		cidadeNewDTO = cidadeService.insert(cidadeNewDTO);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cidadeNewDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(cidadeNewDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CidadeUpdateDTO> update(@PathVariable Long id,
			@RequestBody @Valid CidadeUpdateDTO cidadeUpdateDTO) {

		cidadeUpdateDTO = cidadeService.update(id, cidadeUpdateDTO);

		return ResponseEntity.ok().body(cidadeUpdateDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		cidadeService.delete(id);

		return ResponseEntity.noContent().build();
	}

}
