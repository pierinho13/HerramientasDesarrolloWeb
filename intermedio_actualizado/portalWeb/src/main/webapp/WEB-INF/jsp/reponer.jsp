<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="background-color: #D8E5F6;">
	<form:form method="post" modelAttribute="productoForm" action="/tiendaVirtual/postReponer">
	
		<p>Escoge uno y escribe la cantidad</p>
		<form:select path="id">
			<c:forEach items="${productos}" var="producto">
				<form:option value="${producto.id}">${producto.nombre}</form:option>
			</c:forEach>
		</form:select>
		<p>Cantidad</p>
		<form:input path="cantidad" type="number" /> 
		<input type="submit">
	</form:form>
	<h2><a href="/">Volver</a></h2>
</div>

