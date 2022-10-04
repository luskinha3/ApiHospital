package br.com.five.spring.consultorio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.five.spring.consultorio.form.AtendimentoForm;
import br.com.five.spring.consultorio.service.AtendimentoService;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentosController {
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@PostMapping
	public ResponseEntity<Object> saveAtendimento(@Valid @RequestBody AtendimentoForm atendimentoForm){
		return atendimentoService.save(atendimentoForm);
	}
}
