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
             
        String patronContraseñaSegura =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%./_-]).{6,100})";
        String patronSoloNumeros = "\\d+";
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidoPaterno", "NotEmpty.usuario.apellidoPaterno");
        
        if(usuario.getSoeid().length() != 7){
        	errors.rejectValue("soeid", "NotLenght.usuario.soeid");
        }
        
        if(usuario.getTelefono().equals("")){
        	errors.rejectValue("telefono", "NotEmpty.usuario.telefono");
        }else if(!usuario.getTelefono().matches(patronSoloNumeros)){
        	errors.rejectValue("telefono", "NotNumber.usuario.notnumber");
        }
        
        if(usuario.getPassword().equals("")){
        	errors.rejectValue("password", "NotEmpty.usuario.password");
        }else if(!usuario.getPassword().matches(patronContraseñaSegura)){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }
        
        if(!usuario.getPassword().equals(usuario.getPasswordConfirmation())){
        	 errors.rejectValue("passwordConfirmation", "notmatch.usuario.password");
        }
        
    }
	
}
