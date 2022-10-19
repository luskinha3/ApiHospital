package br.com.five.spring.consultorio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.five.spring.consultorio.enums.RoleName;
import br.com.five.spring.consultorio.modelo.PapelModel;

public interface PapelRepository extends JpaRepository<PapelModel, UUID> {
	
	PapelModel findByRolename (RoleName rolename);
}
