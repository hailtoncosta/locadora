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

import com.veiculo.locadora.model.Cliente;
import com.veiculo.locadora.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/salvarCliente")
	public ModelAndView salvarCliente(Cliente cliente) {
		
		clienteRepository.save(cliente);
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clientes", clienteRepository.findAll(PageRequest.of(0, 4, Sort.by("nome"))));
		modelAndView.addObject("clienteobj", new Cliente());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/listarCliente")
	public ModelAndView listarCliente() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clientes", clienteRepository.findAll(PageRequest.of(0, 4, Sort.by("nome"))));
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarCliente/{idcliente}")
	public ModelAndView editarCliente(@PathVariable("idcliente") Long idcliente) {
		
		Optional<Cliente> clientes = clienteRepository.findById(idcliente);
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clienteobj", clientes.get());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletarCliente/{idcliente}")
	public ModelAndView deletarCliente(@PathVariable("idcliente") Long idcliente) {
		
		clienteRepository.deleteById(idcliente);
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clientes", clienteRepository.findAll(PageRequest.of(0, 4, Sort.by("nome"))));
		modelAndView.addObject("clienteobj", new Cliente());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisarVeiculo")
	public ModelAndView buscarCliente(@PathParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
		
		Page<Cliente> clientes = null;
		
		clientes = clienteRepository.findClienteByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("clienteobj", new Cliente());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clientespage")
	public ModelAndView carregaClientesPorPaginacao(@PageableDefault(size = 4) Pageable pageable, ModelAndView modelAndView, 
			@RequestParam("nomepesquisa") String nomepesquisa) {
		
		Page<Cliente> pageCliente = clienteRepository.findClienteByNamePage(nomepesquisa, pageable);
		modelAndView.addObject("clientes", pageCliente);
		modelAndView.addObject("clienteobj", new Cliente());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		modelAndView.setViewName("cadastro/cliente");
		return modelAndView;
	}
	
}
