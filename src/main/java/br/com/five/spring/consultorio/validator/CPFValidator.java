package br.com.five.spring.consultorio.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;




public class CPFValidator implements ConstraintValidator<ValidarCPF, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String CPF_PATTERN = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";

	@Override
	public void initialize(ValidarCPF constraintAnnotation) {
	}

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		return (validarCPF(cpf));
	}

	private boolean validarCPF(String cpf) {
		pattern = Pattern.compile(CPF_PATTERN);
		matcher = pattern.matcher(cpf);
		return matcher.matches();
	}
}
