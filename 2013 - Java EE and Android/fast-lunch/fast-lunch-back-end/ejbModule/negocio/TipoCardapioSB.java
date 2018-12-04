package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.TipoCardapioConversorDTO;
import negocio.util.validador.entidade.TipoCardapioAtributoValidador;

import persistencia.TipoCardapioDAO;

import dto.TipoCardapioDTO;
import entidade.TipoCardapio;
import fachada.ITipoCardapioFachada;

@Stateless
@Remote(ITipoCardapioFachada.class)
public class TipoCardapioSB implements ITipoCardapioFachada{
	
	@EJB
	TipoCardapioDAO tipoCardapioDAO;
	
	@Override
	public void salvar(TipoCardapioDTO tipoCardapioDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		
		if(TipoCardapioAtributoValidador.validarAtributosPreenchidosEntidade(tipoCardapioDTO))
		{
			if(buscarTipoCardapioPeloNome(tipoCardapioDTO.getNome(),
					tipoCardapioDTO.getEstabelecimentoDTO().getIdEstabelecimento()) == null)
			{
				tipoCardapioDTO.setAtivo(true);
				this.tipoCardapioDAO.salvar(TipoCardapioConversorDTO.converterDTOEmEntidade(tipoCardapioDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(TipoCardapio.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(TipoCardapio.class);
		}
	}

	@Override
	public void remover(long tipoCardapioId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		if(buscar(tipoCardapioId) != null)
		{
			this.tipoCardapioDAO.remover(gerarEntidadePorId(tipoCardapioId));
		}
		else
		{
			throw new EntidadeInexistenteException(TipoCardapio.class);
		}
	}

	@Override
	public TipoCardapioDTO atualizar(TipoCardapioDTO tipoCardapioDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		if(buscar(tipoCardapioDTO.getIdTipoCardapio()) != null)
		{
			if(TipoCardapioAtributoValidador.validarAtributosPreenchidosEntidade(tipoCardapioDTO))
			{
		return TipoCardapioConversorDTO.converterEntidadeEmDTO(
				this.tipoCardapioDAO.atualizar(
						TipoCardapioConversorDTO.converterDTOEmEntidadeComId(tipoCardapioDTO))
				);
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(TipoCardapio.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(TipoCardapio.class);
		}
	}

	@Override
	public TipoCardapioDTO buscar(long tipoCardapioId) {
		return TipoCardapioConversorDTO.converterEntidadeEmDTOComId(
				this.tipoCardapioDAO.buscar(tipoCardapioId));
	}

	@Override
	public List<TipoCardapioDTO> buscarTodos(Long idEstabelecimento) {
		return TipoCardapioConversorDTO.converterEntidadesListEmDTOsList(
					this.tipoCardapioDAO.buscarTipoCardapiosPeloEstabelecimento(idEstabelecimento)
				);
	}
	
	private TipoCardapio gerarEntidadePorId(long tipoCardapioId)
	{
		TipoCardapio tipoCardapio = new TipoCardapio();
		tipoCardapio.setIdTipoCardapio(tipoCardapioId);
		return tipoCardapio;
	}

	@Override
	public TipoCardapioDTO buscarTipoCardapioPeloNome(String nome, Long idEstabelecimento) {
		
		TipoCardapioDTO tipoCardapioDTOBusca = TipoCardapioConversorDTO.converterEntidadeEmDTOComId(
				this.tipoCardapioDAO.buscarTipoCardapioPeloNome(nome, idEstabelecimento));
		
		if(tipoCardapioDTOBusca.getDescricao() == null)
		{
			tipoCardapioDTOBusca = null;
		}
		
		return tipoCardapioDTOBusca;
	}

}
