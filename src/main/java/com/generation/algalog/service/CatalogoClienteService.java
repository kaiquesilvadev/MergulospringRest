package com.generation.algalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.algalog.exception.NegocioException;
import com.generation.algalog.model.Cliente;
import com.generation.algalog.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class CatalogoClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emeilemUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream().anyMatch(clienteExistente -> clienteExistente.equals(cliente));
		
		if (emeilemUso) {
			throw new NegocioException("já existe um cliente cadastra com este email");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
