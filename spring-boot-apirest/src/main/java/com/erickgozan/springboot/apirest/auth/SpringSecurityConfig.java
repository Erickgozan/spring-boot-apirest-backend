package com.erickgozan.springboot.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Clase de configuracion de spring security
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//Interfaz que contiene el usuario con sus datos de sesión(username,password,authorities)
	@Autowired
	@Qualifier("usuarioService")
	private UserDetailsService usuarioService;
	
	//Metodo para encriptar las contraseñas
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	}
	
	//Metodo que configura el usuario registrado y encripta su contraseña
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {		
		return super.authenticationManager();
	}
	
	//Metodo que deshabilita el Cross-site request forgery
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
}
