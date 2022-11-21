package br.com.five.spring.consultorio.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.five.spring.consultorio.form.RoleForm;
import br.com.five.spring.consultorio.modelo.PapelModel;
import br.com.five.spring.consultorio.repository.PapelRepository;

@Service
public class RoleService {
	
	@Autowired
	private PapelRepository roleRepository;

	public PapelModel save(@Valid RoleForm roleForm) {
		PapelModel papelModel = RoleForm.toRole(roleForm);
		
		return roleRepository.save(papelModel);
	}
	
	
}
