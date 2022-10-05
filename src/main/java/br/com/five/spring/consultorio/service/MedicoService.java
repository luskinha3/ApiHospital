package br.com.five.spring.consultorio.service;

import java.time.LocalDate;
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
	
	
	public List<MedicoModelo> getAll(){
		
		return medicoRepository.findAll();
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

	public ResponseEntity<List<MedicoDto>> getAtendeuBetween(LocalDate dataInicio, LocalDate dataFim) {
		List<MedicoModelo> medicos = atendimentoRepository.findMedicosAtenderamBetweenDatas(dataInicio, dataFim);
		return ResponseEntity.status(HttpStatus.OK).body(MedicoDto.convertToDtoList(medicos));
	}

	public ResponseEntity<Object> getByPaciente(UUID pacienteUuid) {
		Optional<PacienteModelo> pacienteOptional = pacienteRepository.findById(pacienteUuid);
		if(!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		
		List<MedicoModelo> medicos = medicoRepository.getByPaciente(pacienteUuid);
		
		return ResponseEntity.status(HttpStatus.OK).body(MedicoDto.convertToDtoList(medicos));
	}
	
	
	
}
