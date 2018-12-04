package fachada;

import java.util.List;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;

import dto.EnderecoDTO;

public interface IEnderecoFachada {
	public abstract void salvar(EnderecoDTO enderecoDTO) throws 
	EntidadeAtributoIncompletoException;
	public abstract void remover(long enderecoId) throws 
	EntidadeInexistenteException;
	public abstract EnderecoDTO atualizar(EnderecoDTO enderecoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract EnderecoDTO buscar(long enderecoId);
	public abstract List<EnderecoDTO> buscarTodos();
}
