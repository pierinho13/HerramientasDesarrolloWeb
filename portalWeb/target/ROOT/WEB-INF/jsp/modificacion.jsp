<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body style="background-color: #D8E5F6;" id="" data-login="PAGINA_DE_LOGIN">

	<div >
		<table>
		  <tr>
		    <th>Nombre</th>
		    <th>Editar</th>
		  </tr>
		  <c:forEach items ="${productos}" var="producto">
			  <tr>
			    <td>${producto.nombre}</td>
			    <td><a href="/tiendaVirtual/editarProducto/${producto.id}">Editar</a><td>
			  </tr>
		  </c:forEach>
		</table>
	</div><!--/container-->
	
	<h2><a href="/">Volver</a></h2>
</body>

</html>

<script type="text/javascript">
history.pushState(null, "", "/tiendaVirtual/listado");
</script>