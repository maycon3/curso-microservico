package br.com.qualyt.msavaliadorcredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.qualyt.msavaliadorcredito.controller.exception.DadosClienteNotFoundException;
import br.com.qualyt.msavaliadorcredito.controller.exception.ErroComunicaoMicroservicesException;
import br.com.qualyt.msavaliadorcredito.controller.exception.ErroSolicitacaoCartaoException;
import br.com.qualyt.msavaliadorcredito.domain.DadosAvaliacao;
import br.com.qualyt.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;
import br.com.qualyt.msavaliadorcredito.domain.ProtocoloSolicitacaoCartao;
import br.com.qualyt.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import br.com.qualyt.msavaliadorcredito.domain.SituacaoCliente;
import br.com.qualyt.msavaliadorcredito.service.AvaliadorCreditoService;

@RestController
@RequestMapping("/avaliacoes-credito")
public class AvaliadorCreditoController {

	@Autowired
	private AvaliadorCreditoService avaliadorClienteService;

	@GetMapping
	public String status() {
		return "Avaliação de Credito ok";
	}

	@GetMapping(value = "/situacao-cliente", params = "cpf")
	public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
		try {
			SituacaoCliente situacaoCliente = avaliadorClienteService.obterStiuacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicaoMicroservicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity realizaAvaliacao(@RequestBody DadosAvaliacao dados) {
		try {
			RetornoAvaliacaoCliente retorno = avaliadorClienteService.realizarAvaliacao(dados.getCpf(),
					dados.getRenda());
			return ResponseEntity.ok(retorno);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicaoMicroservicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}
	
	@PostMapping("/solicitacoes-cartao")
	public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
		try {
			ProtocoloSolicitacaoCartao protocolo = this.avaliadorClienteService.solicitarEmissaoCartao(dados);
			return ResponseEntity.ok().body(protocolo);
		}catch(ErroSolicitacaoCartaoException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
