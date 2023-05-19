package com.generation.algalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.algalog.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{

	public Optional<Cliente> findByEmail(String email);
	}
