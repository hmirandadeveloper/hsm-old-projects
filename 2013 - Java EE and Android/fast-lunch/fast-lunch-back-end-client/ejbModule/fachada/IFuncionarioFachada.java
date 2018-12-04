package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeAtributoUnicoDuplicadoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import dto.FuncionarioDTO;

public interface IFuncionarioFachada {
	public abstract void salvar(FuncionarioDTO funcionarioDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException,
	EntidadeInexistenteException;
	public abstract void remover(long funcionarioId, ETipoUsuario tipoUsuario) throws
	EntidadeInexistenteException;;
	public abstract FuncionarioDTO atualizar(FuncionarioDTO funcionarioDTO, String matriculaAnterior) throws
	EntidadeInexistenteException, EntidadeAtributoIncompletoException, EntidadeAtributoUnicoDuplicadoException;
	public abstract FuncionarioDTO buscar(long funcionarioId);
	public abstract FuncionarioDTO buscarFuncionarioPorMatricula(String matricula, Long id_estabelecimento) throws
	EntidadeInexistenteException;
	public abstract FuncionarioDTO buscaPeloUsuario(Long id_usuario) throws
	EntidadeInexistenteException;
	public abstract List<FuncionarioDTO> buscarTodos(Long idEstabelecimento);
	public abstract void controleAtivacaoFuncionario(FuncionarioDTO funcionarioDTO, boolean situacao);
}
