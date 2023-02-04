package br.com.qualyt.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.qualyt.dto.ClienteNewDTO;
import br.com.qualyt.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public String status() {
		return "ok";
	}
	
	
	@PostMapping
	public ResponseEntity save(@RequestBody ClienteNewDTO dto) {
		var cliente = this.clienteService.converte(dto);
		cliente = this.clienteService.save(cliente);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.query("cpf={cpf}")
					.buildAndExpand(cliente.getCpf())
					.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
		var cliente = this.clienteService.getByCPF(cpf);
		if(cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
}
