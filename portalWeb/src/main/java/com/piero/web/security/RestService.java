package com.piero.web.security;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestService
{
	
	//@Value("${url.api}")// si lo tuviera metido en el propierties q no tengo
	public final static String APIHOST = "http://localhost:8081";
	public final static String API_PREFIX = "proveedoresAPI";
	
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init()
	{
		this.restTemplate = new RestTemplate( this.clientHttpRequestFactory );
	}
	
	public Object get( String url ){
		return this.get( url, null );
	}
	
	public Object get( String url, Map<String, ?> params ){
		return this.get( url, params, Object.class );
	}
	
	public Object get( String url, Map<String, ?> params, Class<?>  clazz ){
		try {
			
			if( params == null ){
				
				params = new HashMap<>();
			}
			
			
			
			logger.info( "GET: Se va a llamar a la url: " + url );
			
			UriComponentsBuilder  builder = UriComponentsBuilder.fromUriString( url );
			
			for( String  key : params.keySet() ){
				
				builder =  builder.queryParam(  key, params.get(  key ) );
			}
			
			
			URI  uri =  builder.build().encode().toUri();
			
			return this.restTemplate.getForObject(  uri,  clazz );
			
		} catch (Exception e) {
			//Si no lo encuentra que no pete sino que devuelva null
			logger.error(e.getMessage());
			return null;
		}
		
	}
	
}

