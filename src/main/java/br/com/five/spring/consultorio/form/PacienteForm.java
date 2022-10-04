package br.com.five.spring.consultorio.form;

import java.time.LocalDate;

import br.com.five.spring.consultorio.modelo.SexoEnum;

public class PacienteForm {
	
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private SexoEnum sexo;
	
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
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	
	
}
