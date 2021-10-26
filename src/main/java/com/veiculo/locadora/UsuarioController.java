package com.veiculo.locadora;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.veiculo.locadora.model.Usuario;
import com.veiculo.locadora.repository.UsuarioRepository;

@Controller
@Transactional
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/salvarUsuario")
	private ModelAndView salvarUsuario(Usuario usuario) {
		
		usuarioRepository.save(usuario);
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

}
