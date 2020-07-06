package com.cadastro.cadastro.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.cadastro.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, String>{

	Cliente findByCodigo(long codigo);

	Cliente findByNomeCompleto(String nomeCompleto);
	
}


