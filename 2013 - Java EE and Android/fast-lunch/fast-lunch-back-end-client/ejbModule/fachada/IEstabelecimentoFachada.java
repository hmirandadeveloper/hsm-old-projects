package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.CnpjInvalidoException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;

public interface IEstabelecimentoFachada {
	public abstract void salvar(EstabelecimentoDTO estabelecimentoDTO, FuncionarioDTO funcionarioDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException,
	CnpjInvalidoException;
	public abstract void remover(long estabelecimentoId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract EstabelecimentoDTO atualizar(EstabelecimentoDTO estabelecimentoDTO)
	throws	EntidadeInexistenteException, EntidadeAtributoIncompletoException, CnpjInvalidoException;
	public abstract EstabelecimentoDTO buscar(long estabelecimentoId);
	public abstract EstabelecimentoDTO buscarEstabelecimentoPorCnpj (String cnpj) throws
	CnpjInvalidoException;
	public abstract List<EstabelecimentoDTO> buscarTodos();
	public abstract void controleAtivacaoEstabelecimento(EstabelecimentoDTO estabelecimentoDTO, boolean situacao);
}
