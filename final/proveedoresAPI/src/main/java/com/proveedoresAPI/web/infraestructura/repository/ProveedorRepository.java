package com.proveedoresAPI.web.infraestructura.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proveedoresAPI.web.infraestructura.entidades.ProductoProveedor;
import com.proveedoresAPI.web.infraestructura.entidades.Proveedor;

@Transactional(readOnly = true)
public interface ProveedorRepository extends PagingAndSortingRepository<Proveedor, Long> {

	Proveedor findById(Long id);
	
	
	@Query("select pr from Proveedor p join p.productos pr where p.id=:proveedorId")
	Set<ProductoProveedor> getProductsProveedor(@Param("proveedorId")Long id);
	
	@Query("select max(p.id) from Proveedor p")
	Long selectMaxId();
	
}
