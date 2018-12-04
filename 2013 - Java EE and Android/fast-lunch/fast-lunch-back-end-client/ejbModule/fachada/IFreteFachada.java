package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.ValorInvalidoException;
import dto.FreteDTO;

public interface IFreteFachada {
	public abstract void salvar(FreteDTO freteDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException,
	ValorInvalidoException;
	public abstract void remover(long freteId, ETipoUsuario admin) throws 
	EntidadeInexistenteException;
	public abstract FreteDTO atualizar(FreteDTO freteDTO) throws 
	EntidadeInexistenteException, ValorInvalidoException,
	EntidadeAtributoIncompletoException;
	public abstract FreteDTO buscar(long freteId);
	public abstract List<FreteDTO> buscarTodos(Long idEstabelecimento);
	public abstract FreteDTO buscarFretePorBairro(String bairro,
			Long idEstabelecimento);
}
