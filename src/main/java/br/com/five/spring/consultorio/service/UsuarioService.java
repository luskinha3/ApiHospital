package br.com.five.spring.consultorio.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.five.spring.consultorio.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UserDetails getUserByUsername(String username) {
		
		UserDetails usuario = usuarioRepository.findByUsername(username).orElseThrow(() 
				-> new UsernameNotFoundException("usuario não encontrado: " + username));
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}
	
}
