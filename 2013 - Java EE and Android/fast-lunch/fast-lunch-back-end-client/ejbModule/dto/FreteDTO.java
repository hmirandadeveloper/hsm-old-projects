package dto;


import java.util.ArrayList;
import java.util.List;

public class FreteDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idFrete;
	private String bairro;
	private double valor;
	private List<DeliveryDTO> deliveryDTOs = new ArrayList<DeliveryDTO>();
	private EstabelecimentoDTO estabelecimentoDTO;
	private boolean ativo;

	public FreteDTO() {
	}

	public FreteDTO(String bairro, double valor) {
		this.bairro = bairro;
		this.valor = valor;
	}

	public FreteDTO(String bairro, double valor, List<DeliveryDTO> deliveryDTOs,
			EstabelecimentoDTO estabelecimentoDTO) {
		this.bairro = bairro;
		this.valor = valor;
		this.deliveryDTOs = deliveryDTOs;
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public Long getIdFrete() {
		return this.idFrete;
	}

	public void setIdFrete(Long idFrete) {
		this.idFrete = idFrete;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public List<DeliveryDTO> getDeliveries() {
		return this.deliveryDTOs;
	}

	public void setDeliveries(List<DeliveryDTO> deliveryDTOs) {
		this.deliveryDTOs = deliveryDTOs;
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
