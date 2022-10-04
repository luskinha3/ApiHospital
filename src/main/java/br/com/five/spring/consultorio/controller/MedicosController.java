package br.com.five.spring.consultorio.controller;

import java.util.List;
import java.util.UUID;

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

import br.com.five.spring.consultorio.dto.MedicoDto;
import br.com.five.spring.consultorio.form.MedicoForm;
import br.com.five.spring.consultorio.modelo.Medico;
import br.com.five.spring.consultorio.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	public ResponseEntity<List<Medico>> getAllMedicos(){
		return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<MedicoDto> saveMedico(@RequestBody @Valid MedicoForm medicoForm){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.save(medicoForm));
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Object> deleteMedico(@PathVariable UUID uuid ){
		return medicoService.delete(uuid);
	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<Object> updateMedico(@PathVariable UUID uuid, @Valid @RequestBody MedicoForm medicoForm){
		return medicoService.update(uuid, medicoForm);
	}
	
}
	
