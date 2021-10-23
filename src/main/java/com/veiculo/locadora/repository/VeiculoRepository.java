package com.veiculo.locadora.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.veiculo.locadora.model.Veiculo;

@Repository
@Transactional
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	/*Consulta por paginação*/
	default Page<Veiculo> findVeiculoByMarcaPage(String modelo, Pageable pageable) {
		
		Veiculo veiculo = new Veiculo();
		veiculo.setModelo(modelo);;
		
		/*Estamos configurando a pesquisa para consultar por partes do nome do banco de dados igual a like do sql*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("modelo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*Une o objeto com o valor e a configuração para consultar*/
		Example<Veiculo> example = Example.of(veiculo, exampleMatcher);
		
		Page<Veiculo> veiculos = findAll(example, pageable);
		
		return veiculos;
		
	}

}
