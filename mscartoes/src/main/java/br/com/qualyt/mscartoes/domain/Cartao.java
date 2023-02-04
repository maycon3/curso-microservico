package br.com.qualyt.mscartoes.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.qualyt.mscartoes.domain.enuns.BandeiraCartao;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limite;
	
	public Cartao() {
	}
	
	public Cartao(Long id, String nome, BandeiraCartao bandeira,
			BigDecimal renda, BigDecimal limite) {
		this.id = id;
		this.nome = nome;
		this.bandeira = bandeira;
		this.renda = renda;
		this.limite = limite;
	}


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


	public BandeiraCartao getBandeira() {
		return bandeira;
	}


	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}


	public BigDecimal getRenda() {
		return renda;
	}


	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}


	public BigDecimal getLimite() {
		return limite;
	}


	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
	
	
}
