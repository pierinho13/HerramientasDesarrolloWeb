package com.piero.web.infraestructura.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.piero.web.infraestructura.entidades.Persona;

@Transactional(readOnly = true)
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {

	Persona findById(Long id);
}
