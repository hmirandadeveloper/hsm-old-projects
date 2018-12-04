package basicas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbranking")
public class Ranking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nome;
	private String pontuacao;
	private String data;
	
	
	public Ranking()
	{
		
	}
	
	public Ranking(long id, String nome, String pontuacao, String data) {
		super();
		this.id = id;
		this.nome = nome;
		this.pontuacao = pontuacao;
		this.data = data;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
