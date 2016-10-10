package com.eficacia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eficacia.model.Usuario;

@Component
public class UsuarioValidator implements Validator{

	public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        Usuario usuario = (Usuario) obj;
             
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "soeid", "NotEmpty.usuario.soeid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidoPaterno", "NotEmpty.usuario.apellidoPaterno");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "NotEmpty.usuario.telefono");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.usuario.password");
        
        if(!usuario.getPassword().equals(usuario.getPasswordConfirmation())){
        	 errors.rejectValue("passwordConfirmation", "notmatch.usuario.password");
        }
        
    }
	
}
