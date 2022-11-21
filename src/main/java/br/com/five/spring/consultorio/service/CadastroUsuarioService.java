package br.com.five.spring.consultorio.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.five.spring.consultorio.enums.RoleName;
import br.com.five.spring.consultorio.form.CadastroUsuarioForm;
import br.com.five.spring.consultorio.modelo.UsuarioModelo;
import br.com.five.spring.consultorio.repository.PapelRepository;
import br.com.five.spring.consultorio.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private PapelRepository papelRepository;

	public ResponseEntity<Object> saveDefault(@Valid CadastroUsuarioForm cadastroUsuarioForm) {
		UsuarioModelo usuario = CadastroUsuarioForm.toUsuario(cadastroUsuarioForm);		
		usuario.adicionarPapel(papelRepository.findByRolename(RoleName.ROLE_USER));
		usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}

	public ResponseEntity<Object> saveAdmin(@Valid CadastroUsuarioForm cadastroUsuarioForm) {
		UsuarioModelo usuario = CadastroUsuarioForm.toUsuario(cadastroUsuarioForm);		
		usuario.adicionarPapel(papelRepository.findByRolename(RoleName.ROLE_ADMIN));
		usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	
	}

	public ResponseEntity<Object> saveSuperUser(@Valid CadastroUsuarioForm cadastroUsuarioForm) {
		UsuarioModelo usuario = CadastroUsuarioForm.toUsuario(cadastroUsuarioForm);		
		usuario.adicionarPapel(papelRepository.findByRolename(RoleName.ROLE_SUPER_USER));
		usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
}
