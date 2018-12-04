package fachada;

import java.util.List;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.ValorInvalidoException;
import dto.ProdutoDTO;

public interface IProdutoFachada {
	public abstract void salvar(ProdutoDTO produtoDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, ValorInvalidoException;
	public abstract void remover(long produtoId) throws 
	EntidadeInexistenteException;
	public abstract ProdutoDTO atualizar(ProdutoDTO produtoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException, ValorInvalidoException;
	public abstract ProdutoDTO buscar(long produtoId) throws 
	EntidadeInexistenteException;
	public abstract ProdutoDTO buscarProdutoPorNome(String nome);
	public abstract List<ProdutoDTO> buscarTodos();
	public abstract void controleAtivacaoProduto(ProdutoDTO produtoDTO, boolean situacao);
}
