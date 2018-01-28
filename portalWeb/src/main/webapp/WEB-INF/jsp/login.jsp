<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="mobile-web-app-capable" content="yes">
<link href="styles.css" rel="stylesheet" type="text/css"/>
<head>
<title>Login en Tienda Virtual</title>
</head>
<body id="" data-login="PAGINA_DE_LOGIN">
	<div class="container">
	
		<c:if test="${not empty errorMessage and errorMessage ne ''}">
			<div class="row">
				<div class="col-xs-12">
					<div class="alert alert-danger">
						<p>${errorMessage}</p>
					</div>		
				</div>
			</div>
		</c:if>
		<c:if test="${not empty successMessage and successMessage ne ''}">
			<div class="row">
				<div class="col-xs-12">
					<div class="alert alert-success">
						<p>${successMessage}</p>
					</div>		
				</div>
			</div>
		</c:if>
		<header class="">
			<h1 class="">Tienda Virtual Piero</h1>
		</header>					
					
		<section class="login-box">
			
			<form id="login-form" class="" action="/login/authenticate" method="post">
				<fieldset>
					
					<legend class="">Accede con tu cuenta</legend>
					
					<div class="">
						
						<label for="usuario" class="">usuario</label>
						<input class="login-form__input" name="username" type="text" />

						<label for="password" class="">contraseña</label>
						<input class="login-form__input" name="password" id="password" type="password" />

						<div class="clearfix"></div>
						<c:if test="${param.error eq 'bad_credentials'}">
							<div class="row" id="badCredentialsDiv">
								<div class="col-xs-12">
									<div class="alert alert-danger">
										<p>Usuario o contraseña incorrectos</p>
									</div>		
								</div>
							</div>
						</c:if>
						<c:if test="${param.error eq 'not_allowed_schedule'}">
							<div class="row" id="badCredentialsDiv">
								<div class="col-xs-12">
									<div class="alert alert-danger">
										<p>No puedes usar la aplicación fuera de tu horario permitido</p>
									</div>		
								</div>
							</div>
						</c:if>
						
						<c:if test="${not empty param.sigeouser and param.sigeouser eq 'true'}">
							<input type="hidden" name="sigeouser" value="true" />
						</c:if>
						
						<div>
							<input type="checkbox" class="" id="remember-me" name="remember-me" checked="checked"/> 
							<label class="" for="remember-me">No cerrar sesión</label>
						</div>
						
						
						<div class="">
							<button type="submit" class="" id="loginButton"><span class=" ">Entrar</span></button>
						</div>
					</div>
				
				</fieldset>	

			</form>
		</section>	
		
	</div><!--/container-->
	

</body>
</html>