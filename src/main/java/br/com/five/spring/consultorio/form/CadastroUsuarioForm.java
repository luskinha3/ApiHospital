package br.com.five.spring.consultorio.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.five.spring.consultorio.modelo.UsuarioModelo;



public class CadastroUsuarioForm {
	
	@NotNull
	@NotEmpty
	public String username;
	@NotNull
	@NotEmpty
	public String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username.trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();
		String senhaCripto = encoder.encode(password.trim());
		this.password = senhaCripto;
	}
	public static UsuarioModelo toUsuario(@Valid CadastroUsuarioForm cadastroUsuarioForm) {
		
		return new UsuarioModelo(cadastroUsuarioForm);
	}
	
	
}
