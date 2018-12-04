package dto;


public class ItemPedidoDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idItemPedido;
	private CardapioProdutoDTO cardapioProdutoDTO;
	private PedidoDTO pedidoDTO;
	private int quantidade;

	public ItemPedidoDTO() {
	}

	public ItemPedidoDTO(Long idItemPedido, CardapioProdutoDTO cardapioProdutoDTO,
			PedidoDTO pedidoDTO, int quantidade) {
		this.idItemPedido = idItemPedido;
		this.cardapioProdutoDTO = cardapioProdutoDTO;
		this.pedidoDTO = pedidoDTO;
		this.quantidade = quantidade;
	}


	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public CardapioProdutoDTO getCardapioProduto() {
		return this.cardapioProdutoDTO;
	}

	public void setCardapioProduto(CardapioProdutoDTO cardapioProdutoDTO) {
		this.cardapioProdutoDTO = cardapioProdutoDTO;
	}

	public PedidoDTO getPedido() {
		return this.pedidoDTO;
	}

	public void setPedido(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("Comparação Cargo...");
		
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof ItemPedidoDTO))
		{
			return false;
		}
		ItemPedidoDTO outro = (ItemPedidoDTO) obj;
		if(cardapioProdutoDTO.getIdcardapioProduto() == null)
		{
			if(cardapioProdutoDTO.getIdcardapioProduto() != null)
			{
				return false;
			}
		}
		else if(!cardapioProdutoDTO.getIdcardapioProduto().equals(outro.cardapioProdutoDTO.getIdcardapioProduto()))
		{
			return false;
		}
		
		return true;
	}
}
