package br.com.five.spring.consultorio.config.validacao;

public class ErroHandlerDTO {
	
	private String campo;
	private String erro;
		
	public ErroHandlerDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	public String getErro() {
		return erro;
	}
}
