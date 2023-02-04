package br.com.qualyt.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String nome;
	private Integer idade;
	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public Integer getIdade() {
		return idade;
	}
	
	

}
