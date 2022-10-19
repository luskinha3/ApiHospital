package br.com.five.spring.consultorio.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.five.spring.consultorio.enums.SexoEnum;
import br.com.five.spring.consultorio.modelo.MedicoModelo;
import br.com.five.spring.consultorio.validator.ValidarCPF;
import br.com.five.spring.consultorio.validator.ValidarCRM;

public class MedicoForm {
	
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
	@NotNull
	@NotEmpty
	@ValidarCRM
	private String crm;
	
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
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm.trim();
	}
	
	public static MedicoModelo converterFormToMedico(MedicoForm medicoForm) {
		return new MedicoModelo (medicoForm);
	}
	
	
}
