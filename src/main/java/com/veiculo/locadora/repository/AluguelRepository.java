package com.veiculo.locadora.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.locadora.model.Aluguel;

@Repository
@Transactional
public interface AluguelRepository extends JpaRepository<Aluguel, Long>{
	
	/*Consulta por paginação*/
	default Page<Aluguel> findAluguelBydataAluguelPage(String dataAluguel, Pageable pageable) {
		
		Aluguel aluguel = new Aluguel();
		aluguel.setDataAluguel(dataAluguel);
		
		/*Estamos configurando a pesquisa para consultar por partes do nome do banco de dados igual a like do sql*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("dataAluguel", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*Une o objeto com o valor e a configuração para consultar*/
		Example<Aluguel> example = Example.of(aluguel, exampleMatcher);
		
		Page<Aluguel> alugueis = findAll(example, pageable);
		
		return alugueis;
	}
}
