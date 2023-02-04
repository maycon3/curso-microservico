package br.com.qualyt.msavaliadorcredito.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.qualyt.msavaliadorcredito.domain.Cartao;
import br.com.qualyt.msavaliadorcredito.domain.CartaoCliente;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {
	
	@GetMapping(params = "cpf")
	ResponseEntity<List<CartaoCliente>> getCartoesByClientes(@RequestParam("cpf") String cpf);
	
	@GetMapping(params = "renda")
	ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda);

}
