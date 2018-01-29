<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<head>
<title>Login en Tienda Virtual</title>
</head>
<body style="background-color: #D8E5F6;" id="" data-login="PAGINA_DE_LOGIN">
	<div >
		<p>ESTA ES LA PAGINA DE QUE TE HAS LOGUEADO CORRECTAMENTE</p>
		
		<c:choose>
		
			<c:when test="${esAdmin!= null && esAdmin == true}">
				<h2>Estas son las opciones para el administrador</h2>
				<ol>
				  <li><a href="/tiendaVirtual/alta">Alta de productos</a></li>
				  <li><a href="/tiendaVirtual/baja">Baja de productos</a></li>
				  <li><a href="/tiendaVirtual/modificacion">Modificación de productos</a></li>
				  <li><a href="/tiendaVirtual/listado">Listado de productos</a></li>
				  <li><a href="/tiendaVirtual/reposicion">Reposición de productos</a></li>
				</ol> 
			</c:when>
			<c:otherwise>
				<h2>Estas son las opciones para el cliente</h2>
				<ol>
				  <li><a href="/tiendaVirtual/compra">Compra de productos</a></li>
				</ol> 
			</c:otherwise>
		</c:choose>
		
		<h2><a href="/logout">Cerrar sesion</a></h2>
		
	</div><!--/container-->
</body>
</html>

<script type="text/javascript">
</script>