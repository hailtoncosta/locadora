package com.veiculo.locadora;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		Iterable<Cliente> clienteIt = clienteRepository.findAll();//Listar clientes na tabela ao salvar
		modelAndView.addObject("clientes", clienteIt);
		modelAndView.addObject("clienteobj", new Cliente());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/listarCliente")
	public ModelAndView listarCliente() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		Iterable<Cliente> clienteIt = clienteRepository.findAll();
		modelAndView.addObject("clientes", clienteIt);
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
		modelAndView.addObject("clientes", clienteRepository.findAll());
		modelAndView.addObject("clienteobj", new Cliente());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisarVeiculo")
	public ModelAndView buscarCliente(@PathParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cliente");
		modelAndView.addObject("clientes", clienteRepository.findClienteByName(nomepesquisa));
		modelAndView.addObject("clienteobj", new Cliente());
		return modelAndView;
	}
	
}
