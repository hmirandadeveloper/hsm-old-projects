package dto;


import java.util.ArrayList;
import java.util.List;


public class CardapioProdutoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idcardapioProduto;
	private ProdutoDTO produtoDTO;
	private CardapioDTO cardapioDTO;
	private Boolean ativo;
	private List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<ItemPedidoDTO>();

	public CardapioProdutoDTO() {
	}

	public CardapioProdutoDTO(ProdutoDTO produtoDTO, CardapioDTO cardapioDTO) {
		this.produtoDTO = produtoDTO;
		this.cardapioDTO = cardapioDTO;
	}

	public CardapioProdutoDTO(ProdutoDTO produtoDTO, CardapioDTO cardapioDTO,
			Boolean ativo, List<ItemPedidoDTO> itemPedidoDTOs) {
		this.produtoDTO = produtoDTO;
		this.cardapioDTO = cardapioDTO;
		this.ativo = ativo;
		this.itemPedidoDTOs = itemPedidoDTOs;
	}

	public Long getIdcardapioProduto() {
		return this.idcardapioProduto;
	}

	public void setIdcardapioProduto(Long idcardapioProduto) {
		this.idcardapioProduto = idcardapioProduto;
	}

	public ProdutoDTO getProduto() {
		return this.produtoDTO;
	}

	public void setProduto(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

	public CardapioDTO getCardapio() {
		return this.cardapioDTO;
	}

	public void setCardapio(CardapioDTO cardapioDTO) {
		this.cardapioDTO = cardapioDTO;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<ItemPedidoDTO> getTbItemPedidos() {
		return this.itemPedidoDTOs;
	}

	public void setTbItemPedidos(List<ItemPedidoDTO> itemPedidoDTOs) {
		this.itemPedidoDTOs = itemPedidoDTOs;
	}

	@Override
	public boolean equals(Object obj) {
		
		System.out.println("Comparação Cardapio Produto...");
		
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof CardapioProdutoDTO))
		{
			return false;
		}
		CardapioProdutoDTO outro = (CardapioProdutoDTO) obj;
		if(produtoDTO.getNome() == null)
		{
			if(outro.getProduto().getNome() != null)
			{
				return false;
			}
		}
		else if(!produtoDTO.getNome().equals(outro.getProduto().getNome()))
		{
			return false;
		}
		return true;
	}
}
