package br.com.five.spring.consultorio.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.five.spring.consultorio.dto.AtendimentoDto;
import br.com.five.spring.consultorio.form.AtendimentoForm;
import br.com.five.spring.consultorio.form.AtendimentoUpdateForm;
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
	
	@PutMapping("/{uuid}")
	public ResponseEntity<Object> updateAtendimento(@PathVariable UUID uuid, @Valid @RequestBody AtendimentoUpdateForm atendimentoUpdateForm){
		return atendimentoService.update(uuid, atendimentoUpdateForm);
	}
	
	@GetMapping("/{dataInicio}/{dataFim}")
	public ResponseEntity<List<AtendimentoDto>> getAtendimentosBetween(@PathVariable String dataInicio, @PathVariable String dataFim ){
			
		return atendimentoService.getBetween(LocalDate.parse(dataInicio), LocalDate.parse(dataFim));
	}
}
