package fachada;

import java.util.List;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;

import dto.UsuarioDTO;

public interface IUsuarioFachada {
	public abstract void salvar(UsuarioDTO usuarioDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long usuarioId) throws 
	EntidadeInexistenteException;
	public abstract UsuarioDTO atualizar(UsuarioDTO usuarioDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract UsuarioDTO buscar(long usuarioId);
	public abstract UsuarioDTO buscarUsuarioPeloLogin(String login);
	public abstract List<UsuarioDTO> buscarTodos();
}
