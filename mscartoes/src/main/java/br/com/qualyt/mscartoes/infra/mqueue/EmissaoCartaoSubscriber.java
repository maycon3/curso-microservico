package br.com.qualyt.mscartoes.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.qualyt.mscartoes.domain.Cartao;
import br.com.qualyt.mscartoes.domain.ClienteCartao;
import br.com.qualyt.mscartoes.domain.DadosSolicitacaoEmisaoCartao;
import br.com.qualyt.mscartoes.repositories.CartaoRepository;
import br.com.qualyt.mscartoes.repositories.ClienteCartaoRepository;

@Component
public class EmissaoCartaoSubscriber {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteCartaoRepository clienteRepository;

	@RabbitListener(queues = "${mq.queues.emissao-cartoes}")
	public void receberSolicitacaoEmissao(@Payload String payload) {
		
		var mapper = new ObjectMapper();
		try {
			DadosSolicitacaoEmisaoCartao dados = mapper
				.readValue(payload, DadosSolicitacaoEmisaoCartao.class);
			
			Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
			ClienteCartao cliente = new ClienteCartao();
			
			cliente.setCartao(cartao);
			cliente.setCpf(dados.getCpf());
			cliente.setLimite(dados.getLimiteLiberado());
			
			clienteRepository.save(cliente);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
