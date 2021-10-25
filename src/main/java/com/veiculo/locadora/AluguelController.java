package com.veiculo.locadora;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.veiculo.locadora.model.Aluguel;
import com.veiculo.locadora.repository.AluguelRepository;

@Controller
public class AluguelController {
	
	@Autowired
	private AluguelRepository aluguelRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/salvaraluguel")
	public ModelAndView salvarAluguel(Aluguel aluguel) {
		
		aluguelRepository.save(aluguel);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/aluguel");
		modelAndView.addObject("alugueis", aluguelRepository.findAll(PageRequest.of(0, 4, Sort.by("dataAluguel"))));
		modelAndView.addObject("aluguelobj", new Aluguel());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/listaraluguel")
	public ModelAndView listarAluguel() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/aluguel");
		modelAndView.addObject("alugueis", aluguelRepository.findAll(PageRequest.of(0, 4, Sort.by("dataAluguel"))));
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editaraluguel/{idaluguel}")
	public ModelAndView editarAluguel(@PathVariable("idaluguel") Long idaluguel) {
		
		Optional<Aluguel> alugueis = aluguelRepository.findById(idaluguel);
		ModelAndView modelAndView = new ModelAndView("cadastro/aluguel");
		modelAndView.addObject("alugueis", aluguelRepository.findAll(PageRequest.of(0, 4, Sort.by("dataAluguel"))));
		modelAndView.addObject("aluguelobj", alugueis.get());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletaraluguel/{idaluguel}")
	public ModelAndView deletarAluguel(@PathVariable("idaluguel") Long idaluguel) {
		
		aluguelRepository.deleteById(idaluguel);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/aluguel");
		modelAndView.addObject("alugueis", aluguelRepository.findAll(PageRequest.of(0, 4, Sort.by("dataAluguel"))));
		modelAndView.addObject("aluguelobj", new Aluguel());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisaraluguel")
	public ModelAndView pesquisarAluguel(@PathParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size = 4, sort={"dataAluguel"}) Pageable pageable) {
		
		Page<Aluguel> alugueis = null;
		
		alugueis = aluguelRepository.findAluguelBydataAluguelPage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/aluguel");
		modelAndView.addObject("alugueis", alugueis);
		modelAndView.addObject("aluguelobj", new Aluguel());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/aluguelpage")
	public ModelAndView carregarAluguelPorPaginacao(@PageableDefault(size = 4) Pageable pageable, ModelAndView modelAndView,
			@RequestParam("nomepesquisa") String nomepesquisa) {
		
		Page<Aluguel> pageAluguel = aluguelRepository.findAluguelBydataAluguelPage(nomepesquisa, pageable);
		modelAndView.addObject("alugueis", pageAluguel);
		modelAndView.addObject("aluguelobj", new Aluguel());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		modelAndView.setViewName("cadastro/aluguel");
		return modelAndView;
	}
	
}
