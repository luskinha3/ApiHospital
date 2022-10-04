package br.com.five.spring.consultorio.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.five.spring.consultorio.dto.MedicoDto;
import br.com.five.spring.consultorio.form.MedicoForm;
import br.com.five.spring.consultorio.modelo.MedicoModelo;
import br.com.five.spring.consultorio.repository.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	
	public List<MedicoModelo> getAll(){
		
		return medicoRepository.findAll();
	}
	
	//private List<MedicoDto> converteMedicoToDto(List<Medico> medicos){
	//	return medicos.stream().map(MedicoDto :: new).collect(Collectors.toList());
	//}
	
	@Transactional
	public MedicoDto save(MedicoForm medicoForm) {
		
		MedicoModelo medico = medicoRepository.save(converterFormToMedico(medicoForm));
		return new MedicoDto(medico);
	}

	private MedicoModelo converterFormToMedico(MedicoForm medicoForm) {
		return new MedicoModelo (medicoForm);
	}
	
	@Transactional
	public ResponseEntity<Object> delete(UUID uuid) {
		Optional<MedicoModelo> optional = medicoRepository.findById(uuid);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado");
		}
		MedicoModelo medico = optional.get();
		if(medico.hasAtendimentos()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não pode deleter um Medico que contem atendimentos");
		}
		medicoRepository.delete(medico);
		return ResponseEntity.status(HttpStatus.OK).body("Medico excluído com sucesso");
	}
	
	@Transactional
	public ResponseEntity<Object> update(UUID uuid, MedicoForm medicoForm) {
		Optional<MedicoModelo> optional = medicoRepository.findById(uuid);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado");
		}
		MedicoModelo medico = optional.get();
		medico.setNome(medicoForm.getNome());
		medico.setCpf(medicoForm.getCpf());
		medico.setCrm(medicoForm.getCrm());
		medico.setDataNascimento(medicoForm.getDataNascimento());
		medico.setSexo(medicoForm.getSexo());
		
		medicoRepository.save(medico);
		
		return ResponseEntity.status(HttpStatus.OK).body(new MedicoDto(medico));
	}
	
	
	
}
