package com.eficacia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eficacia.model.Usuario;

@Component
public class UsuarioUpdateValidator implements Validator{

	public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

	public void validate(Object obj, Errors errors) {
        Usuario usuario = (Usuario) obj;
             
        String patronContraseñaSegura =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,100})";
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
        }else if(usuario.getPassword().length() != 8){
        	errors.rejectValue("password", "NotLenght.usuario.password");
        }else if(!tieneEspaciosEnBlanco(usuario.getPassword())){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }else if(tieneCaracteresConsecutivos(usuario.getPassword()) >= 3){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }else if(usuario.getPassword().contains(usuario.getNombre()) || usuario.getPassword().contains(usuario.getApellidoPaterno())){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }else if(usuario.getPassword().contains("Banamex") || usuario.getPassword().contains("Citi") || usuario.getPassword().contains("CitiBanamex")){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }else if(usuario.getPassword().charAt(0) == '0'){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }else if(!usuario.getPassword().matches(patronContraseñaSegura)){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }
        
        if(!usuario.getPassword().equals(usuario.getPasswordConfirmation())){
        	 errors.rejectValue("passwordConfirmation", "notmatch.usuario.password");
        }
        if(usuario.getRol() == null){
            errors.rejectValue("rol", "NotEmpty.usuario.rol");
       }
        
    }
	
	public boolean tieneEspaciosEnBlanco(String password){
    	boolean validacion = true;
    	int tamanoCadena = password.length();
        for (int i = 0; i < tamanoCadena; i++) {
          if (Character.isWhitespace(password.charAt(i))) {
            validacion = false;
          }
        }
    	return validacion;
    }
    
    public int tieneCaracteresConsecutivos(String password) {
        int charBlock = 0;
        int holder = 1;
        if(password.length() == 0){ 
           charBlock = 0;
        } else if(password.length() == 1){
           charBlock = 1;
        } else {
            for(int i=0; i < password.length()-1; i++){   
                if((password.length() == 2) && (password.charAt(i) != password.charAt(i+1))){ 
                    charBlock =1; 
                }   
                else if((password.length() == 3) && (password.charAt(i) != password.charAt(i+1))){
                   charBlock = 1; 
                } 
                else if (password.charAt(i) == password.charAt(i+1)){
                   holder = holder + 1; 
                     if(holder > charBlock){
                       charBlock = holder;
                       }
               } else holder = 1; 
            }
        }
        return charBlock;
      }
	
}
