package dto;

import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idTipoPagamento;
	private String descricao;
	private Boolean ativo;
	private List<PedidoDTO> pedidoDTOs = new ArrayList<PedidoDTO>();
	private EstabelecimentoDTO estabelecimentoDTO;

	public TipoPagamentoDTO() {
	}

	public TipoPagamentoDTO(String descricao, Boolean ativo,
			List<PedidoDTO> pedidoDTOs,
			EstabelecimentoDTO estabelecimentoDTO) {
		this.descricao = descricao;
		this.ativo = ativo;
		this.pedidoDTOs = pedidoDTOs;
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public Long getIdTipoPagamento() {
		return this.idTipoPagamento;
	}

	public void setIdTipoPagamento(Long idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<PedidoDTO> getPedidos() {
		return this.pedidoDTOs;
	}

	public void setPedidos(List<PedidoDTO> pedidoDTOs) {
		this.pedidoDTOs = pedidoDTOs;
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	@Override
	public boolean equals(Object obj) {
		
		System.out.println("Comparação TipoPagamento...");
		
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof TipoPagamentoDTO))
		{
			return false;
		}
		TipoPagamentoDTO outro = (TipoPagamentoDTO) obj;
		if(descricao == null)
		{
			if(outro.getDescricao() != null)
			{
				return false;
			}
		}
		else if(!descricao.equals(outro.getDescricao()))
		{
			return false;
		}
		
		return true;
	}
}
