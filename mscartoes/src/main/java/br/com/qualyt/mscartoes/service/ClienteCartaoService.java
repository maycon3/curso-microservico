package br.com.qualyt.mscartoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qualyt.mscartoes.domain.ClienteCartao;
import br.com.qualyt.mscartoes.repositories.ClienteCartaoRepository;

@Service
public class ClienteCartaoService {

	@Autowired
	private ClienteCartaoRepository clienteRepository;
	
	public List<ClienteCartao> listCartoesByCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}
}
