package com.proveedoresAPI.web.infraestructura.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proveedoresAPI.web.infraestructura.entidades.ProductoProveedor;

@Transactional(readOnly = true)
public interface ProductoProveedorRepository extends PagingAndSortingRepository<ProductoProveedor, Long>,JpaRepository<ProductoProveedor, Long> {

	ProductoProveedor findById(Long id);
	
	ProductoProveedor findByCodigoIgnoreCase(String codigo);
	
	@Query("select p from ProductoProveedor p")
	List<ProductoProveedor> getAllProducts();
	
	@Query("select p from ProductoProveedor p where p.cantidad >= :cantidad")
	List<ProductoProveedor> getProductosPorCantidad(@Param("cantidad") Integer cantidad);
	
	@Query("select p from ProductoProveedor p where p.cantidad >= :cantidad and p.fechaEntrada <= :fecha")
	List<ProductoProveedor> getProductosPorCantidadYFecha(@Param("cantidad") Integer cantidad,@Param("fecha") Date fecha);
	
	
	@Query("select pd from Proveedor p right join p.productos pd where p.id = :proveedorId")
	List<ProductoProveedor> buscaProductosProveedor(@Param("proveedorId") Long proveedorId);
	
	@Query("select max(p.id) from ProductoProveedor p")
	Long selectMaxId();
}
