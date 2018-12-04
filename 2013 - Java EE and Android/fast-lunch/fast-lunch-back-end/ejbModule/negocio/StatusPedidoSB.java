package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import constantes.ETipoUsuario;

import negocio.exception.DataInferiorException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.UsuarioSemPremissaoException;
import negocio.util.conversor.dto.StatusPedidoConversorDTO;
import negocio.util.validador.data.OperacoesData;
import negocio.util.validador.entidade.StatusPedidoAtributoValidador;

import persistencia.StatusPedidoDAO;

import dto.StatusPedidoDTO;
import dto.UsuarioDTO;
import entidade.StatusPedido;
import fachada.IStatusPedidoFachada;

@Stateless
@Remote(IStatusPedidoFachada.class)
public class StatusPedidoSB implements IStatusPedidoFachada{
	
	@EJB
	StatusPedidoDAO statusPedidoDAO;
	
	@Override
	public void salvar(StatusPedidoDTO statusPedidoDTO) throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, DataInferiorException {
		
		if(StatusPedidoAtributoValidador.validarAtributosPreenchidosEntidade(statusPedidoDTO))
		{
			if(buscar(statusPedidoDTO.getIdStatusPedido()) == null)
			{
				if(OperacoesData.validacaoComDataAtual(statusPedidoDTO.getDataHora()))
				{
					this.statusPedidoDAO.salvar(StatusPedidoConversorDTO.converterDTOEmEntidade(statusPedidoDTO));
				}
				else
				{
					throw new DataInferiorException(StatusPedido.class);
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(StatusPedido.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(StatusPedido.class);
		}
	}

	@Override
	public void remover(long statusPedidoId, UsuarioDTO usuarioDTOLogado) 
			throws EntidadeInexistenteException {
		
		if(buscar(statusPedidoId) != null)
		{
			this.statusPedidoDAO.remover(gerarEntidadePorId(statusPedidoId));
		}
		else
		{
			throw new EntidadeInexistenteException(StatusPedido.class);
		}
	}

	@Override
	public StatusPedidoDTO atualizar(StatusPedidoDTO statusPedidoDTO, UsuarioDTO usuarioDTOLogado) 
			throws EntidadeInexistenteException, EntidadeAtributoIncompletoException, UsuarioSemPremissaoException {
		
		StatusPedidoDTO statusPedidoDTOAnterior = buscar(statusPedidoDTO.getIdStatusPedido());
		
		if(statusPedidoDTOAnterior != null)
		{
			if(StatusPedidoAtributoValidador.validarAtributosPreenchidosEntidade(statusPedidoDTO))
			{
				if((statusPedidoDTO.getStatusPedido().ordinal() < 
						statusPedidoDTOAnterior.getStatusPedido().ordinal()) || 
						usuarioDTOLogado.getTipoUsuario().ordinal() >= ETipoUsuario.GER.ordinal())
				{
					return StatusPedidoConversorDTO.converterEntidadeEmDTO(
							this.statusPedidoDAO.atualizar(
									StatusPedidoConversorDTO.converterDTOEmEntidadeComId(statusPedidoDTO))
							);
				}
				else
				{
					throw new UsuarioSemPremissaoException();
				}
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(StatusPedido.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(StatusPedido.class);
		}
	}

	@Override
	public StatusPedidoDTO buscar(long statusPedidoId) {
		return StatusPedidoConversorDTO.converterEntidadeEmDTOComId(
				this.statusPedidoDAO.buscar(statusPedidoId));
	}

	@Override
	public List<StatusPedidoDTO> buscarTodos() {
		return StatusPedidoConversorDTO.converterEntidadesListEmDTOsList(
					this.statusPedidoDAO.buscarTodos()
				);
	}
	
	private StatusPedido gerarEntidadePorId(long statusPedidoId)
	{
		StatusPedido statusPedido = new StatusPedido();
		statusPedido.setIdStatusPedido(statusPedidoId);
		return statusPedido;
	}

}
