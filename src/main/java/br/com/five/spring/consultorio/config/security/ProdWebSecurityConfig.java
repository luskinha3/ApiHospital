package br.com.five.spring.consultorio.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

import br.com.five.spring.consultorio.enums.RoleName;
import br.com.five.spring.consultorio.filters.CsrfLoggerFilter;

@Configuration
@Profile("prod")
public class ProdWebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.GET, "/**").hasAnyAuthority(RoleName.ROLE_USER.toString(), RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.PUT, "/**").hasAnyAuthority(RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())			
			.antMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority(RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST, "/atendimentos").hasAnyAuthority(RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST, "/medicos").hasAnyAuthority(RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST, "/pacientes").hasAnyAuthority(RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST, "/usuarios/default").hasAnyAuthority(RoleName.ROLE_ADMIN.toString(), RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST, "/usuarios/admin").hasAnyAuthority(RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST, "/role").hasAnyAuthority(RoleName.ROLE_SUPER_USER.toString())
			.antMatchers(HttpMethod.POST,"/usuarios/super").denyAll()
			.anyRequest().authenticated()
			.and().addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
