package br.com.five.spring.consultorio.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.five.spring.consultorio.modelo.AtendimentoModelo;
import br.com.five.spring.consultorio.modelo.MedicoModelo;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoModelo, UUID> {

	List<AtendimentoModelo> findByDataAtendimentoBetween(LocalDate dataInicio, LocalDate dataFim);
	
	@Query("SELECT atendimento.medico FROM AtendimentoModelo atendimento  where atendimento.dataAtendimento between ?1 and ?2")
	List<MedicoModelo> findMedicosAtenderamBetweenDatas (LocalDate dataInicio, LocalDate dataFim);
}
