package br.com.five.spring.consultorio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.five.spring.consultorio.form.RoleForm;
import br.com.five.spring.consultorio.modelo.PapelModel;
import br.com.five.spring.consultorio.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ResponseEntity<PapelModel> saveRole(@RequestBody @Valid RoleForm roleForm  ){
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(roleForm));
	}
}
