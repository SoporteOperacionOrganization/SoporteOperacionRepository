package com.eficacia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eficacia.model.Agenda;
import com.eficacia.model.Usuario;

@Component
public class AgendaValidator implements Validator{

	String patronSoloNumeros = "\\d+";
	
	public boolean supports(Class<?> clazz) {
        return Agenda.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        Agenda agenda = (Agenda) obj;
             
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaTransaccion", "NotEmpty.agenda.fechaTransaccion");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaCierre", "NotEmpty.agenda.fechaCierre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "razonSocial", "NotEmpty.agenda.razonSocial");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreRepresentante", "NotEmpty.agenda.nombreRepresentante");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ejecutivo", "NotEmpty.agenda.ejecutivo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sede", "NotEmpty.agenda.sede");
        
        if(agenda.getNumeroCliente().length() != 5){
        	errors.rejectValue("numeroCliente", "NotLenght.agenda.numeroCliente");
        }else if(!agenda.getNumeroCliente().matches(patronSoloNumeros)){
        	errors.rejectValue("numeroCliente", "NotNumber.agenda.notnumber");
        }
        
        if(agenda.getNumeroTelefono().equals("")){
        	errors.rejectValue("numeroTelefono", "NotEmpty.agenda.numeroTelefono");
        }else if(!agenda.getNumeroTelefono().matches(patronSoloNumeros)){
        	errors.rejectValue("numeroTelefono", "NotNumber.agenda.notnumber");
        }
        
        if(agenda.getSoeid().length() != 7){
        	errors.rejectValue("soeid", "NotLenght.agenda.soeid");
        }
        
        
        
    }
	
}
