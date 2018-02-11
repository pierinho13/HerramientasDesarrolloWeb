package com.proveedoresAPI.web.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InitializerWeb extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	    registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class,PersistenceContext.class, WebSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected javax.servlet.Filter[] getServletFilters() {

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);

		return new Filter[] { encodingFilter, new MultipartFilter() };
	}
}