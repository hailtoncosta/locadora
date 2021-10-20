package com.veiculo.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.locadora.model.Cliente;

@Controller
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where c.nome like %?1%")
	List<Cliente> findClienteByName(String nome);
	
}
