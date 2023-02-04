package br.com.qualyt.mscartoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qualyt.mscartoes.domain.ClienteCartao;

@Repository
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long>{

	List<ClienteCartao> findByCpf(String cpf);
}
