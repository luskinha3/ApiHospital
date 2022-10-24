package br.com.five.spring.consultorio.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.five.spring.consultorio.modelo.MedicoModelo;

public class MedicoDto {
	
	public String medicoId;
	public String nome;
	public String crm;
	
	public MedicoDto(MedicoModelo medico) {
		this.medicoId = medico.getMedicoId().toString();
		this.nome = medico.getNome();
		this.crm = medico.getCrm();
	}
	
	public String getMedicoId() {
		return medicoId;
	}
	public void setId(String uuid) {
		this.medicoId = uuid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}

	public static List<MedicoDto> convertToDtoList(List<MedicoModelo> medicos) {
		
		return medicos.stream().map(MedicoDto :: new).collect(Collectors.toList());
	}
	
	public static Page<MedicoDto> convertToDtoPage(Page<MedicoModelo> medicos){	
		List<MedicoDto> listMedicosDto = new ArrayList<MedicoDto>();
		medicos.forEach(m ->listMedicosDto.add(new MedicoDto(m)));
		return new PageImpl<MedicoDto>(listMedicosDto, medicos.getPageable(), listMedicosDto.size());
		
	}
	
	
}
