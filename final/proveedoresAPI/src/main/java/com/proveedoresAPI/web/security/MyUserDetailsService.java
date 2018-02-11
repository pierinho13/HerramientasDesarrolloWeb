package com.proveedoresAPI.web.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proveedoresAPI.web.infraestructura.comun.ApiUser;
import com.proveedoresAPI.web.infraestructura.entidades.PersonaApi;
import com.proveedoresAPI.web.infraestructura.entidades.RolApi;
import com.proveedoresAPI.web.infraestructura.entidades.UsuarioApi;
import com.proveedoresAPI.web.infraestructura.repository.PersonaApiRepository;
import com.proveedoresAPI.web.infraestructura.repository.UsuarioApiRepository;


@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private UsuarioApiRepository usuarioApiRepository;
	@Autowired
	private PersonaApiRepository personaApiRepository;
	
	@Autowired(required=false) 
	private HttpServletRequest request;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Logger loginLogger = LoggerFactory.getLogger("logins");
	
	@Override
	public UserDetails loadUserByUsername( String _username ) throws UsernameNotFoundException
	{
		UsuarioApi _usuario = this.usuarioApiRepository.findByUsernameIgnoreCase( _username );
		
		String ip="";
		
		if (request!=null) {
			ip = request.getHeader("X-Real-IP");
			
			if (ip == null) {
				logger.warn("{}: La IP remota (X-Real-IP) viene a null",_username); 
				ip = request.getRemoteAddr();
			}
			logger.trace("{} intenta entrar desde ip {}, remotehost {}, headerHost {}",_username,ip,request.getRemoteHost(),request.getHeader("Host"));
		}
		
		if( _usuario == null )
		{
			loginLogger.error( "Login fallido para {} desde la IP {}. Usuario no encontrado.",_username,ip );
			throw new UsernameNotFoundException("El usuario con login "+_username+" no se puede encontrar.");
		}
		
		Set<RolApi> roles = usuarioApiRepository.getRolesOfUser(_usuario.getId());
		if (roles==null || roles.size()==0) {
			loginLogger.error("Login fallido para {}. El usuario no tiene roles asignados.",_username);
			throw new IllegalStateException("Un Usuario tiene que tener al menos un Rol");
		} 
		
		ApiUser user = new ApiUser( _username, _usuario.getPassword(), roles );

		if (_usuario.getPersonaApi()!=null){
			PersonaApi personaApi = this.personaApiRepository.findById( _usuario.getPersonaApi().getId() );
			user.setPersonaId( personaApi.getId() );
			user.setPersonaNombre( personaApi.getNombre() );
			user.setIsAdmin(roles.iterator().next().getEsAdmin());
			
		}
		
		return user;
	}
}
