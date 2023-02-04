package br.com.qualyt.msavaliadorcredito.controller.exception;

public class ErroSolicitacaoCartaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ErroSolicitacaoCartaoException(String msg) {
		super(msg);
	}

}
