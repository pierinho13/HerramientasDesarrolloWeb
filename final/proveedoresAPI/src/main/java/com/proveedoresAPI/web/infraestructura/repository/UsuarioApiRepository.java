package com.proveedoresAPI.web.infraestructura.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proveedoresAPI.web.infraestructura.entidades.RolApi;
import com.proveedoresAPI.web.infraestructura.entidades.UsuarioApi;


@Transactional(readOnly = true)
public interface UsuarioApiRepository extends PagingAndSortingRepository<UsuarioApi, Long> {

	UsuarioApi findByUsernameIgnoreCase(String username);
	
	@Query("select r from UsuarioApi u join u.roles r where u.id=:userId")
	Set<RolApi> getRolesOfUser(@Param("userId")Long id);
	
}
