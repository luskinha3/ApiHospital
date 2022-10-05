package br.com.five.spring.consultorio.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.five.spring.consultorio.modelo.PacienteModelo;
import br.com.five.spring.consultorio.modelo.SexoEnum;
import br.com.five.spring.consultorio.validator.ValidarCPF;

public class PacienteForm {
	
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	@ValidarCPF
	private String cpf;
	@NotNull
	@NotEmpty
	private LocalDate dataNascimento;
	@NotNull
	@NotEmpty
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
	
	
	public static PacienteModelo converterFormToPaciente(PacienteForm pacienteForm) {
		return new PacienteModelo (pacienteForm);
	}
	
}
