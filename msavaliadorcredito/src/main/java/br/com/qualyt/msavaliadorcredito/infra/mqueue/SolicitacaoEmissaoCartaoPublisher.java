package br.com.qualyt.msavaliadorcredito.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.qualyt.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queueEmissaoCartoes;
	
	
	public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
		var json = converteParaString(dados);
		rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
	}
	
	
	private String converteParaString(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dados);
	}
	
}
