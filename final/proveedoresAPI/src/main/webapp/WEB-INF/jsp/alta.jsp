<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="background-color: #B7FEAF;">
	<form:form method="post" modelAttribute="proveedorForm" action="/proveedoresAPI/crearAltaProveedor">
		<p>Nombre</p>
		<form:input maxlength="20" path="nombre" type="text" /> 
		<p>Cif</p>
		<form:input maxlength="20" path="cif" type="text" /> 
		<p>Telefono</p>
		<form:input path="telefono" type="number" /> 
		<p>Direccion</p>
		<form:input maxlength="100" path="direccion" type="text" /> 
		<form:input path="id" type="hidden" /> 
		 <p><input type="submit" value="Guardar" /> <input type="reset" value="Resetear" /></p>
	</form:form>
	<h2><a href="/proveedoresAPI">Volver</a></h2>
</div>

