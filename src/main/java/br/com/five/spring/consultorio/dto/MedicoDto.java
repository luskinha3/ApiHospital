package br.com.five.spring.consultorio.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.five.spring.consultorio.modelo.MedicoModelo;

public class MedicoDto {
	
	public String id;
	public String nome;
	public String crm;
	
	public MedicoDto(MedicoModelo medico) {
		this.id = medico.getId().toString();
		this.nome = medico.getNome();
		this.crm = medico.getCrm();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String uuid) {
		this.id = uuid;
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
	
	
}
