package com.eficacia.custommodel;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUsuario extends org.springframework.security.core.userdetails.User {
	
    private String nombre;
    private String apellidoPaterno;
	
    public CustomUsuario(String username, 
    					 String password, 
    					 boolean enabled, 
    					 boolean accountNonExpired,
    					 boolean credentialsNonExpired, 
    					 boolean accountNonLocked,
    					 Collection<? extends GrantedAuthority> authorities, 
    					 String nombre, 
    					 String apellidoPaterno) {
    				super(username, password, enabled, accountNonExpired, credentialsNonExpired,
    				accountNonLocked, authorities);

		this.setNombre(nombre);
		this.setApellidoPaterno(apellidoPaterno);
		}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	
    
    
    
}
