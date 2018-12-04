package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.TipoPagamentoConversorDTO;
import negocio.util.validador.entidade.TipoPagamentoAtributoValidador;

import persistencia.TipoPagamentoDAO;

import dto.TipoPagamentoDTO;
import entidade.TipoPagamento;
import fachada.ITipoPagamentoFachada;

@Stateless
@Remote(ITipoPagamentoFachada.class)
public class TipoPagamentoSB implements ITipoPagamentoFachada{
	
	@EJB
	TipoPagamentoDAO tipoPagamentoDAO;
	
	@Override
	public void salvar(TipoPagamentoDTO tipoPagamentoDTO) throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		if(TipoPagamentoAtributoValidador.validarAtributosPreenchidosEntidade(tipoPagamentoDTO))
		{
			if(buscarTipoPagamentoPelaDescricao(tipoPagamentoDTO.getDescricao(),
					tipoPagamentoDTO.getEstabelecimentoDTO().getIdEstabelecimento()) == null)
			{
				tipoPagamentoDTO.setAtivo(true);
				this.tipoPagamentoDAO.salvar(TipoPagamentoConversorDTO.converterDTOEmEntidade(tipoPagamentoDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(TipoPagamento.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(TipoPagamento.class);
		}
	}

	@Override
	public void remover(long tipoPagamentoId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		if(buscar(tipoPagamentoId) != null)
		{
		this.tipoPagamentoDAO.remover(gerarEntidadePorId(tipoPagamentoId));
		}
		else
		{
			throw new EntidadeInexistenteException(TipoPagamento.class);
		}
	}

	@Override
	public TipoPagamentoDTO atualizar(TipoPagamentoDTO tipoPagamentoDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		if(buscar(tipoPagamentoDTO.getIdTipoPagamento()) != null)
		{
			if(TipoPagamentoAtributoValidador.validarAtributosPreenchidosEntidade(tipoPagamentoDTO))
			{
				return TipoPagamentoConversorDTO.converterEntidadeEmDTO(
						this.tipoPagamentoDAO.atualizar(
								TipoPagamentoConversorDTO.converterDTOEmEntidadeComId(tipoPagamentoDTO))
						);
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(TipoPagamento.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(TipoPagamento.class);
		}
	}

	@Override
	public TipoPagamentoDTO buscar(long tipoPagamentoId) {
		return TipoPagamentoConversorDTO.converterEntidadeEmDTOComId(
				this.tipoPagamentoDAO.buscar(tipoPagamentoId));
	}

	@Override
	public List<TipoPagamentoDTO> buscarTodos(Long idEstabelecimento) {
		return TipoPagamentoConversorDTO.converterEntidadesListEmDTOsList(
					this.tipoPagamentoDAO.buscarTipoPagamentosPeloEstabelecimento(idEstabelecimento)
				);
	}
	
	private TipoPagamento gerarEntidadePorId(long tipoPagamentoId)
	{
		TipoPagamento tipoPagamento = new TipoPagamento();
		tipoPagamento.setIdTipoPagamento(tipoPagamentoId);
		return tipoPagamento;
	}

	@Override
	public TipoPagamentoDTO buscarTipoPagamentoPelaDescricao(String descricao, Long idEstabelecimento) {
		TipoPagamentoDTO tipoPagamentoDTOBusca = TipoPagamentoConversorDTO.converterEntidadeEmDTOComId(
				this.tipoPagamentoDAO.buscarTipoPagamentoPelaDescricao(descricao, idEstabelecimento));
		
		if(tipoPagamentoDTOBusca.getDescricao() == null)
		{
			tipoPagamentoDTOBusca = null;
		}
		
		return tipoPagamentoDTOBusca;
	}

	@Override
	public void controleAtivacaoTipoPagamento(
			TipoPagamentoDTO tipoPagamentoDTO, boolean situacao) {
		// TODO Auto-generated method stub
		
	}

}
