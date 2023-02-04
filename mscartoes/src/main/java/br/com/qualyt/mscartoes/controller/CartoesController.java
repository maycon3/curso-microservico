package br.com.qualyt.mscartoes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.qualyt.mscartoes.domain.Cartao;
import br.com.qualyt.mscartoes.domain.ClienteCartao;
import br.com.qualyt.mscartoes.dtos.CartaoNewDTO;
import br.com.qualyt.mscartoes.dtos.CartoesPorClienteDTO;
import br.com.qualyt.mscartoes.service.CartaoService;
import br.com.qualyt.mscartoes.service.ClienteCartaoService;

@RestController
@RequestMapping(value ="/cartoes")
public class CartoesController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private ClienteCartaoService clienteService;

	@GetMapping
	public String status() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity cadastra(@RequestBody CartaoNewDTO dto) {
		Cartao cartao = this.cartaoService.converte(dto);
		this.cartaoService.save(cartao);
		return ResponseEntity.status(HttpStatus.SC_CREATED).build();
	}
	
	
	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
		List<Cartao> cartoes = this.cartaoService.getCartoesRendaMenorIgual(renda);
		return ResponseEntity.ok(cartoes);
	}
	
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartoesPorClienteDTO>> getCartoesByClientes(@RequestParam("cpf")
	     String cpf) {
			List<ClienteCartao> list = this.clienteService.listCartoesByCpf(cpf);
			List<CartoesPorClienteDTO> resultList = list
							.stream()
							.map(x -> new CartoesPorClienteDTO(x))
							.collect(Collectors.toList());
			return ResponseEntity.ok(resultList);
	}
	
	
	
}
