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

import br.com.five.spring.consultorio.enums.SexoEnum;
import br.com.five.spring.consultorio.form.MedicoForm;

@Entity
@Table(name = "TB_medicos")
public class MedicoModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID medicoId;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	private String crm;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medico")
	private List<AtendimentoModelo> atendimentos = new ArrayList<>();
	
	
	public MedicoModelo() {}
	
	public MedicoModelo(MedicoForm medicoForm) {
		this.nome = medicoForm.getNome();
		this.cpf = medicoForm.getCpf();
		this.dataNascimento = medicoForm.getDataNascimento();
		this.sexo = medicoForm.getSexo();
		this.crm = medicoForm.getCrm();
	}
	public UUID getMedicoId() {
		return medicoId;
	}
	public void setMedicoId (UUID medicoId) {
		this.medicoId = medicoId;
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
		this.crm = crm;
	}
	
	public boolean hasAtendimentos() {
		return !this.atendimentos.isEmpty();
	}

	
	
	
	
}
