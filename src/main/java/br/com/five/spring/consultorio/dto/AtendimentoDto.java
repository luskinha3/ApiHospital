package br.com.five.spring.consultorio.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.five.spring.consultorio.modelo.AtendimentoModelo;

public class AtendimentoDto {
	
	private String uuid;
	private String nomeMedico;
	private String nomePaciente;
	private LocalDate dataAtendimento;
	private String observacao;
	private Boolean ativo;
	
	public AtendimentoDto(AtendimentoModelo atendimento) {
		this.uuid = atendimento.getId().toString();
		this.observacao = atendimento.getObservacao();
		this.ativo = atendimento.getAtivo();
		this.dataAtendimento = atendimento.getDataAtendimento();
		this.nomeMedico = atendimento.getNomeMedico();
		this.nomePaciente = atendimento.getNomePaciente();
	}
	
	public String getNomeMedico() {
		return nomeMedico;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}
	public String getObservacao() {
		return observacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}

	public String getUuid() {
		return uuid;
	}
	
	public static List<AtendimentoDto> convertToDtoList(List<AtendimentoModelo> atendimentos){
		return atendimentos.stream().map(AtendimentoDto :: new).collect(Collectors.toList());
	}
	
	public static Page<AtendimentoDto> convertToDtoPage(Page<AtendimentoModelo> atendimentos){	
		List<AtendimentoDto> listAtendimentoDto = new ArrayList<AtendimentoDto>();
		atendimentos.forEach(a ->listAtendimentoDto.add(new AtendimentoDto(a)));
		return new PageImpl<AtendimentoDto>(listAtendimentoDto, atendimentos.getPageable(), listAtendimentoDto.size());
		
	}
	
}
