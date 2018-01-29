package com.piero.web.security;

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

import com.piero.web.infraestructura.comun.AppUser;
import com.piero.web.infraestructura.entidades.Persona;
import com.piero.web.infraestructura.entidades.Rol;
import com.piero.web.infraestructura.entidades.Usuario;
import com.piero.web.infraestructura.repository.PersonaRepository;
import com.piero.web.infraestructura.repository.UsuarioRepository;


@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired(required=false) 
	private HttpServletRequest request;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Logger loginLogger = LoggerFactory.getLogger("logins");
	
	@Override
	public UserDetails loadUserByUsername( String _username ) throws UsernameNotFoundException
	{
		Usuario _usuario = this.usuarioRepository.findByUsernameIgnoreCase( _username );
		
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
		
		Set<Rol> roles = usuarioRepository.getRolesOfUser(_usuario.getId());
		if (roles==null || roles.size()==0) {
			loginLogger.error("Login fallido para {}. El usuario no tiene roles asignados.",_username);
			throw new IllegalStateException("Un Usuario tiene que tener al menos un Rol");
		} 
		
		AppUser user = new AppUser( _username, _usuario.getPassword(), roles );

		if (_usuario.getPersona()!=null){
			Persona persona = this.personaRepository.findById( _usuario.getPersona().getId() );
			user.setPersonaId( persona.getId() );
			user.setPersonaNombre( persona.getNombre() );
			user.setIsAdmin(roles.iterator().next().getEsAdmin());
			
		}
		
		return user;
	}
}
