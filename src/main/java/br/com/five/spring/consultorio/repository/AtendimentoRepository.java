package br.com.five.spring.consultorio.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.AtendimentoModelo;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoModelo, UUID> {

	List<AtendimentoModelo> findByDataAtendimentoBetween(LocalDate dataInicio, LocalDate dataFim);

}
