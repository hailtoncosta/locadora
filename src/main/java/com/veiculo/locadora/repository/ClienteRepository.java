package com.veiculo.locadora.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.locadora.model.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where c.nome like %?1%")
	List<Cliente> findClienteByName(String nome);
	
	/*Consulta por paginação*/
	default Page<Cliente> findClienteByNamePage(String nome, Pageable pageable) {
		
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		
		/*Estamos configurando a pesquisa para consultar por partes do nome do banco de dados igual a like do sql*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*Une o objeto com o valor e a configuração para consultar*/
		Example<Cliente> example = Example.of(cliente, exampleMatcher);
		
		Page<Cliente> clientes = findAll(example, pageable);
		
		return clientes;
	}
	
}
