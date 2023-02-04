package br.com.qualyt.mscartoes.dtos;

import java.math.BigDecimal;

import br.com.qualyt.mscartoes.domain.enuns.BandeiraCartao;

public class CartaoNewDTO {

	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limite;
	
	public String getNome() {
		return nome;
	}
	
	public BandeiraCartao getBandeira() {
		return bandeira;
	}
	
	public BigDecimal getRenda() {
		return renda;
	}
	
	public BigDecimal getLimite() {
		return limite;
	}
	
}
