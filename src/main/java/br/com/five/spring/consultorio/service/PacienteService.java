package br.com.five.spring.consultorio.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
	
	
	public Page<PacienteModelo> getAll(Pageable pageable){
		
		return pacienteRepository.findAll(pageable);
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

	public ResponseEntity<Object> getByMedico(UUID medicoUuid, Pageable pageable) {
		Optional<MedicoModelo> medicoOptional = medicoRepository.findById(medicoUuid);
		if(!medicoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado");
		}
		
		Page<PacienteModelo> pacientes = pacienteRepository.getByMedico(medicoUuid, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(PacienteDto.convertToDtoPage(pacientes));
	}
	
	public ResponseEntity<InputStreamResource> generatePdf(Pageable pageable)  {
		
		Page<PacienteModelo> pacientes = pacienteRepository.findAll(pageable);
		
		 ByteArrayInputStream bis = formatarPdf(pacientes.toList());

	        var headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=pacientes.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
		
		
	}

	private ByteArrayInputStream formatarPdf(List<PacienteModelo> pacientes) {
		
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4, 2, 2, 2});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;


            hcell = new PdfPCell(new Phrase("Nome", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("CPF", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("DATA NASCIMENTO", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("SEXO", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            pacientes.forEach(p -> {
            	
            	 PdfPCell cell;
            	 
                 cell = new PdfPCell(new Phrase(p.getNome()));
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(String.valueOf(p.getCpf())));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setPaddingRight(5);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(String.valueOf(p.getDataNascimento())));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setPaddingRight(5);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(String.valueOf(p.getSexo())));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setPaddingRight(5);
                 table.addCell(cell);
            });
               

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            //logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
	}
	
}
