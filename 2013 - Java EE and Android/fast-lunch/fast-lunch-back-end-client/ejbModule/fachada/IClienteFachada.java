package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.CpfInvalidoException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import dto.ClienteDTO;

public interface IClienteFachada {
	public abstract void salvar(ClienteDTO clienteDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, 
	CpfInvalidoException;
	public abstract void remover(long clienteId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract ClienteDTO atualizar(ClienteDTO clienteDTO) throws
	EntidadeInexistenteException, CpfInvalidoException, EntidadeAtributoIncompletoException;
	public abstract ClienteDTO buscar(long clienteId);
	public abstract ClienteDTO buscaPeloCpf(String cpf) throws
	CpfInvalidoException;
	public abstract ClienteDTO buscaPeloEmail(String email) throws
	EntidadeInexistenteException;
	public abstract ClienteDTO buscaPeloUsuario(Long id_usuario) throws
	EntidadeInexistenteException;
	public abstract List<ClienteDTO> buscarTodos();
	public abstract void controleAtivacaoCliente(ClienteDTO clienteDTO, boolean situacao);
}
