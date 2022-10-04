package br.com.five.spring.consultorio.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.five.spring.consultorio.form.AtendimentoForm;
import br.com.five.spring.consultorio.modelo.AtendimentoModelo;
import br.com.five.spring.consultorio.modelo.MedicoModelo;
import br.com.five.spring.consultorio.modelo.PacienteModelo;
import br.com.five.spring.consultorio.repository.AtendimentoRepository;
import br.com.five.spring.consultorio.repository.MedicoRepository;
import br.com.five.spring.consultorio.repository.PacienteRepository;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private MedicoRepository medicoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Transactional
	public ResponseEntity<Object> save(AtendimentoForm atendimentoForm){
		
		Optional<MedicoModelo> medicoOptional = medicoRepository.findById(UUID.fromString(atendimentoForm.getMedicoId()));
		if(!medicoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado");
		}
		
		Optional<PacienteModelo> pacienteOptional = pacienteRepository.findById(UUID.fromString(atendimentoForm.getPacienteId()));
		if(!medicoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		
		MedicoModelo medico = medicoOptional.get();
		PacienteModelo paciente = pacienteOptional.get();
		
		AtendimentoModelo atendimento = converterFormToAtendimento(atendimentoForm);
		atendimento.setDataAtendimento(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
		atendimento.setMedico(medico);
		atendimento.setPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoRepository.save(atendimento));
	}
	
	public AtendimentoModelo converterFormToAtendimento(AtendimentoForm atendimentoForm) {
		return new AtendimentoModelo (atendimentoForm);
	}
}
