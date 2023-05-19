package com.generation.algalog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.algalog.model.Cliente;
import com.generation.algalog.repository.ClienteRepository;
import com.generation.algalog.service.CatalogoClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository  clienteRepository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>>  getAll(){
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id){
		return clienteRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente  post(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.findById(cliente.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(catalogoClienteService.salvar(cliente)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		Optional<Cliente> postagem = clienteRepository.findById(id);

		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		catalogoClienteService.excluir(id);
	}
}
