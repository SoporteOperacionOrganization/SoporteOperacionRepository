package com.eficacia.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class EficaciaWebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	//private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		// Usuarios en memoria.
		
        auth.inMemoryAuthentication().withUser("user").password("12345").roles("ADMIN, EJECUTIVO");
        
     // Usuarios en base de datos
        auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		
		http.csrf().disable();
		// La pagina no requiere login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
     // /userInfo requeire rol de admin, moderaor, tester
        // Si no se ha realizado login, se redirige a /login
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')");
        
     // Para rol de admin.
        http.authorizeRequests().antMatchers("/admin","/listarUsuarios","/listarAgendas").access("hasRole('ROLE_ADMIN')");
        
     // Cuando usuario se ha logueado como XX.
        // Pero acceso requiere rol YY,
        // AccessDeniedException sera lanzada.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        
     // Config para formulario de login
        http.authorizeRequests().and().formLogin()//
                // Submit URL de pagina de login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/inicio")//
                .failureUrl("/login?error=true")//
                .usernameParameter("soeid")//
                .passwordParameter("contrasena")
                // Config para pagina de logout
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        
        //Timeout de sesion, se redirige a la pagina deseada
        http.sessionManagement()
        .invalidSessionUrl("/login");
        
        //http.sessionManagement().maximumSessions(2);
       
		
	}
	
	
	
}
