package br.com.five.spring.consultorio.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.five.spring.consultorio.dto.PacienteDto;
import br.com.five.spring.consultorio.form.PacienteForm;
import br.com.five.spring.consultorio.modelo.PacienteModelo;
import br.com.five.spring.consultorio.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public ResponseEntity<Page<PacienteModelo>> getAllPacientes(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(pacienteService.getAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<PacienteDto> savePaciente(@RequestBody @Valid PacienteForm pacienteForm){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.save(pacienteForm));
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Object> deletePaciente(@PathVariable UUID uuid ){
		return pacienteService.delete(uuid);
	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<Object> updatePaciente(@PathVariable UUID uuid, @Valid @RequestBody PacienteForm pacienteForm){
		return pacienteService.update(uuid, pacienteForm);
	}
	
	@GetMapping("/{medicoUuid}")
	public ResponseEntity<Object> getPacientesByMedico(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable UUID medicoUuid){
		return pacienteService.getByMedico(medicoUuid, pageable);
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> getPacientesPdf(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable)  {
		return pacienteService.generatePdf(pageable);
	}
	
}
	
