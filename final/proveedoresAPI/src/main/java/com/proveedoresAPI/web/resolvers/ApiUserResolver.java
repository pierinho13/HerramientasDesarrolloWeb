package com.proveedoresAPI.web.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.proveedoresAPI.web.infraestructura.comun.ApiUser;

@Component
public class ApiUserResolver implements WebArgumentResolver {


	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(ApiUser.class)) {
			ApiUser appUser = (ApiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (appUser==null) {
			    throw new IllegalAccessException("No se ha podido recuperar el AppUser");
			}
			return appUser;
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}