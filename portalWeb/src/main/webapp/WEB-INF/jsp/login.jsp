<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="mobile-web-app-capable" content="yes">
<link href="" rel="stylesheet" type="text/css"/>
<head>
<title>TIENDA VIRTUAL PIERO</title>
</head>
<body style="background-color: #D8E5F6;">
	<div>
	
		<header>
			<h1 >TIENDA VIRTUAL PIERO</h1>
		</header>					
					
		<section>
			
			<form id="login-form"  action="/login/authenticate" method="post">
				<fieldset>
					
					<legend >Accede con tu cuenta</legend>
					
					<div>
						
						<label for="usuario" >usuario</label>
						<input  id="usuario" name="username" autocomplete="on" type="text" />

						<label for="password" >contraseña</label>
						<input  name="password" id="password" type="password" />

						<c:if test="${param.error eq 'bad_credentials'}">
							<div  id="badCredentialsDiv">
								<div>
									<div >
										<p>Usuario o contraseña incorrectos</p>
									</div>		
								</div>
							</div>
						</c:if>
						
						<div>
							<input type="checkbox"  id="remember-me" name="remember-me" checked="checked"/> 
							<label  for="remember-me">No cerrar sesión</label>
						</div>
						
						<div >
							<button type="submit"  id="loginButton"><span>Entrar</span></button>
						</div>
					</div>
				
				</fieldset>	

			</form>
		</section>	
		
	</div>
	

</body>
</html>