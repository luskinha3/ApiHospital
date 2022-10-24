package br.com.five.spring.consultorio.modelo;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.five.spring.consultorio.form.AtendimentoForm;

@Entity
@Table(name = "TB_atendimentos")
public class AtendimentoModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID atendimentoId;
	private LocalDate dataAtendimento;
	@ManyToOne
	private MedicoModelo medico;
	@ManyToOne
	private PacienteModelo paciente;
	private String observacao;
	private Boolean ativo;
	
	public AtendimentoModelo () {}	
	
	public AtendimentoModelo(AtendimentoForm atendimentoForm) {
		this.observacao = atendimentoForm.getObservacao();
		this.ativo = true;
	}
	public UUID getAtendimentoId() {
		return atendimentoId;
	}
	public void setAtendimentoId(UUID atendimentoId) {
		this.atendimentoId = atendimentoId;
	}
	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public MedicoModelo getMedico() {
		return medico;
	}
	public void setMedico(MedicoModelo medico) {
		this.medico = medico;
	}
	public PacienteModelo getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteModelo paciente) {
		this.paciente = paciente;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	public String getNomeMedico() {
		return medico.getNome();
	}
	
	public String getNomePaciente() {
		return paciente.getNome();
	}

	
	
	
}
