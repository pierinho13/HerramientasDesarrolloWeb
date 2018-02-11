<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<head>
<title>Login en Tienda Virtual</title>
</head>
<body style="background-color: #B7FEAF;" id="" data-login="PAGINA_DE_LOGIN">
	<div >
		<c:choose>
			<c:when test="${not empty mensaje}">
				<h1>${mensaje}</h1>
			</c:when>
			<c:otherwise>
				<p>ESTA ES LA PAGINA PRINCIPAL DEL API</p>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${esAdmin!= null && esAdmin == true}">
				<h2>Estas son las opciones para el administrador</h2>
				<ol>
				  <li><a href="/proveedoresAPI/altaProveedor">Alta proveedor</a></li>
				  <li><a href="/proveedoresAPI/altaProducto">Alta producto</a></li>
				  <li><a href="/proveedoresAPI/listadoProveedores">Ver</a></li>
				</ol> 
			</c:when>
			<c:otherwise>
				<h2>ESTAS EN PROVEEDOR API DEBES INICIAR SESION CON UN USUARIO ADMIN</h2>
			</c:otherwise>
		</c:choose>
		
		<h2><a href="/proveedoresAPI/logout">Cerrar sesion</a></h2>
		
	</div><!--/container-->
</body>
</html>

<script type="text/javascript">
</script>