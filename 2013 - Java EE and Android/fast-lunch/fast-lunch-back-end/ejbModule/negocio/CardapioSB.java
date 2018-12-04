package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import constantes.ETipoUsuario;

import negocio.exception.DataInferiorException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.CardapioConversorDTO;
import negocio.util.validador.data.OperacoesData;
import negocio.util.validador.entidade.CardapioAtributoValidador;

import persistencia.CardapioDAO;

import dto.CardapioDTO;
import entidade.Cardapio;
import fachada.ICardapioFachada;

@Stateless
@Remote(ICardapioFachada.class)
public class CardapioSB implements ICardapioFachada{
	
	@EJB
	CardapioDAO cardapioDAO;
	
	@Override
	public void salvar(CardapioDTO cardapioDTO) throws EntidadeAtributoIncompletoException, 
	DataInferiorException, EntidadeJaCadastradaException {
		if(CardapioAtributoValidador.validarAtributosPreenchidosEntidade(cardapioDTO))
		{
			if(OperacoesData.validacaoComDataAtual(cardapioDTO.getValidade()))
			{
				if(buscarCardapioPorNome(cardapioDTO.getNome(), 
						cardapioDTO.getTipoCardapio().getEstabelecimentoDTO().
						getIdEstabelecimento()) == null)
				{
					this.cardapioDAO.salvar(
							CardapioConversorDTO.converterDTOEmEntidade(cardapioDTO));
				}
				else
				{
					throw new EntidadeJaCadastradaException(Cardapio.class);
				}
			}
			else
			{
				throw new DataInferiorException(Cardapio.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Cardapio.class);
		}
	}

	@Override
	public void remover(long cardapioId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		if(buscar(cardapioId) != null)
		{
			this.cardapioDAO.remover(gerarEntidadePorId(cardapioId));
		}
		else
		{
			throw new EntidadeInexistenteException(Cardapio.class);
		}
	}

	@Override
	public CardapioDTO atualizar(CardapioDTO cardapioDTO) 
			throws EntidadeInexistenteException, DataInferiorException, EntidadeAtributoIncompletoException {
		
		if(CardapioAtributoValidador.validarAtributosPreenchidosEntidade(cardapioDTO))
		{
			if(buscar(cardapioDTO.getIdCardapio()) != null)
			{
				if(OperacoesData.validacaoComDataAtual(cardapioDTO.getValidade()))
				{
					return CardapioConversorDTO.converterEntidadeEmDTO(
							this.cardapioDAO.atualizar(
									CardapioConversorDTO.converterDTOEmEntidadeComId(cardapioDTO))
							);
				}
				else
				{
					throw new DataInferiorException(Cardapio.class);
				}
			}
			else
			{
				throw new EntidadeInexistenteException(Cardapio.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Cardapio.class);
		}
	}

	@Override
	public CardapioDTO buscar(long cardapioId) {
		return CardapioConversorDTO.converterEntidadeEmDTOComId(
				this.cardapioDAO.buscar(cardapioId));
	}

	@Override
	public List<CardapioDTO> buscarTodos(Long idEstabelecimento) {
		System.out.println("Buscando todos os cardápios para o estabelecimento de ID: " + idEstabelecimento);
		return CardapioConversorDTO.converterEntidadesListEmDTOsList(
					this.cardapioDAO.buscarCardapiosPeloEstabelecimento(idEstabelecimento, new Date())
				);
	}
	
	private Cardapio gerarEntidadePorId(long cardapioId)
	{
		Cardapio cardapio = new Cardapio();
		cardapio.setIdCardapio(cardapioId);
		return cardapio;
	}

	@Override
	public CardapioDTO buscarCardapioPorNome(String nome, Long idEstabelecimento) {
		
		CardapioDTO cardapioDTOBusca = CardapioConversorDTO.converterEntidadeEmDTO(
				this.cardapioDAO.buscarCardapioPeloNome(nome, idEstabelecimento, new Date()));

		if(cardapioDTOBusca.getNome() == null)
		{
			cardapioDTOBusca = null;
		}
		return cardapioDTOBusca;
	}

}
