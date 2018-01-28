package com.piero.web.infraestructura.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.piero.web.infraestructura.entidades.Rol;
import com.piero.web.infraestructura.entidades.Usuario;


@Transactional(readOnly = true)
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByUsernameIgnoreCase(String username);
	
	@Query("select r from Usuario u join u.roles r where u.id=:userId")
	Set<Rol> getRolesOfUser(@Param("userId")Long id);
	
}
