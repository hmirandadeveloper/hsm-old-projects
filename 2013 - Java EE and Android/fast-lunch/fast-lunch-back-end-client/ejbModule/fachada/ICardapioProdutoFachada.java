package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import dto.CardapioProdutoDTO;

public interface ICardapioProdutoFachada {
	public abstract void salvar(CardapioProdutoDTO cardapioProdutoDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long cardapioProdutoId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract CardapioProdutoDTO atualizar(CardapioProdutoDTO cardapioProdutoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException,
	EntidadeJaCadastradaException;
	public abstract CardapioProdutoDTO buscar(long cardapioProdutoId);
	public abstract List<CardapioProdutoDTO> buscarTodos(Long idEstabelecimento);
	public abstract List<CardapioProdutoDTO> buscarProdutosPeloNome(Long idEstabelecimento, String nomeProduto);
	public abstract CardapioProdutoDTO buscarProdutoPeloNome(Long idEstabelecimento, String nomeProduto);
	public abstract void controleAtivacaoCardapioProduto(CardapioProdutoDTO cardapioProdutoDTO, boolean situacao);
	public abstract List<CardapioProdutoDTO> buscarPeloCardapio(Long idEstabelecimento,
			String nomeCardapio);
}
