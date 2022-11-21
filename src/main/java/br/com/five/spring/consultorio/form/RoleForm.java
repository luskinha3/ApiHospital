package br.com.five.spring.consultorio.form;

import javax.validation.Valid;

import br.com.five.spring.consultorio.modelo.PapelModel;

public class RoleForm {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static PapelModel toRole(@Valid RoleForm roleForm) {
		return new PapelModel(roleForm);
	}
	
	
}
