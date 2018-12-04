package dto;

import java.util.Date;

import constantes.EStatusPedido;

public class StatusPedidoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idStatusPedido;
	private PedidoDTO pedidoDTO;
	private EStatusPedido statusPedido;
	private Date dataHora;

	public StatusPedidoDTO() {
	}

	public StatusPedidoDTO(PedidoDTO pedidoDTO, EStatusPedido statusPedido, Date dataHora) {
		this.pedidoDTO = pedidoDTO;
		this.statusPedido = statusPedido;
		this.dataHora = dataHora;
	}

	public Long getIdStatusPedido() {
		return this.idStatusPedido;
	}

	public void setIdStatusPedido(Long idStatusPedido) {
		this.idStatusPedido = idStatusPedido;
	}

	public PedidoDTO getPedido() {
		return this.pedidoDTO;
	}

	public void setPedido(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public EStatusPedido getStatusPedido() {
		return this.statusPedido;
	}

	public void setStatusPedido(EStatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

}
