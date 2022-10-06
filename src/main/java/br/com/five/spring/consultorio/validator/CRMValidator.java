package br.com.five.spring.consultorio.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;




public class CRMValidator implements ConstraintValidator<ValidarCRM, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String CRM_PATTERN = "^[0-9]{6}\\/[A-Z]{2}$";
	
	@Override
	public void initialize(ValidarCRM constraintAnnotation) {
	}

	@Override
	public boolean isValid(String crm, ConstraintValidatorContext context) {
		return (validarCRM(crm));
	}

	private boolean validarCRM(String crm) {
		pattern = Pattern.compile(CRM_PATTERN);
		matcher = pattern.matcher(crm);
		return matcher.matches();
	}
}
