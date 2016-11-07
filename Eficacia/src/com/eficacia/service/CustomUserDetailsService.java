package com.eficacia.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.security.auth.login.CredentialException;

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
public class CustomUserDetailsService implements UserDetailsService {
	boolean credentialsNonExpired = true;

	@Autowired
	private com.eficacia.service.UsuarioService usuarioService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String soeid) throws UsernameNotFoundException {
		com.eficacia.model.Usuario domainUser = usuarioService.obtenerUsuario(soeid);
		boolean enabled = true;
		boolean accountNonExpired = true;

		boolean accountNonLocked = true;

		return new CustomUsuario(domainUser.getSoeid(), domainUser.getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, domainUser.getRol().getNombre(),
				getAuthorities(domainUser.getRol().getId(), domainUser.isUsuarioCredencialesNoExpiradas()),
				domainUser.getNombre(), domainUser.getApellidoPaterno());

	}

	public Collection<? extends GrantedAuthority> getAuthorities(Integer role, Boolean credentialsNonExpired) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role, credentialsNonExpired));
		return authList;
	}

	public List<String> getRoles(Integer role, boolean NonExpired) {

		List<String> roles = new ArrayList<String>();
		if (NonExpired) {
			if (role.intValue() == 1) {
				roles.add("ROLE_ADMIN");
			} else if (role.intValue() == 2) {
				roles.add("ROLE_EJECUTIVO");
			}
		}
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
