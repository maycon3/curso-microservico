package br.com.qualyt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.qualyt.domain.Cliente;
import br.com.qualyt.dto.ClienteNewDTO;
import br.com.qualyt.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Transactional
	public Cliente save(Cliente cliente) {
		return this.repository.save(cliente);
	}
	
	public Optional<Cliente> getByCPF(String cpf) {
		return this.repository.findByCpf(cpf);
	}
	
	public Cliente converte(ClienteNewDTO dto) {
		return new Cliente(null,dto.getCpf(),dto.getNome(),dto.getIdade());
	}
	
}
