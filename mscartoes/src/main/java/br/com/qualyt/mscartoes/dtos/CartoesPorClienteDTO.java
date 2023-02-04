package br.com.qualyt.mscartoes.dtos;

import java.math.BigDecimal;

import br.com.qualyt.mscartoes.domain.ClienteCartao;

public class CartoesPorClienteDTO {

	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
	public CartoesPorClienteDTO(ClienteCartao cliente) {
		this.nome = cliente.getCartao().getNome();
		this.bandeira = cliente.getCartao().getBandeira().toString();
		this.limiteLiberado = cliente.getCartao().getLimite();
	}

	public String getNome() {
		return nome;
	}

	public String getBandeira() {
		return bandeira;
	}

	public BigDecimal getLimiteLiberado() {
		return limiteLiberado;
	}
	
	
}
