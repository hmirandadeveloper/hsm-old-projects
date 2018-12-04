package fachada;

import java.util.List;

import negocio.exception.DataInferiorException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.UsuarioSemPremissaoException;

import dto.StatusPedidoDTO;
import dto.UsuarioDTO;

public interface IStatusPedidoFachada {
	public abstract void salvar(StatusPedidoDTO statusPedidoDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, DataInferiorException;
	public abstract void remover(long statusPedidoId, UsuarioDTO usuarioDTOLogado) throws 
	EntidadeInexistenteException;
	public abstract StatusPedidoDTO atualizar(StatusPedidoDTO statusPedidoDTO, UsuarioDTO usuarioDTOLogado) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException, UsuarioSemPremissaoException;
	public abstract StatusPedidoDTO buscar(long statusPedidoId);
	public abstract List<StatusPedidoDTO> buscarTodos();
}
