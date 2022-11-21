package br.com.five.spring.consultorio.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.five.spring.consultorio.form.CadastroUsuarioForm;

@Entity
@Table(name = "TB_users")
public class UsuarioModelo implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID usuarioId;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<PapelModel> papeis;
	
	public UsuarioModelo() {}
	
	public UsuarioModelo(@Valid CadastroUsuarioForm cadastroUsuarioForm) {
		this.papeis = new ArrayList<PapelModel>();
		this.username = cadastroUsuarioForm.getUsername();
		this.password = cadastroUsuarioForm.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.papeis;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public UUID getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(UUID uuid) {
		this.usuarioId = uuid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void adicionarPapel(PapelModel papel) {
		this.papeis.add(papel);
	}
	
	
}
