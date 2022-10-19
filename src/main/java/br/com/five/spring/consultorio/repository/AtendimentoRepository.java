package br.com.five.spring.consultorio.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.AtendimentoModelo;
import br.com.five.spring.consultorio.modelo.MedicoModelo;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoModelo, UUID> {

	Page<AtendimentoModelo> findByDataAtendimentoBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
	
	@Query("SELECT atendimento.medico FROM AtendimentoModelo atendimento  where atendimento.dataAtendimento between ?1 and ?2")
	Page<MedicoModelo> findMedicosAtenderamBetweenDatas (LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
}
