package br.com.qualyt.msavaliadorcredito.controller.exception;

public class DadosClienteNotFoundException  extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DadosClienteNotFoundException() {
		super("Dados do cliente nao encontrados para o CPF informado!");
	}

}
