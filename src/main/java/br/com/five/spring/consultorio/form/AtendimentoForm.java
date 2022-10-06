package br.com.five.spring.consultorio.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.five.spring.consultorio.modelo.AtendimentoModelo;

public class AtendimentoForm {
	
	@NotNull
	@NotEmpty
	private String pacienteId;
	@NotNull
	@NotEmpty
	private String medicoId;
	@NotNull
	@NotEmpty
	private String observacao;
	
	
	public String getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(String pacienteId) {
		this.pacienteId = pacienteId.trim();
	}
	public String getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(String medicoId) {
		this.medicoId = medicoId.trim();
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao.trim();
	}

	
	public static AtendimentoModelo converterFormToAtendimento(AtendimentoForm atendimentoForm) {
		return new AtendimentoModelo (atendimentoForm);
	}
	
}
