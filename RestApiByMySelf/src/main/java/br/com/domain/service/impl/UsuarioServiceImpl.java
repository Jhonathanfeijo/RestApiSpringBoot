package br.com.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.domain.model.Usuario;
import br.com.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarios;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario =usuarios.findByusuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		String [] roles = usuario.isAdmin() ? new String [] {"USER","ADMIN"} : new String [] {"USER"};
		return User.builder().username(usuario.getUsuario()).password(usuario.getSenha()).roles(roles).build();
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuarios.save(usuario);
	}

}
