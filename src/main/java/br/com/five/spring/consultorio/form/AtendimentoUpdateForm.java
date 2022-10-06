package br.com.five.spring.consultorio.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtendimentoUpdateForm {
	
	@NotNull
	@NotEmpty
	public String observacao;
	@NotNull
	public Boolean ativo;
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservação(String observacao) {
		this.observacao = observacao.trim();
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
