package br.com.five.spring.consultorio.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.five.spring.consultorio.dto.MedicoDto;
import br.com.five.spring.consultorio.form.MedicoForm;
import br.com.five.spring.consultorio.modelo.MedicoModelo;
import br.com.five.spring.consultorio.modelo.PacienteModelo;
import br.com.five.spring.consultorio.repository.AtendimentoRepository;
import br.com.five.spring.consultorio.repository.MedicoRepository;
import br.com.five.spring.consultorio.repository.PacienteRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	public Page<MedicoModelo> getAll(Pageable pageable){
		
		return medicoRepository.findAll(pageable);
	}
	
	//private List<MedicoDto> converteMedicoToDto(List<Medico> medicos){
	//	return medicos.stream().map(MedicoDto :: new).collect(Collectors.toList());
	//}
	
	@Transactional
	public MedicoDto save(MedicoForm medicoForm) {
		return new MedicoDto(medicoRepository.save(MedicoForm.converterFormToMedico(medicoForm)));
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

	public ResponseEntity<Page<MedicoDto>> getAtendeuBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
		Page<MedicoModelo> medicos = atendimentoRepository.findMedicosAtenderamBetweenDatas(dataInicio, dataFim, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(MedicoDto.convertToDtoPage(medicos));
	}

	public ResponseEntity<Object> getByPaciente(UUID pacienteUuid, Pageable pageable) {
		Optional<PacienteModelo> pacienteOptional = pacienteRepository.findById(pacienteUuid);
		if(!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		
		Page<MedicoModelo> medicos = medicoRepository.getByPaciente(pacienteUuid, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(MedicoDto.convertToDtoPage(medicos));
	}
	
	
	
}
