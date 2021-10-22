package com.veiculo.locadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.veiculo.locadora.model.Cliente;
import com.veiculo.locadora.repository.ClienteRepository;

@Controller
public class IndexController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping("/")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
	@RequestMapping("/veiculo")
	public ModelAndView veiculo() {
		ModelAndView modelAndView = new ModelAndView("cadastro/veiculo");
		return modelAndView;
	}
	
	@RequestMapping("/cliente")
	public ModelAndView cliente() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clientes", clienteRepository.findAll(PageRequest.of(0, 4, Sort.by("nome"))));
		modelAndView.addObject("clienteobj", new Cliente());
		return modelAndView;
	}
	
	@RequestMapping("/aluguel")
	public ModelAndView aluguel() {
		ModelAndView modelAndView = new ModelAndView("cadastro/aluguel");
		return modelAndView;
	}
}
