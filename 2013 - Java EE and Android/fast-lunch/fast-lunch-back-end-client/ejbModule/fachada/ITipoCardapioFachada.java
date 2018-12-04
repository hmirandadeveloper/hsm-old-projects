package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;

import dto.TipoCardapioDTO;

public interface ITipoCardapioFachada {
	public abstract void salvar(TipoCardapioDTO tipoCardapioDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long tipoCardapioId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract TipoCardapioDTO atualizar(TipoCardapioDTO tipoCardapioDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract TipoCardapioDTO buscar(long tipoCardapioId);
	public abstract TipoCardapioDTO buscarTipoCardapioPeloNome(String nome, Long idEstabelecimento);
	public abstract List<TipoCardapioDTO> buscarTodos(Long idEstabelecimento);
}
