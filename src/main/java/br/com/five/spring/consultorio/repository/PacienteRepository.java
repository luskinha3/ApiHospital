package br.com.five.spring.consultorio.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.five.spring.consultorio.modelo.PacienteModelo;

public interface PacienteRepository extends JpaRepository<PacienteModelo, UUID> {
	
	@Query("SELECT p FROM PacienteModelo p JOIN MedicoModelo m on m.id = ?1 ")
	Page<PacienteModelo> getByMedico(UUID medicoUuid, Pageable pageable);

}
