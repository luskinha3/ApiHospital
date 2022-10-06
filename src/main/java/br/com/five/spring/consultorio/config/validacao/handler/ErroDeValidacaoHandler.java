package br.com.five.spring.consultorio.config.validacao.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroHandlerDTO> handle(MethodArgumentNotValidException exception){
		List<ErroHandlerDTO> listErros = new ArrayList<ErroHandlerDTO>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String mensagemDeErro = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroHandlerDTO erro = new ErroHandlerDTO(e.getField(), mensagemDeErro);
			listErros.add(erro);
		});
		
		return listErros;
	}
}