package br.com.five.spring.consultorio.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.five.spring.consultorio.form.PacienteForm;

@Entity
@Table(name = "pacientes")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<Atendimento> atendimentos = new ArrayList<>();
	
	public Paciente() {}
	
	public Paciente (PacienteForm pacienteForm) {
		this.nome = pacienteForm.getNome();
		this.cpf = pacienteForm.getCpf();
		this.dataNascimento = pacienteForm.getDataNascimento();
		this.sexo = pacienteForm.getSexo();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
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

	public boolean hasAtendimentos() {
		return ! this.atendimentos.isEmpty();
	}
	
	
}
