package dto;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class CardapioDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idCardapio;
	private String nome;
	private TipoCardapioDTO tipoCardapioDTO;
	private Date validade;
	private List<CardapioProdutoDTO> cardapioProdutoDTOs = new ArrayList<CardapioProdutoDTO>();

	public CardapioDTO() {
	}

	public CardapioDTO(String nome,
			TipoCardapioDTO tipoCardapioDTO) {
		this.nome = nome;
		this.tipoCardapioDTO = tipoCardapioDTO;
	}

	public CardapioDTO(String nome,
			TipoCardapioDTO tipoCardapioDTO, Date validade,
			List<CardapioProdutoDTO> cardapioProdutos) {
		this.nome = nome;
		this.tipoCardapioDTO = tipoCardapioDTO;
		this.validade = validade;
		this.cardapioProdutoDTOs = cardapioProdutos;
	}


	public Long getIdCardapio() {
		return this.idCardapio;
	}

	public void setIdCardapio(Long idCardapio) {
		this.idCardapio = idCardapio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoCardapioDTO getTipoCardapio() {
		return this.tipoCardapioDTO;
	}

	public void setTipoCardapio(TipoCardapioDTO tipoCardapioDTO) {
		this.tipoCardapioDTO = tipoCardapioDTO;
	}

	public Date getValidade() {
		return this.validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public List<CardapioProdutoDTO> getCardapioProdutos() {
		return this.cardapioProdutoDTOs;
	}

	public void setCardapioProdutos(List<CardapioProdutoDTO> cardapioProdutos) {
		this.cardapioProdutoDTOs = cardapioProdutos;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		System.out.println("Comparação Cardapio...");
		
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof CardapioDTO))
		{
			return false;
		}
		CardapioDTO outro = (CardapioDTO) obj;
		if(nome == null)
		{
			if(outro.getNome() != null)
			{
				return false;
			}
		}
		else if(!nome.equals(outro.getNome()))
		{
			return false;
		}
		
		return true;
	}
}
