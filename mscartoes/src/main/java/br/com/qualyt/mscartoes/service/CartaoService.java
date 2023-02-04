package br.com.qualyt.mscartoes.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.qualyt.mscartoes.domain.Cartao;
import br.com.qualyt.mscartoes.dtos.CartaoNewDTO;
import br.com.qualyt.mscartoes.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepo;
	
	@Transactional
	public Cartao save(Cartao cartao) {
		return this.cartaoRepo.save(cartao);
	}
	
	public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
		var rendaBigDecimal = BigDecimal.valueOf(renda);
		return this.cartaoRepo.findByRendaLessThanEqual(rendaBigDecimal);
	}
	
	public Cartao converte(CartaoNewDTO dto) {
		return new Cartao(null, dto.getNome(),dto.getBandeira(),dto.getRenda(),dto.getLimite());
	}
}
