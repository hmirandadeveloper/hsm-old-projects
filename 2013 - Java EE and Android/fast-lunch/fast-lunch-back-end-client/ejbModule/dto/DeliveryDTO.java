package dto;


public class DeliveryDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idDelivery;
	private PedidoDTO pedidoDTO;
	private FreteDTO freteDTO;
	private EnderecoDTO enderecoDTO;


	public DeliveryDTO() {
	}

	public DeliveryDTO(PedidoDTO pedidoDTO, FreteDTO freteDTO, EnderecoDTO enderecoDTO) {
		this.pedidoDTO = pedidoDTO;
		this.freteDTO = freteDTO;
		this.enderecoDTO = enderecoDTO;
	}


	public Long getIdDelivery() {
		return this.idDelivery;
	}

	public void setIdDelivery(Long idDelivery) {
		this.idDelivery = idDelivery;
	}

	public PedidoDTO getPedido() {
		return this.pedidoDTO;
	}

	public void setPedido(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public FreteDTO getFrete() {
		return this.freteDTO;
	}

	public void setFrete(FreteDTO freteDTO) {
		this.freteDTO = freteDTO;
	}

	public EnderecoDTO getEndereco() {
		return this.enderecoDTO;
	}

	public void setEndereco(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

}
