package br.com.five.spring.consultorio.form;

import br.com.five.spring.consultorio.modelo.AtendimentoModelo;

public class AtendimentoForm {
	
	private String pacienteId;
	private String medicoId;
	private String observacao;
	private boolean ativo;
	
	public String getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(String pacienteId) {
		this.pacienteId = pacienteId;
	}
	public String getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(String medicoId) {
		this.medicoId = medicoId;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public static AtendimentoModelo converterFormToAtendimento(AtendimentoForm atendimentoForm) {
		return new AtendimentoModelo (atendimentoForm);
	}
	
}
