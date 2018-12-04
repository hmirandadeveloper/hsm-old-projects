package br.edu.unibratec.pgm.jogomemoria.basicas;

public class Partida {
	private long id;
	private String usuarioNome;
	private String pontuacao;
	private String data;
	
	public Partida()
	{
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsuarioNome() {
		return usuarioNome;
	}
	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}
	public String getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(String pontuacao) {
		this.pontuacao = pontuacao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
