<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div id="content" style="background-color: #D8E5F6;">
	
	<h1>IMPORTAR DESDE API. SOLO LA CANTIDAD ES CAMPO OBLIGATORIO</h1>
	
		<p>ESCRIBA LA CANTIDAD QUE DESEAS Y SE TE IMPORTARAN TODOS LOS PRODUCTOS QUE DISPONGAN DE ESA CANTIDAD</p>
		<p>Cantidad</p>
		<input id="cantidadId" type="number" /> 
		<p>Fecha hasta. Es decir se importan los productos con fecha menor a la fecha especificada</p>
		<input type="date" id="fechaId" min="2000-01-02">
		<p>Raiz del API. Por defecto es http://localhost:8081, pero puede ser cambiado si hemos lanzado API en otra dirección. Si quieres que sea el por defecto, no rellenes nada en este campo</p>
		<input type="text" id="apiId">
		<button onclick="httpGetFromAPI()" id="Importar">IMPORTAR DESDE API</button>
	<h2><a href="/">Volver</a></h2>
</div>

<script type="text/javascript">

	function httpGetFromAPI()
	{
		
		var cantidad=  document.getElementById("cantidadId").value;
		var fecha=  document.getElementById("fechaId").value;
		var api=  document.getElementById("apiId").value;
		console.log(cantidad);
		console.log(fecha);
		
		if(cantidad  && cantidad > 0) {
			
			
			var theUrl = window.location.origin+"/tiendaVirtual/importarProductosAPi?cantidad="+cantidad;
			
			if (fecha) {
				theUrl = theUrl+ '&fecha='+fecha.toString('yyyyMMddHHmmss');
			}
			if (api) {
				theUrl = theUrl+ '&api='+api;
			}
		    var xmlHttp = new XMLHttpRequest();
		    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
		    xmlHttp.send( null );
		    document.write(xmlHttp.responseText);
		    return xmlHttp.responseText;
		} else {
			alert("Escribe una cantidad mayor que 0");

		}
	}
</script>