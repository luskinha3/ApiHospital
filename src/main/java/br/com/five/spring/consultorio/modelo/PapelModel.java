package br.com.five.spring.consultorio.modelo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;

import br.com.five.spring.consultorio.enums.RoleName;
import br.com.five.spring.consultorio.form.RoleForm;

@Entity
@Table(name = "TB_roles")
public class PapelModel implements GrantedAuthority, Serializable {


	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID roleId;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private RoleName rolename;

	public PapelModel(@Valid RoleForm roleForm) {
		this.rolename =  RoleName.valueOf(roleForm.getName());
	}
	
	public PapelModel() {}

	@Override
	public String getAuthority() {
		return this.rolename.toString();
	}

	public UUID getRoleId() {
		return roleId;
	}

	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}

	public RoleName getRolename() {
		return rolename;
	}

	public void setRolename(RoleName rolename) {
		this.rolename = rolename;
	}
	
	
	
}
