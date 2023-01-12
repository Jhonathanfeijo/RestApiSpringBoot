package br.com.domain.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.model.Usuario;
import br.com.domain.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioServiceImpl usuarios;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvarUsuario(@RequestBody @Valid Usuario usuario) {
		String senhaCriptograda = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptograda);
		return usuarios.salvar(usuario);
	}

}
