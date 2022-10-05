package br.com.five.spring.consultorio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.MedicoModelo;

@Repository
public interface MedicoRepository extends  JpaRepository<MedicoModelo, UUID>{
	
	@Query("SELECT m FROM MedicoModelo m JOIN PacienteModelo p on p.id = ?1 ")
	List<MedicoModelo> getByPaciente(UUID pacienteUuid);
	
	
	
}
