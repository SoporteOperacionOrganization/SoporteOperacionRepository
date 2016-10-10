package com.eficacia.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eficacia.custommodel.CustomUsuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private com.eficacia.service.UsuarioService usuarioService;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String soeid) throws UsernameNotFoundException {
		System.out.println("ID DE ROL: " + soeid);
		com.eficacia.model.Usuario domainUser = usuarioService.obtenerUsuario(soeid);
		System.out.println("ID DE ROL: " + domainUser.getRol().getId());
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new CustomUsuario(
        	domainUser.getSoeid(),
        	domainUser.getPassword(),
        	enabled, 
            accountNonExpired, 
            credentialsNonExpired, 
            accountNonLocked,
            getAuthorities(domainUser.getRol().getId()),
            domainUser.getNombre(),
            domainUser.getApellidoPaterno()
        );
        
        /*return new User(
                domainUser.getSoeid(), 
                domainUser.getPassword(), 
                enabled, 
                accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked,
                getAuthorities(domainUser.getRol().getId())
        );*/
	}
	
	

	
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
	
	public List<String> getRoles(Integer role) {
		 
        List<String> roles = new ArrayList<String>();
 
        if (role.intValue() == 1) {
            roles.add("ROLE_ADMIN");
        } else if (role.intValue() == 2) {
            roles.add("ROLE_EJECUTIVO");
        }/*else if(role.intValue() == 3){
        	roles.add("ROLE_DBA");
        }else if(role.intValue() == 4){
        	roles.add("ROLE_TESTER");
        }*/
        return roles;
    }
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
	
	
}
