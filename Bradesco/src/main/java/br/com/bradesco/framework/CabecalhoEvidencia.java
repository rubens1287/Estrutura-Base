package br.com.bradesco.framework;
/**
 * Classe de configuração do cabeçalho da evidencia
 * 
 * @author Rubens Lobo
 *
 */
public class CabecalhoEvidencia {
	
	private String nomeEmpresa;
	private String nomeProjeto;
	private String nomeSistema;
	private String nomeCenario;
	private String nomeCasoTeste;
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public String getNomeSistema() {
		return nomeSistema;
	}
	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
	}
	public String getNomeCenario() {
		return nomeCenario;
	}
	public void setNomeCenario(String nomeCenario) {
		this.nomeCenario = nomeCenario;
	}
	public String getNomeCasoTeste() {
		return nomeCasoTeste;
	}
	public void setNomeCasoTeste(String nomeCasoTeste) {
		this.nomeCasoTeste = nomeCasoTeste;
	}
	
}
