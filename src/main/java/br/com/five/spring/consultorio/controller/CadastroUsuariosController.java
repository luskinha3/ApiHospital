package br.com.five.spring.consultorio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.five.spring.consultorio.form.CadastroUsuarioForm;
import br.com.five.spring.consultorio.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuariosController {
	
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	
	@PostMapping("/default")
	public ResponseEntity<Object> saveUsuarioDefault(@RequestBody @Valid CadastroUsuarioForm cadastroUsuarioForm ){
		return cadastroUsuarioService.saveDefault(cadastroUsuarioForm);
	}
	
	@PostMapping("/admin")
	public ResponseEntity<Object> saveUsuarioAdmin(@RequestBody @Valid CadastroUsuarioForm cadastroUsuarioForm ){
		return cadastroUsuarioService.saveAdmin(cadastroUsuarioForm);
	}
}
