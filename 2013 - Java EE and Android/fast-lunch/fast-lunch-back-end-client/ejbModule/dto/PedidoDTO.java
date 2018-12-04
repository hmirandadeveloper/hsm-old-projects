package dto;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import constantes.EOrigemPedido;

public class PedidoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPedido;
	private Long numeroPedido;
	private EstabelecimentoDTO estabelecimentoDTO;
	private FuncionarioDTO funcionarioDTO;
	private ClienteDTO clienteDTO;
	private Date dataPedido;
	private double valorTotal;
	private EOrigemPedido origemPedido;
	private List<StatusPedidoDTO> statusPedidoDTOs = new ArrayList<StatusPedidoDTO>();
	private List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<ItemPedidoDTO>();
	private List<TipoPagamentoDTO> tbTipoPagamentos = new ArrayList<TipoPagamentoDTO>();
	private List<DeliveryDTO> deliveryDTOs = new ArrayList<DeliveryDTO>();

	public PedidoDTO() {
	}

	public PedidoDTO(Long numeroPedido, EstabelecimentoDTO estabelecimentoDTO,
			FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, Date dataPedido,
			long valorTotal) {
		this.numeroPedido = numeroPedido;
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.funcionarioDTO = funcionarioDTO;
		this.clienteDTO = clienteDTO;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
	}

	public PedidoDTO(Long numeroPedido, EstabelecimentoDTO estabelecimentoDTO,
			FuncionarioDTO funcionarioDTO, ClienteDTO clienteDTO, Date dataPedido,
			double valorTotal, EOrigemPedido origemPedido,
			List<StatusPedidoDTO> statusPedidoDTOs,
			List<ItemPedidoDTO> itemPedidoDTOs,
			List<TipoPagamentoDTO> tbTipoPagamentos, List<DeliveryDTO> deliveryDTOs) {
		this.numeroPedido = numeroPedido;
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.funcionarioDTO = funcionarioDTO;
		this.clienteDTO = clienteDTO;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.origemPedido = origemPedido;
		this.statusPedidoDTOs = statusPedidoDTOs;
		this.itemPedidoDTOs = itemPedidoDTOs;
		this.tbTipoPagamentos = tbTipoPagamentos;
		this.deliveryDTOs = deliveryDTOs;
	}

	public Long getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return this.estabelecimentoDTO;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public FuncionarioDTO getFuncionario() {
		return this.funcionarioDTO;
	}

	public void setFuncionario(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}

	public ClienteDTO getCliente() {
		return this.clienteDTO;
	}

	public void setCliente(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public Date getDataPedido() {
		return this.dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public EOrigemPedido getOrigemPedido() {
		return this.origemPedido;
	}

	public void setOrigemPedido(EOrigemPedido origemPedido) {
		this.origemPedido = origemPedido;
	}

	public List<StatusPedidoDTO> getStatusPedidos() {
		return this.statusPedidoDTOs;
	}

	public void setStatusPedidos(List<StatusPedidoDTO> statusPedidoDTOs) {
		this.statusPedidoDTOs = statusPedidoDTOs;
	}

	public List<ItemPedidoDTO> getItemPedidos() {
		return this.itemPedidoDTOs;
	}

	public void setItemPedidos(List<ItemPedidoDTO> itemPedidoDTOs) {
		this.itemPedidoDTOs = itemPedidoDTOs;
	}

	public List<TipoPagamentoDTO> getTipoPagamentos() {
		return this.tbTipoPagamentos;
	}

	public void setTipoPagamentos(List<TipoPagamentoDTO> tbTipoPagamentos) {
		this.tbTipoPagamentos = tbTipoPagamentos;
	}

	public List<DeliveryDTO> getDeliveries() {
		return this.deliveryDTOs;
	}

	public void setDeliveries(List<DeliveryDTO> deliveryDTOs) {
		this.deliveryDTOs = deliveryDTOs;
	}

}
