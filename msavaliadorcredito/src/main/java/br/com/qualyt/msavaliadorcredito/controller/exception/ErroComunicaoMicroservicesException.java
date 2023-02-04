package br.com.qualyt.msavaliadorcredito.controller.exception;

public class ErroComunicaoMicroservicesException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	
	public ErroComunicaoMicroservicesException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}
	
	

}
