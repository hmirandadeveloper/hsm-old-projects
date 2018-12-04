package fachada;

import java.util.List;

import constantes.EDiaSemana;
import constantes.ETipoUsuario;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import dto.HorarioFuncionamentoDeliveryDTO;

public interface IHorarioFuncionamentoDeliveryFachada {
	public abstract void salvar(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long horarioFuncionamentoDeliveryId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract HorarioFuncionamentoDeliveryDTO 
	atualizar(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract HorarioFuncionamentoDeliveryDTO buscar(long horarioFuncionamentoDeliveryId);
	public abstract List<HorarioFuncionamentoDeliveryDTO> buscarTodos(Long idEstabelecimento);
	public abstract HorarioFuncionamentoDeliveryDTO buscarPorDia(Long idEstabelecimento, EDiaSemana diaSemana);
}
