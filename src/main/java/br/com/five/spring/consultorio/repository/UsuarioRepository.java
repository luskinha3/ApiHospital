package br.com.five.spring.consultorio.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.UsuarioModelo;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModelo, UUID> {
	
	Optional<UsuarioModelo> findByUsername (String username);
}
