package com.piero.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.piero.web.security.CustomAuthFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	CustomAuthFailureHandler  customAuthFailureHandler;
	
	public WebSecurityConfig()
	{
		super();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.userDetailsService(userDetailsService);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login*", "/login/authenticate", "/tiendaVirtual/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll().loginProcessingUrl( "/login/authenticate" )
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
        .and().formLogin().failureHandler(customAuthFailureHandler).failureUrl("/login?error=bad_credentials")
        .and().rememberMe().tokenValiditySeconds(1200);
    } 
}