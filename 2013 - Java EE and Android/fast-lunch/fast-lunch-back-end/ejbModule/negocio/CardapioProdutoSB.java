package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.CardapioProdutoConversorDTO;
import negocio.util.validador.entidade.CardapioProdutoAtributoValidador;
import persistencia.CardapioProdutoDAO;
import constantes.ETipoUsuario;
import dto.CardapioProdutoDTO;
import entidade.CardapioProduto;
import fachada.ICardapioProdutoFachada;

@Stateless
@Remote(ICardapioProdutoFachada.class)
public class CardapioProdutoSB implements ICardapioProdutoFachada{

	@EJB
	CardapioProdutoDAO cardapioProdutoDAO;

	@Override
	public void salvar(CardapioProdutoDTO cardapioProdutoDTO) throws EntidadeAtributoIncompletoException, 
	EntidadeJaCadastradaException {
		System.out.println("Salvando...");
		if(CardapioProdutoAtributoValidador.validarAtributosPreenchidosEntidade(cardapioProdutoDTO))
		{			
			if(buscarPeloProdutoNomeECardapio(
					cardapioProdutoDTO.getCardapio().
					getTipoCardapio().
					getEstabelecimentoDTO().getIdEstabelecimento(), 
					cardapioProdutoDTO.getProduto().getNome(), 
					cardapioProdutoDTO.getCardapio().getNome()
					) == null)
			{
				CardapioProdutoDTO cardapioProdutoDTOBusca = buscarProdutoPeloNome(
						cardapioProdutoDTO.getCardapio().getTipoCardapio().getEstabelecimentoDTO().
						getIdEstabelecimento(), cardapioProdutoDTO.getProduto().getNome());

				if(cardapioProdutoDTOBusca != null)
				{
					cardapioProdutoDTO.setProduto(cardapioProdutoDTOBusca.getProduto());
				}
				cardapioProdutoDTO.getProduto().setAtivo(true);
				if(cardapioProdutoDTO.getProduto().getImagem() == null)
				{
					cardapioProdutoDTO.getProduto().setImagem("Sem Imagem");
				}

				System.out.println("Iniciando persistência...");

				cardapioProdutoDTO.setAtivo(true);

				this.cardapioProdutoDAO.salvar(
						CardapioProdutoConversorDTO.converterDTOEmEntidadeComId(cardapioProdutoDTO));

				System.out.println("Salvo com sucesso!");

			}
			else
			{
				throw new EntidadeJaCadastradaException(CardapioProduto.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(CardapioProduto.class);
		}
	}

	@Override
	public void remover(long cardapioProdutoId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		if(buscar(cardapioProdutoId) != null)
		{
			if(tipoUsuario == ETipoUsuario.ADMIN)
			{
				this.cardapioProdutoDAO.remover(gerarEntidadePorId(cardapioProdutoId));
			}
			else
			{
				CardapioProduto cardapioProduto = this.cardapioProdutoDAO.buscar(cardapioProdutoId);
				cardapioProduto.setAtivo(false);
				this.cardapioProdutoDAO.atualizar(cardapioProduto);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(CardapioProduto.class);
		}
	}

	@Override
	public CardapioProdutoDTO atualizar(CardapioProdutoDTO cardapioProdutoDTO) 
			throws EntidadeInexistenteException, EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		if(CardapioProdutoAtributoValidador.validarAtributosPreenchidosEntidade(cardapioProdutoDTO))
		{
			if(buscar(cardapioProdutoDTO.getIdcardapioProduto()) != null)
			{
				System.out.println("Verificando se já existe um produto com esse nome");
				CardapioProdutoDTO cardapioProdutoDTOResultado = buscarPeloProdutoNomeECardapio(
						cardapioProdutoDTO.getCardapio().
						getTipoCardapio().
						getEstabelecimentoDTO().getIdEstabelecimento(), 
						cardapioProdutoDTO.getProduto().getNome(), 
						cardapioProdutoDTO.getCardapio().getNome()
						);
				if(cardapioProdutoDTOResultado.equals(cardapioProdutoDTO))
				{
					System.out.println("Não existe!!");
					System.out.println("Pontuação Fidelidade de Produto: " + cardapioProdutoDTO.getProduto().getPontuacaoFidelidade());

					cardapioProdutoDTO.getProduto().setAtivo(true);
					if(cardapioProdutoDTO.getProduto().getImagem() == null)
					{
						cardapioProdutoDTO.getProduto().setImagem("Sem Imagem");
					}

					System.out.println("Iniciando persistência...");

					cardapioProdutoDTO.setAtivo(true);
					System.out.println("Atualizado com sucesso!");
					return CardapioProdutoConversorDTO.converterEntidadeEmDTO(
							this.cardapioProdutoDAO.atualizar(
									CardapioProdutoConversorDTO.converterDTOEmEntidadeComId(cardapioProdutoDTO)
									));


				}
				else
				{
					throw new EntidadeJaCadastradaException(CardapioProduto.class);
				}
			}
			else
			{
				throw new EntidadeInexistenteException(CardapioProduto.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(CardapioProduto.class);
		}
	}

	@Override
	public CardapioProdutoDTO buscar(long cardapioProdutoId) {
		return CardapioProdutoConversorDTO.converterEntidadeEmDTOComId(
				this.cardapioProdutoDAO.buscar(cardapioProdutoId));
	}

	@Override
	public List<CardapioProdutoDTO> buscarTodos(Long idEstabelecimento) {
		return CardapioProdutoConversorDTO.converterEntidadesListEmDTOsList(
				this.cardapioProdutoDAO.
				buscarCardapioProdutosPeloEstabelecimento(idEstabelecimento)
				);
	}

	private CardapioProduto gerarEntidadePorId(long cardapioProdutoId)
	{
		CardapioProduto cardapioProduto = new CardapioProduto();
		cardapioProduto.setIdcardapioProduto(cardapioProdutoId);
		return cardapioProduto;
	}

	@Override
	public void controleAtivacaoCardapioProduto(
			CardapioProdutoDTO cardapioProdutoDTO, boolean situacao) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CardapioProdutoDTO> buscarProdutosPeloNome(
			Long idEstabelecimento, String nomeProduto) {

		List<CardapioProdutoDTO> cardapioProdutoDTOs = CardapioProdutoConversorDTO.converterEntidadesListEmDTOsList(
				this.cardapioProdutoDAO.buscarCardapioProdutosPeloNomeProduto(nomeProduto, idEstabelecimento)
				);

		if(cardapioProdutoDTOs == null)
		{
			return null;
		}
		else if(cardapioProdutoDTOs.size() == 0)
		{
			return null;
		}

		return cardapioProdutoDTOs;
	}

	@Override
	public CardapioProdutoDTO buscarProdutoPeloNome(
			Long idEstabelecimento, String nomeProduto) {
		CardapioProdutoDTO cardapioProdutoDTOBusca = CardapioProdutoConversorDTO.converterEntidadeEmDTOComId(
				this.cardapioProdutoDAO.buscarCardapioProdutoPeloNomeProduto(nomeProduto, idEstabelecimento)
				);

		if(cardapioProdutoDTOBusca.getIdcardapioProduto() == null)
		{
			cardapioProdutoDTOBusca = null;
		}
		return cardapioProdutoDTOBusca;
	}

	private CardapioProdutoDTO buscarPeloProdutoNomeECardapio(
			Long idEstabelecimento, String nomeProduto, String nomeCardapio) {

		CardapioProdutoDTO cardapioProdutoDTOBuscaUnica = CardapioProdutoConversorDTO.converterEntidadeEmDTOComId(
				this.cardapioProdutoDAO.buscarCardapioProdutoPeloNomeProdutoECardapio(nomeProduto, nomeCardapio, idEstabelecimento)
				);	

		if(cardapioProdutoDTOBuscaUnica.getIdcardapioProduto() == null)
		{
			cardapioProdutoDTOBuscaUnica = null;
		}

		return cardapioProdutoDTOBuscaUnica;
	}
	
	@Override
	public List<CardapioProdutoDTO> buscarPeloCardapio(
			Long idEstabelecimento, String nomeCardapio) {

		List<CardapioProdutoDTO> cardapioProdutoDTOBusca = CardapioProdutoConversorDTO.converterEntidadesListEmDTOsList(
				this.cardapioProdutoDAO.buscarCardapioProdutoPeloCardapio(nomeCardapio, idEstabelecimento)
				);	

		if(cardapioProdutoDTOBusca == null)
		{
			cardapioProdutoDTOBusca = null;
		}

		return cardapioProdutoDTOBusca;
	}

}
