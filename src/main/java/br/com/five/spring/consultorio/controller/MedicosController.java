package br.com.five.spring.consultorio.controller;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.five.spring.consultorio.dto.MedicoDto;
import br.com.five.spring.consultorio.form.MedicoForm;
import br.com.five.spring.consultorio.modelo.MedicoModelo;
import br.com.five.spring.consultorio.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	public ResponseEntity<Page<MedicoModelo>> getAllMedicos(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAll(pageable));
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
	
	@GetMapping("/{dataInicio}/{dataFim}")
	public ResponseEntity<Page<MedicoDto>> getAtendimentosBetween(@PageableDefault(page = 0, size = 10, sort = "medico.nome", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable String dataInicio, @PathVariable String dataFim ){
			
		return medicoService.getAtendeuBetween(LocalDate.parse(dataInicio), LocalDate.parse(dataFim), pageable);
	}
	
	@GetMapping("/{pacienteUuid}")
	public ResponseEntity<Object> getMedicosByPaciente(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable UUID pacienteUuid){
		return medicoService.getByPaciente(pacienteUuid, pageable);
	}
	
}
	
