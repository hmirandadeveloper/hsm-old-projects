package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.DataInferiorException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;

import dto.CardapioDTO;

public interface ICardapioFachada {
	public abstract void salvar(CardapioDTO cardapioDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException,
	DataInferiorException;
	public abstract void remover(long cardapioId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract CardapioDTO atualizar(CardapioDTO cardapioDTO) throws 
	EntidadeInexistenteException, DataInferiorException, 
	EntidadeAtributoIncompletoException;
	public abstract CardapioDTO buscar(long cardapioId);
	public abstract CardapioDTO buscarCardapioPorNome(String nome, Long idEstabelecimento);
	public abstract List<CardapioDTO> buscarTodos(Long idEstabelecimento);
}
