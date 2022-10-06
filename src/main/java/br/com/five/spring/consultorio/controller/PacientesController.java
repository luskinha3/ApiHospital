package br.com.five.spring.consultorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.lowagie.text.DocumentException;

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
	public ResponseEntity<List<PacienteModelo>> getAllPacientes(){
		return ResponseEntity.status(HttpStatus.OK).body(pacienteService.getAll());
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
	public ResponseEntity<Object> getPacientesByMedico(@PathVariable UUID medicoUuid){
		return pacienteService.getByMedico(medicoUuid);
	}
	
	@GetMapping("/pdf")
	public void getPacientesPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filenam=pdf_pacientes"; 
		response.setHeader(headerKey, headerValue);
		
		pacienteService.generatePdf(response);
	}
	
}
	
