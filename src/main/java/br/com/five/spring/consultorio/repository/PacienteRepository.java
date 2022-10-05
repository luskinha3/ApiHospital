package br.com.five.spring.consultorio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.five.spring.consultorio.modelo.PacienteModelo;

public interface PacienteRepository extends JpaRepository<PacienteModelo, UUID> {
	
	@Query("SELECT p FROM PacienteModelo p JOIN MedicoModelo m on m.id = ?1 ")
	List<PacienteModelo> getByMedico(UUID medicoUuid);

}
