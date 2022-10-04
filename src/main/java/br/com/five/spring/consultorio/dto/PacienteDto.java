package br.com.five.spring.consultorio.dto;

import br.com.five.spring.consultorio.modelo.Paciente;

public class PacienteDto {
	
	public String id;
	public String nome;
	public String cpf;
	
	public PacienteDto(Paciente paciente) {
		this.id = paciente.getId().toString();
		this.nome = paciente.getNome();
		this.cpf = paciente.getCpf();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
}
