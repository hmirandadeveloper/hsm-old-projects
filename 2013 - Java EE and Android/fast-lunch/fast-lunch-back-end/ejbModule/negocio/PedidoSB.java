package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.IntervaloHorarioInvalidoException;
import negocio.util.conversor.dto.ItemPedidoConversorDTO;
import negocio.util.conversor.dto.PedidoConversorDTO;
import negocio.util.validador.data.OperacoesData;
import negocio.util.validador.entidade.PedidoAtributoValidador;
import persistencia.ItemPedidoDAO;
import persistencia.PedidoDAO;
import constantes.ETipoUsuario;
import dto.ItemPedidoDTO;
import dto.PedidoDTO;
import entidade.Pedido;
import fachada.IPedidoFachada;

@Stateless
@Remote(IPedidoFachada.class)
public class PedidoSB implements IPedidoFachada{
	
	@EJB
	PedidoDAO pedidoDAO;
	
	@EJB
	ItemPedidoDAO itemPedidoDAO;
	
	@Override
	public void salvar(PedidoDTO pedidoDTO) throws EntidadeAtributoIncompletoException, IntervaloHorarioInvalidoException {
		
		if(PedidoAtributoValidador.validarAtributosPreenchidosEntidade(pedidoDTO))
		{
			if(OperacoesData.validacaoComDataAtual(pedidoDTO.getDataPedido()))
			{
				calcularValorTotal(pedidoDTO);
				pedidoDTO.setDataPedido(new Date());
				System.out.println("A porra do ID que ele está tentando inserir é: " + pedidoDTO.getIdPedido());
				
				this.pedidoDAO.salvarPedido((PedidoConversorDTO.converterDTOEmEntidade(pedidoDTO))
						, ItemPedidoConversorDTO.converterDTOsListEmEntidadesSet(pedidoDTO.getItemPedidos()));
			}
			else
			{
				throw new IntervaloHorarioInvalidoException();
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Pedido.class);
		}
	}

	@Override
	public void remover(long pedidoId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		
		if(buscar(pedidoId) != null)
		{
			this.pedidoDAO.remover(gerarEntidadePorId(pedidoId));
		}
		else
		{
			throw new EntidadeInexistenteException(Pedido.class);
		}
	}

	@Override
	public PedidoDTO atualizar(PedidoDTO pedidoDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		
		if(buscar(pedidoDTO.getIdPedido()) != null)
		{
			if(PedidoAtributoValidador.validarAtributosPreenchidosEntidade(pedidoDTO))
			{
			return PedidoConversorDTO.converterEntidadeEmDTO(
					this.pedidoDAO.atualizar(
							PedidoConversorDTO.converterDTOEmEntidadeComId(pedidoDTO))
					);
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(Pedido.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Pedido.class);
		}
	}

	@Override
	public PedidoDTO buscar(long pedidoId) {
		return PedidoConversorDTO.converterEntidadeEmDTOComId(
				this.pedidoDAO.buscar(pedidoId));
	}

	@Override
	public List<PedidoDTO> buscarTodos(Long id_estabelecimento) {
		return PedidoConversorDTO.converterEntidadesListEmDTOsList(
					this.pedidoDAO.buscarPedidosPeloIdEstabelecimento(id_estabelecimento)
				);
	}
	
	private Pedido gerarEntidadePorId(long pedidoId)
	{
		Pedido pedido = new Pedido();
		pedido.setIdPedido(pedidoId);
		return pedido;
	}
	
	private void calcularValorTotal(PedidoDTO pedidoDTO)
	{
		if(pedidoDTO.getItemPedidos().size() > 0)
		{
			double valorTotalPedido = 0;
			for(ItemPedidoDTO ip : pedidoDTO.getItemPedidos())
			{
				valorTotalPedido = (valorTotalPedido + ((
						ip.getCardapioProduto().getProduto().getPrecoPromocional() > 0 ? 
								ip.getCardapioProduto().getProduto().getPrecoPromocional() : 
									ip.getCardapioProduto().getProduto().getPreco()
									) * ip.getQuantidade()));
			}
		}
	}
	
	@Override	
	public List<PedidoDTO> buscarPedidosPeloCnpjEstabelecimento(String cnpj)
	{
		List<PedidoDTO> pedidoDTOs = PedidoConversorDTO.converterEntidadesListEmDTOsList(
				this.pedidoDAO.buscarPedidosPeloCnpjEstabelecimento(cnpj)
				);
		
		if(pedidoDTOs.size() == 0)
		{
			pedidoDTOs = null;
		}
		
		return pedidoDTOs;
	}
	
	@Override	
	public List<PedidoDTO> buscarPedidosPeloIdFuncionario(Long id_funcionario)
	{
		List<PedidoDTO> pedidoDTOs = PedidoConversorDTO.converterEntidadesListEmDTOsList(
				this.pedidoDAO.buscarPedidosPeloIdFuncionario(id_funcionario)
				);
		
		if(pedidoDTOs.size() == 0)
		{
			pedidoDTOs = null;
		}
			
		return pedidoDTOs;
	}
	
	@Override
	public List<PedidoDTO> buscarPedidosPeloCpfCliente(String cpf, Long id_estabelecimento)
	{
		List<PedidoDTO> pedidoDTOs = PedidoConversorDTO.converterEntidadesListEmDTOsList(
				this.pedidoDAO.buscarPedidosPeloCpfCliente(cpf, id_estabelecimento)
				);
		
		if(pedidoDTOs.size() == 0)
		{
			pedidoDTOs = null;
		}
			
		return pedidoDTOs;
	}
	
	@Override
	public List<PedidoDTO> buscarPedidosPelaOrigem(String origem, Long id_estabelecimento)
	{
		List<PedidoDTO> pedidoDTOs = PedidoConversorDTO.converterEntidadesListEmDTOsList(
				this.pedidoDAO.buscarPedidosPelaOrigem(origem, id_estabelecimento)
				);
		
		if(pedidoDTOs.size() == 0)
		{
			pedidoDTOs = null;
		}
		
		return pedidoDTOs;
	}
	
}
