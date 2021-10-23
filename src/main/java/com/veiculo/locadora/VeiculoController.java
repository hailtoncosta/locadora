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

import com.veiculo.locadora.model.Veiculo;
import com.veiculo.locadora.repository.VeiculoRepository;

@Controller
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/salvarVeiculo")
	public ModelAndView salvarVeiculo(Veiculo veiculo) {
		
		veiculoRepository.save(veiculo);
		ModelAndView modelAndView = new ModelAndView("cadastro/veiculo");
		modelAndView.addObject("veiculos", veiculoRepository.findAll(PageRequest.of(0, 4, Sort.by("modelo"))));
		modelAndView.addObject("veiculoobj", new Veiculo());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarVeiculo/{idveiculo}")
	public ModelAndView editarVeiculo(@PathVariable("idveiculo") Long idveiculo) {
		
		Optional<Veiculo> veiculos = veiculoRepository.findById(idveiculo);
		ModelAndView modelAndView = new ModelAndView("cadastro/veiculo");
		modelAndView.addObject("veiculos", veiculoRepository.findAll(PageRequest.of(0, 4, Sort.by("modelo"))));
		modelAndView.addObject("veiculoobj", veiculos.get());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletarVeiculo/{idveiculo}")
	public ModelAndView deletarVeiculo(@PathVariable("idveiculo") Long idveiculo) {
		
		veiculoRepository.deleteById(idveiculo);
		ModelAndView modelAndView = new ModelAndView("cadastro/veiculo");
		modelAndView.addObject("veiculos", veiculoRepository.findAll(PageRequest.of(0, 4, Sort.by("modelo"))));
		modelAndView.addObject("veiculoobj", new Veiculo());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/listarVeiculo")
	public ModelAndView listarVeiculo() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/veiculo");
		modelAndView.addObject("veiculos", veiculoRepository.findAll(PageRequest.of(0, 4, Sort.by("modelo"))));
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisarVeiculo")
	public ModelAndView pesquisarVeiculo(@PathParam("nomepesquisa") String nomepesquisa, 
				@PageableDefault(size = 4, sort = {"modelo"}) Pageable pageable) {
		
		Page<Veiculo> veiculos = null;
		
		veiculos = veiculoRepository.findVeiculoByMarcaPage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/veiculo");
		modelAndView.addObject("veiculos", veiculos);
		modelAndView.addObject("veiculoobj", new Veiculo());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/veiculospage")
	public ModelAndView carregarVeiculosPorPaginacao(@PageableDefault(size = 4) Pageable pageable, ModelAndView modelAndView,
			@RequestParam("nomepesquisa") String nomepesquisa) {
		
		Page<Veiculo> pageVeiculo = veiculoRepository.findVeiculoByMarcaPage(nomepesquisa, pageable);
		modelAndView.addObject("veiculos", pageVeiculo);
		modelAndView.addObject("veiculoobj", new Veiculo());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		modelAndView.setViewName("cadastro/veiculo");
		return modelAndView;
	}
	
}
