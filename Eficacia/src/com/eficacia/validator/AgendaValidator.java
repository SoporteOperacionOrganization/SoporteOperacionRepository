package com.eficacia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eficacia.model.Agenda;
import com.eficacia.model.Usuario;

@Component
public class AgendaValidator implements Validator{

	public boolean supports(Class<?> clazz) {
        return Agenda.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        Agenda agenda = (Agenda) obj;
             
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigoTransaccion", "NotEmpty.agenda.codigoTransaccion");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaTransaccion", "NotEmpty.agenda.fechaTransaccion");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaCierre", "NotEmpty.agenda.fechaCierre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroCliente", "NotEmpty.agenda.numeroCliente");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "razonSocial", "NotEmpty.agenda.razonSocial");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreRepresentante", "NotEmpty.agenda.nombreRepresentante");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTelefono", "NotEmpty.agenda.numeroTelefono");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "soeid", "NotEmpty.agenda.soeid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ejecutivo", "NotEmpty.agenda.ejecutivo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sede", "NotEmpty.agenda.sede");
        
    }
	
}
