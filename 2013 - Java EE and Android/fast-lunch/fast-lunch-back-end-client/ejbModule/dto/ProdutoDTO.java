package dto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idProduto;
	private String nome;
	private String descricao;
	private double preco;
	private String imagem;
	private boolean ativo;
	private double precoPromocional;
	private Integer pontuacaoFidelidade;
	private Boolean disponibilidadeFidelidade;
	private List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs = 
			new ArrayList<FidelidadeTrocaDTO>();
	private List<CardapioProdutoDTO> tbCardapioProdutos = 
			new ArrayList<CardapioProdutoDTO>();

	public ProdutoDTO() {
	}

	public ProdutoDTO(String nome, String descricao, double preco, String imagem,
			boolean ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
		this.ativo = ativo;
	}

	public ProdutoDTO(String nome, String descricao, double preco, String imagem,
			boolean ativo, double precoPromocional, Integer pontuacaoFidelidade,
			Boolean disponibilidadeFidelidade,
			List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs,
			List<CardapioProdutoDTO> tbCardapioProdutos) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
		this.ativo = ativo;
		this.precoPromocional = precoPromocional;
		this.pontuacaoFidelidade = pontuacaoFidelidade;
		this.disponibilidadeFidelidade = disponibilidadeFidelidade;
		this.fidelidadeTrocaDTOs = fidelidadeTrocaDTOs;
		this.tbCardapioProdutos = tbCardapioProdutos;
	}

	public Long getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getPrecoPromocional() {
		return this.precoPromocional;
	}

	public void setPrecoPromocional(double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public Integer getPontuacaoFidelidade() {
		return this.pontuacaoFidelidade;
	}

	public void setPontuacaoFidelidade(Integer pontuacaoFidelidade) {
		this.pontuacaoFidelidade = pontuacaoFidelidade;
	}

	public Boolean getDisponibilidadeFidelidade() {
		return this.disponibilidadeFidelidade;
	}

	public void setDisponibilidadeFidelidade(Boolean disponibilidadeFidelidade) {
		this.disponibilidadeFidelidade = disponibilidadeFidelidade;
	}

	public List<FidelidadeTrocaDTO> getFidelidadeTrocas() {
		return this.fidelidadeTrocaDTOs;
	}

	public void setFidelidadeTrocas(List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs) {
		this.fidelidadeTrocaDTOs = fidelidadeTrocaDTOs;
	}

	public List<CardapioProdutoDTO> getCardapioProdutos() {
		return this.tbCardapioProdutos;
	}

	public void setCardapioProdutos(List<CardapioProdutoDTO> tbCardapioProdutos) {
		this.tbCardapioProdutos = tbCardapioProdutos;
	}

}
