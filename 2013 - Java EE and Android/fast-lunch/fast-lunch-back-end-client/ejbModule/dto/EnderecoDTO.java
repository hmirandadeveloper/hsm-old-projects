package dto;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idEndereco;
	private String cep;
	private String logradouro;
	private long numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String pontoReferencia;
	private List<DeliveryDTO> deliveryDTOs = new ArrayList<DeliveryDTO>();
	private List<ClienteDTO> clienteDTOs = new ArrayList<ClienteDTO>();

	public EnderecoDTO() {
	}

	public EnderecoDTO(String cep, String logradouro, long numero,
			String bairro, String cidade, String uf) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public EnderecoDTO(String cep, String logradouro, long numero,
			String complemento, String bairro, String cidade, String uf,
			String pontoReferencia, List<DeliveryDTO> deliveryDTOs,
			List<ClienteDTO> clienteDTOs) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.pontoReferencia = pontoReferencia;
		this.deliveryDTOs = deliveryDTOs;
		this.clienteDTOs = clienteDTOs;
	}

	public Long getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPontoReferencia() {
		return this.pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public List<DeliveryDTO> getDeliveries() {
		return this.deliveryDTOs;
	}

	public void setDeliveries(List<DeliveryDTO> deliveryDTOs) {
		this.deliveryDTOs = deliveryDTOs;
	}

	public List<ClienteDTO> getClientes() {
		return this.clienteDTOs;
	}

	public void setClientes(List<ClienteDTO> clienteDTOs) {
		this.clienteDTOs = clienteDTOs;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof EnderecoDTO)
		{
			EnderecoDTO enderecoDTO = (EnderecoDTO)obj;
			if(enderecoDTO.getIdEndereco().equals(idEndereco))
			{
				return true;
			}
		}
		
		return false;
	}
}
