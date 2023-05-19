package com.generation.algalog.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="tb_cliente")
public class Cliente {

	// id com alo incremento 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	// valor não pode ser nulo e com menos de 100 caracteres 
	@Column(name = "nome ")// usado so para aprender mais comandos 
	@NotBlank(message =" insira um valor ")
	@Size(max = 100 ,message = "valor a maixo de 100 caracteres ")
	private String nome;
	
	@NotBlank  // não pode ser nulo nem vazio 
	@Email  // valida email
	@Size(max = 255) // tamanho maximo 
	private String email;
	
	@NotBlank // não pode ser nulo 
	@Size(max = 20 ) // tamanho maximo
	@Column(name = "fone")
	private String telefone;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(Id, other.Id);
	}

	
}
