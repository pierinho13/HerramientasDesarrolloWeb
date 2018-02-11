package com.proveedoresAPI.web.infraestructura.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.proveedoresAPI.web.infraestructura.entidades.PersonaApi;

@Transactional(readOnly = true)
public interface PersonaApiRepository extends PagingAndSortingRepository<PersonaApi, Long> {

	PersonaApi findById(Long id);
}
