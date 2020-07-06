package com.cadastro.cadastro.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.cadastro.model.Cidade;
import com.cadastro.cadastro.model.Cliente;


public interface CidadeRepository extends CrudRepository<Cidade, String>{

	Cidade findByCliente(Cliente cliente);

	Cidade findByNome(String nome);

	Cidade findByCodigo(Long codigo);
}
