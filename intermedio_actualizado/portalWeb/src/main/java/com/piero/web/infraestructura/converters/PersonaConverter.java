package com.piero.web.infraestructura.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.piero.web.infraestructura.entidades.Persona;
import com.piero.web.infraestructura.repository.PersonaRepository;

@Component
public class PersonaConverter implements Converter<String, Persona> {

	@Autowired
	private PersonaRepository repository;

	public Persona convert(String source) {
		Long id = null;
		try {
			id = new Long (source.replace( ".", "" ));
		} catch (Exception e) {
			throw new IllegalArgumentException("Se pidió una persona con un ID=(" + source + ") que no es numérico.");
		}
//		AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (appUser == null) {
//			throw new IllegalArgumentException("No se ha podido recuperar el AppUser");
//		}
		Persona entidad = repository.findOne(id);
		if (entidad==null) {
			throw new IllegalArgumentException("No se encuentra la persona de ID " + source);
		}
		return entidad;
	}

	public void setRepository(PersonaRepository repository) {
		this.repository = repository;
	}

}
