package fachada;

import java.util.List;

import negocio.exception.DataInferiorException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.ValorInvalidoException;

import dto.FidelidadeTrocaDTO;

public interface IFidelidadeTrocaFachada {
	public abstract void salvar(FidelidadeTrocaDTO fidelidadeTrocaDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException,
	ValorInvalidoException, DataInferiorException;
	public abstract void remover(long fidelidadeTrocaId) throws 
	EntidadeInexistenteException;
	public abstract FidelidadeTrocaDTO atualizar(FidelidadeTrocaDTO fidelidadeTrocaDTO) throws 
	EntidadeInexistenteException, ValorInvalidoException, EntidadeAtributoIncompletoException;
	public abstract FidelidadeTrocaDTO buscar(long fidelidadeTrocaId);
	public abstract List<FidelidadeTrocaDTO> buscarTodos();
}
