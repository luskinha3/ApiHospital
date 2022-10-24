package br.com.five.spring.consultorio.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.five.spring.consultorio.modelo.PacienteModelo;

public class PacienteDto {
	
	public String pacienteId;
	public String nome;
	public String cpf;
	
	public PacienteDto(PacienteModelo paciente) {
		this.pacienteId = paciente.getPacienteId().toString();
		this.nome = paciente.getNome();
		this.cpf = paciente.getCpf();
	}
	
	public String getPacienteId() {
		return pacienteId;
	}
	public void setId(String id) {
		this.pacienteId = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static List<PacienteDto> convertToDtoList(List<PacienteModelo> pacientes) {
		
		return pacientes.stream().map(PacienteDto :: new).collect(Collectors.toList());
	}
	
	public static Page<PacienteDto> convertToDtoPage(Page<PacienteModelo> pacientes){	
		List<PacienteDto> listPacientesDto = new ArrayList<PacienteDto>();
		pacientes.forEach(p ->listPacientesDto.add(new PacienteDto(p)));
		return new PageImpl<PacienteDto>(listPacientesDto, pacientes.getPageable(), listPacientesDto.size());
		
	}
	
	
}
