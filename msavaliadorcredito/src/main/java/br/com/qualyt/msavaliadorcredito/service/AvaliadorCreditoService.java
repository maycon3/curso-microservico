package br.com.qualyt.msavaliadorcredito.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.qualyt.msavaliadorcredito.clients.CartoesResourceClient;
import br.com.qualyt.msavaliadorcredito.clients.ClienteResourceClient;
import br.com.qualyt.msavaliadorcredito.controller.exception.DadosClienteNotFoundException;
import br.com.qualyt.msavaliadorcredito.controller.exception.ErroComunicaoMicroservicesException;
import br.com.qualyt.msavaliadorcredito.controller.exception.ErroSolicitacaoCartaoException;
import br.com.qualyt.msavaliadorcredito.domain.Cartao;
import br.com.qualyt.msavaliadorcredito.domain.CartaoAprovado;
import br.com.qualyt.msavaliadorcredito.domain.CartaoCliente;
import br.com.qualyt.msavaliadorcredito.domain.DadosCliente;
import br.com.qualyt.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;
import br.com.qualyt.msavaliadorcredito.domain.ProtocoloSolicitacaoCartao;
import br.com.qualyt.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import br.com.qualyt.msavaliadorcredito.domain.SituacaoCliente;
import br.com.qualyt.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException.FeignClientException;

@Service
public class AvaliadorCreditoService {

	@Autowired
	private ClienteResourceClient clienteClient;

	@Autowired
	private CartoesResourceClient cartoesClient;
	
	@Autowired
	private SolicitacaoEmissaoCartaoPublisher solicitacaoPublisher;

	public SituacaoCliente obterStiuacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ErroComunicaoMicroservicesException {
		try {
			DadosCliente dadosCleintes = this.clienteClient.dadosCliente(cpf).getBody();
			List<CartaoCliente> cartoesClientes = this.cartoesClient.getCartoesByClientes(cpf).getBody();

			return new SituacaoCliente(dadosCleintes, cartoesClientes);
		} catch (FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicaoMicroservicesException(e.getMessage(), status);
		}
	}
	

	public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
			throws DadosClienteNotFoundException, ErroComunicaoMicroservicesException {
		try {
			DadosCliente dadosClientes = this.clienteClient.dadosCliente(cpf).getBody();
			List<Cartao> cartoes = this.cartoesClient.getCartoesRendaAteh(renda).getBody();
			
			var listaCartoesAprovados = cartoes.stream().map(cartao -> {
				BigDecimal limiteBasico = cartao.getLimite();
				BigDecimal idadeBD = BigDecimal.valueOf(dadosClientes.getIdade());
				var fator = idadeBD.divide(BigDecimal.valueOf(10));
				BigDecimal limiteAprovado = fator.multiply(limiteBasico);
				
				CartaoAprovado aprovado = new CartaoAprovado();
				aprovado.setCartao(cartao.getNome());
				aprovado.setBandeira(cartao.getBandeira());
				aprovado.setLimiteAprovado(limiteAprovado);
				
				return aprovado;
			}).collect(Collectors.toList());
			
			return new RetornoAvaliacaoCliente(listaCartoesAprovados);
			
		}catch (FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicaoMicroservicesException(e.getMessage(), status);
		}

	}
	
	public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
		try {
			this.solicitacaoPublisher.solicitarCartao(dados);
			return new ProtocoloSolicitacaoCartao(UUID.randomUUID().toString());
		}catch(Exception e) {
			throw new ErroSolicitacaoCartaoException(e.getMessage());
		}
	}
}
