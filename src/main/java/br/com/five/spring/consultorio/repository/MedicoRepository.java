package br.com.five.spring.consultorio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.Medico;

@Repository
public interface MedicoRepository extends  JpaRepository<Medico, UUID>{

}
