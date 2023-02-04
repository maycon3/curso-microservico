package br.com.qualyt.msavaliadorcredito.domain;

import java.math.BigDecimal;

public class Cartao {

	private Long id;
	private String nome;
	private String bandeira;
	private BigDecimal limite;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getBandeira() {
		return bandeira;
	}
	
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	public BigDecimal getLimite() {
		return limite;
	}
	
	public void setLimiteBasico(BigDecimal limite) {
		this.limite = limite;
	}
	
	
}
