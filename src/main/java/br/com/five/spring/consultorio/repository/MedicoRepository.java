package br.com.five.spring.consultorio.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.MedicoModelo;

@Repository
public interface MedicoRepository extends  JpaRepository<MedicoModelo, UUID>{
	
	@Query("SELECT m FROM MedicoModelo m JOIN AtendimentoModelo a ON a.paciente.pacienteId = ?1 GROUP BY m.medicoId")
	Page<MedicoModelo> getByPaciente(UUID pacienteUuid, Pageable pageable);
	
	
	
}
