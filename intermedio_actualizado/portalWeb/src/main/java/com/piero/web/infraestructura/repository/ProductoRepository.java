package com.piero.web.infraestructura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.piero.web.infraestructura.entidades.Producto;

@Transactional(readOnly = true)
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long> {

	Producto findById(Long id);
	
	Producto findByCodigoIgnoreCase(String codigo);
	
	@Query("select p from Producto p")
	List<Producto> getAllProducts();
	
	@Query("select max(p.id) from Producto p")
	Long selectMaxId();
}
