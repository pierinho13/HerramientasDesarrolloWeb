package com.proveedoresAPI.web.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proveedoresAPI.web.infraestructura.comun.ApiUser;
import com.proveedoresAPI.web.infraestructura.entidades.ProductoProveedor;
import com.proveedoresAPI.web.infraestructura.entidades.Proveedor;
import com.proveedoresAPI.web.infraestructura.repository.ProductoProveedorRepository;
import com.proveedoresAPI.web.infraestructura.repository.ProveedorRepository;
import com.proveedoresAPI.web.utils.MensajeRespuesta;


@Controller
public class ApiController {
	
	@Autowired
	ProductoProveedorRepository productoProveedorRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	
	@RequestMapping(value="altaProveedor", method = RequestMethod.GET)
    public String getAltaProveedor(Model model, ApiUser user) {
		
		
		Proveedor proveedor = new Proveedor();
		
		model.addAttribute("proveedorForm", proveedor);
		
        return "alta";
    }
	
	@Transactional
	@RequestMapping(value="crearAltaProveedor", method = RequestMethod.POST)
	public String postAlta(@ModelAttribute("proveedorForm") Proveedor proveedor,Model model, ApiUser user) {
		
		try {
			if (proveedor.getId() != null) {
				model.addAttribute("mensaje","SE HA MODIFICADO EXITOSAMENTE");
			} else {
				model.addAttribute("mensaje","SE HA DADO DE ALTA EXITOSAMENTE");
			}
			
			if (StringUtils.isNotBlank(proveedor.getCif())) {
				Date fecha = new Date();
				proveedor.setCif(proveedor.getNombre()+ fecha.getTime());
			}
			
			Long maxId = proveedorRepository.selectMaxId();
			proveedor.setId(maxId+50L);
			
			proveedorRepository.save(proveedor);
			
			model.addAttribute("proveedores", (List<Proveedor>) proveedorRepository.findAll());
		} catch (Exception e) {
			model.addAttribute("mensaje","SE HA PRODUCIDO ERROR INTENTELO NUEVAMENTE");
			return "listadoProveedor";
		}
		
		return "listadoProveedor";
	}
	
	@RequestMapping(value="altaProducto", method = RequestMethod.GET)
	public String getAlta(Model model, ApiUser user) {
		
		
		ProductoProveedor productoProveedor = new ProductoProveedor();
		
		model.addAttribute("productoForm", productoProveedor);
		
		List<Proveedor> proveedores = (List<Proveedor>) proveedorRepository.findAll();
		
		model.addAttribute("proveedores", proveedores);
		
		return "altaProducto";
	}
	@Transactional
	@RequestMapping(value="crearAltaProducto", method = RequestMethod.POST)
	public String postAltaProducto(@ModelAttribute("productoForm") ProductoProveedor producto,Model model, ApiUser user) {
		
		try {
			if (producto.getId() != null) {
				model.addAttribute("mensaje","SE HA MODIFICADO EXITOSAMENTE");
			} else {
				model.addAttribute("mensaje","SE HA DADO DE ALTA EXITOSAMENTE");
			}
			
			if (StringUtils.isNotBlank(producto.getCodigo())) {
				Date fecha = new Date();
				producto.setCodigo(producto.getNombre()+ fecha.getTime());
			}
			
			Long maxId = productoProveedorRepository.selectMaxId();
			
			producto.setId(maxId+1);
			
			Long proveedorId = producto.getProveedorId();
			producto = productoProveedorRepository.saveAndFlush(producto);
			if (proveedorId != null) {
				Proveedor proveedor = proveedorRepository.findById(proveedorId);
				Set<ProductoProveedor> productos = proveedor.getProductos();
				productos.add(producto);
				
				proveedorRepository.save(proveedor);
			}
			
			model.addAttribute("productos", (List<ProductoProveedor>) productoProveedorRepository.getAllProducts());
		} catch (Exception e) {
			model.addAttribute("mensaje","SE HA PRODUCIDO ERROR INTENTELO NUEVAMENTE");
			return "listadoProducto";
		}
		
		return "listadoProducto";
	}
	
	
	@RequestMapping(value="listadoProveedores", method = RequestMethod.GET)
	public String getListadoProveedores(Model model, ApiUser user) {
		
		
		List<Proveedor> proveedores = (List<Proveedor>) proveedorRepository.findAll();
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		model.addAttribute("proveedores", proveedores);
		
		return "listadoProveedor";
	}
	
	@RequestMapping(value="proveedor/{proveedorId}/productos", method = RequestMethod.GET)
	public String getListadoProductos(@PathVariable Long proveedorId, Model model, ApiUser user) {
		
		
		List<ProductoProveedor> productos = (List<ProductoProveedor>) productoProveedorRepository.buscaProductosProveedor(proveedorId);
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		model.addAttribute("productos", productos);
		
		return "listadoProducto";
	}
	@Transactional
	@RequestMapping(value="producto/porCantidadYFecha", method = RequestMethod.GET)
	public @ResponseBody MensajeRespuesta getListadoProductosCantidad(@RequestParam Integer cantidad,@RequestParam(required=false) String  fecha,  Model model) {
		
		Date date=  null;
		if(fecha!=null) {
			try {
				DateFormat format = new SimpleDateFormat("yyyyMMdd");
				date=  format.parse(fecha);
				
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
		MensajeRespuesta respuesta =  new MensajeRespuesta();
		
		try {
			
			List<ProductoProveedor> datos = new ArrayList<>();
			if (date == null) {
				
				datos = (List<ProductoProveedor>) productoProveedorRepository.getProductosPorCantidad(cantidad);
				
			} else {
				
				datos = (List<ProductoProveedor>) productoProveedorRepository.getProductosPorCantidadYFecha(cantidad, date);
			}
			
			List<ProductoProveedor> datosEnviar = new ArrayList<>();
			
			if(CollectionUtils.isNotEmpty(datos)) {
				for(ProductoProveedor productoProveedor : datos) {
					
					ProductoProveedor enviar = new ProductoProveedor();
					
					BeanUtils.copyProperties(enviar, productoProveedor);
					datosEnviar.add(enviar);
					if(productoProveedor.getCantidad() != null) {
						Integer nuevaCantidad=  productoProveedor.getCantidad() - cantidad;
						productoProveedor.setCantidad(nuevaCantidad);
						productoProveedorRepository.save(productoProveedor);
					}
					
				}
			}
			respuesta.setEstado("OK");
			respuesta.setProductos(datosEnviar);
		} catch (Exception e) {
			respuesta.setEstado("NOK");
		}
		
		
		return respuesta;
	}
	
}