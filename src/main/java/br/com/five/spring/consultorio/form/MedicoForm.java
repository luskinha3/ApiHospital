package br.com.five.spring.consultorio.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.five.spring.consultorio.modelo.Sexo;

public class MedicoForm {
	
	@NotNull
	@NotEmpty
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private Sexo sexo;
	private String crm;
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
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	
}