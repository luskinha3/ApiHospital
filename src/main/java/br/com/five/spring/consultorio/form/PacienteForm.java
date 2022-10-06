package br.com.five.spring.consultorio.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat
	private LocalDate dataNascimento;
	private SexoEnum sexo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.trim();
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
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
