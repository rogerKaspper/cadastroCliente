package com.cadastro.cadastro.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cidade implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigo;
	
	private String nome;

	private String estado;
	
	@OneToOne
	private Cliente cliente;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	
}
