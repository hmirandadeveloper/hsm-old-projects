package fachada;

import java.util.List;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.IntervaloHorarioInvalidoException;
import dto.HorarioFuncionamentoDTO;

public interface IHorarioFuncionamentoFachada {
	public abstract void salvar(HorarioFuncionamentoDTO horarioFuncionamentoDTO) throws 
	EntidadeAtributoIncompletoException, IntervaloHorarioInvalidoException,
	EntidadeJaCadastradaException;
	public abstract void remover(long horarioFuncionamentoId) throws 
	EntidadeInexistenteException;
	public abstract HorarioFuncionamentoDTO atualizar(HorarioFuncionamentoDTO horarioFuncionamentoDTO) throws 
	EntidadeInexistenteException, IntervaloHorarioInvalidoException, EntidadeAtributoIncompletoException;
	public abstract HorarioFuncionamentoDTO buscar(long horarioFuncionamentoId);
	public abstract List<HorarioFuncionamentoDTO> buscarTodos();
}
