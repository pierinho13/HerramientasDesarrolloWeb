<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="background-color: #B7FEAF;">
	<form:form method="post" modelAttribute="productoForm" action="/proveedoresAPI/crearAltaProducto">
		<p>Nombre</p>
		<form:input maxlength="20" path="nombre" type="text" /> 
		<p>Codigo</p>
		<form:input maxlength="20" path="codigo" type="text" /> 
		<p>Precio</p>
		<form:input path="precio" type="number" /> 
		<p>Cantidad</p>
		<form:input path="cantidad" type="number" /> 
		<form:input path="id" type="hidden" /> 
		
		<p>Proveedor</p>
		<form:select path="proveedorId">
			<c:forEach items="${proveedores}" var="p">
				<form:option value="${p.id}">${p.nombre}</form:option>
			</c:forEach>
		</form:select>
		
		
		 <p><input type="submit" value="Guardar" /> <input type="reset" value="Resetear" /></p>
	</form:form>
	<h2><a href="/proveedoresAPI">Volver</a></h2>
</div>

