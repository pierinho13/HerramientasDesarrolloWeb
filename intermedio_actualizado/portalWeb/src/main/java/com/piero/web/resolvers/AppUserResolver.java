package com.piero.web.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.piero.web.infraestructura.comun.AppUser;

@Component
public class AppUserResolver implements WebArgumentResolver {


	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(AppUser.class)) {
			AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (appUser==null) {
			    throw new IllegalAccessException("No se ha podido recuperar el AppUser");
			}
			return appUser;
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}