package br.com.five.spring.consultorio.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import br.com.five.spring.consultorio.dto.PacienteDto;
import br.com.five.spring.consultorio.form.PacienteForm;
import br.com.five.spring.consultorio.modelo.MedicoModelo;
import br.com.five.spring.consultorio.modelo.PacienteModelo;
import br.com.five.spring.consultorio.repository.MedicoRepository;
import br.com.five.spring.consultorio.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	
	public List<PacienteModelo> getAll(){
		
		return pacienteRepository.findAll();
	}
	
	//private List<MedicoDto> converteMedicoToDto(List<Medico> medicos){
	//	return medicos.stream().map(MedicoDto :: new).collect(Collectors.toList());
	//}
	
	@Transactional
	public PacienteDto save(PacienteForm pacienteForm) {
		
		return new PacienteDto(pacienteRepository.save(PacienteForm.converterFormToPaciente(pacienteForm)));
	}

	
	
	@Transactional
	public ResponseEntity<Object> delete(UUID uuid) {
		Optional<PacienteModelo> optional = pacienteRepository.findById(uuid);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		PacienteModelo paciente = optional.get();
		if(paciente.hasAtendimentos()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não pode deleter um Paciente que contem atendimentos");
		}
		pacienteRepository.delete(paciente);
		return ResponseEntity.status(HttpStatus.OK).body("Paciente excluído com sucesso");
	}
	
	@Transactional
	public ResponseEntity<Object> update(UUID uuid, PacienteForm pacienteForm) {
		Optional<PacienteModelo> optional = pacienteRepository.findById(uuid);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		PacienteModelo paciente = optional.get();
		paciente.setNome(pacienteForm.getNome());
		paciente.setCpf(pacienteForm.getCpf());
		paciente.setDataNascimento(pacienteForm.getDataNascimento());
		paciente.setSexo(pacienteForm.getSexo());
		
		pacienteRepository.save(paciente);
		
		return ResponseEntity.status(HttpStatus.OK).body(new PacienteDto(paciente));
	}

	public ResponseEntity<Object> getByMedico(UUID medicoUuid) {
		Optional<MedicoModelo> medicoOptional = medicoRepository.findById(medicoUuid);
		if(!medicoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado");
		}
		
		List<PacienteModelo> pacientes = pacienteRepository.getByMedico(medicoUuid);
		
		return ResponseEntity.status(HttpStatus.OK).body(PacienteDto.convertToDtoList(pacientes));
	}
	
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		
		List<PacienteModelo> pacientes = pacienteRepository.findAll();
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);

		pacientes.forEach(p -> {
			Paragraph paragrafo = new Paragraph(p.toString(), font);
			paragrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(paragrafo);
		});
		
		document.close();
	}
	
}
