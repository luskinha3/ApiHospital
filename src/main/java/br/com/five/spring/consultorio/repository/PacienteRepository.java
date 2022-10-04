package br.com.five.spring.consultorio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.five.spring.consultorio.modelo.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

}
