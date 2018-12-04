package dto.factory;

import dto.CardapioDTO;
import dto.CardapioProdutoDTO;
import dto.CargoDTO;
import dto.ClienteDTO;
import dto.DeliveryDTO;
import dto.EnderecoDTO;
import dto.EstabelecimentoDTO;
import dto.FidelidadeTrocaDTO;
import dto.FreteDTO;
import dto.FuncionarioDTO;
import dto.HorarioFuncionamentoDTO;
import dto.HorarioFuncionamentoDeliveryDTO;
import dto.HorarioFuncionamentoEstabelecimentoDTO;
import dto.ItemPedidoDTO;
import dto.PedidoDTO;
import dto.ProdutoDTO;
import dto.StatusPedidoDTO;
import dto.TipoCardapioDTO;
import dto.TipoPagamentoDTO;
import dto.UsuarioDTO;

public abstract class FastLunchDTOFactory {
	
	public static CardapioDTO getCardapioDTO()
	{
		CardapioDTO cardapioDTO = new CardapioDTO();
		cardapioDTO.setTipoCardapio(new TipoCardapioDTO());
		
		return cardapioDTO;
	}
	
	public static CardapioProdutoDTO getCardapioProdutoDTO()
	{
		CardapioProdutoDTO cardapioProdutoDTO = new CardapioProdutoDTO();
		cardapioProdutoDTO.setCardapio(new CardapioDTO());
		cardapioProdutoDTO.setProduto(new ProdutoDTO());
		
		return cardapioProdutoDTO;
	}
	
	public static CargoDTO getCargoDTO()
	{
		CargoDTO cargoDTO = new CargoDTO();
		cargoDTO.setEstabelecimento(new EstabelecimentoDTO());
		
		return cargoDTO;
	}
	
	public static ClienteDTO getClienteDTO()
	{
		return new ClienteDTO();
	}
	
	public static ClienteDTO getClienteDTOAtributosCarregados()
	{
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setUsuario(new UsuarioDTO());
		
		return clienteDTO;
	}
	
	public static DeliveryDTO getDeliveryDTO()
	{
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		deliveryDTO.setEndereco(new EnderecoDTO());
		deliveryDTO.setFrete(new FreteDTO());
		deliveryDTO.setPedido(new PedidoDTO());
		
		return deliveryDTO;
	}
	
	public static EnderecoDTO getEnderecoDTO()
	{
		return new EnderecoDTO();
	}
	
	public static EstabelecimentoDTO getEstabelecimentoDTO()
	{
		EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
		estabelecimentoDTO.setEstabelecimento(new EstabelecimentoDTO());
		estabelecimentoDTO.setEnderecoDTO(new EnderecoDTO());
		
		return estabelecimentoDTO;
	}
	
	public static FidelidadeTrocaDTO getFidelidadeTrocaDTO()
	{
		FidelidadeTrocaDTO fidelidadeTrocaDTO = new FidelidadeTrocaDTO();
		fidelidadeTrocaDTO.setCliente(new ClienteDTO());
		fidelidadeTrocaDTO.setProduto(new ProdutoDTO());
		
		return fidelidadeTrocaDTO;
	}
	
	public static FreteDTO getFreteDTO()
	{
		FreteDTO freteDTO = new FreteDTO();
		freteDTO.setEstabelecimentoDTO(new EstabelecimentoDTO());
		
		return new FreteDTO();
	}
	
	public static FuncionarioDTO getFuncionarioDTO()
	{
		return new FuncionarioDTO();
	}
	
	public static FuncionarioDTO getFuncionarioDTOAtributosCarregados()
	{
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		
		funcionarioDTO.setUsuario(new UsuarioDTO());
		funcionarioDTO.setCargo(new CargoDTO());
		
		return funcionarioDTO;
	}
	
	public static HorarioFuncionamentoDeliveryDTO getHorarioFuncionamentoDeliveryDTO()
	{
		HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO = 
				new HorarioFuncionamentoDeliveryDTO();
		horarioFuncionamentoDeliveryDTO.setEstabelecimentoDTO(new EstabelecimentoDTO());
		horarioFuncionamentoDeliveryDTO.setHorarioFuncionamento(new HorarioFuncionamentoDTO());
		return horarioFuncionamentoDeliveryDTO;
	}
	
	public static HorarioFuncionamentoDTO getHorarioFuncionamentoDTO()
	{
		return new HorarioFuncionamentoDTO();
	}
	
	public static HorarioFuncionamentoEstabelecimentoDTO getHorarioFuncionamentoEstabelecimentoDTO()
	{
		HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO = 
				new HorarioFuncionamentoEstabelecimentoDTO();
		horarioFuncionamentoEstabelecimentoDTO.setEstabelecimento(new EstabelecimentoDTO());
		horarioFuncionamentoEstabelecimentoDTO.setHorarioFuncionamento(new HorarioFuncionamentoDTO());
		
		return horarioFuncionamentoEstabelecimentoDTO;
	}
	
	public static ItemPedidoDTO getItemPedidoDTO()
	{
		return new ItemPedidoDTO();
	}
	
	public static PedidoDTO getPedidoDTO()
	{
		return new PedidoDTO();
	}
	
	public static PedidoDTO getPedidoDTOAtributosCarregados()
	{
		PedidoDTO pedidoDTO = new PedidoDTO();
		
		pedidoDTO.setCliente(new ClienteDTO());
		pedidoDTO.setFuncionario(new FuncionarioDTO());
		pedidoDTO.setEstabelecimento(new EstabelecimentoDTO());
		
		return pedidoDTO;
	}
	
	public static ProdutoDTO getProdutoDTO()
	{
		return new ProdutoDTO();
	}
	
	public static StatusPedidoDTO getStatusPedidoDTO()
	{
		return new StatusPedidoDTO();
	}
	
	
	public static TipoCardapioDTO getTipoCardapioDTO()
	{
		return new TipoCardapioDTO();
	}
	
	public static TipoPagamentoDTO getTipoPagamentoDTO()
	{
		return new TipoPagamentoDTO();
	}
	
	public static UsuarioDTO getUsuarioDTO()
	{
		return new UsuarioDTO();
	}
}
