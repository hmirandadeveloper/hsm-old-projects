package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.IntervaloHorarioInvalidoException;
import negocio.util.conversor.dto.HorarioFuncionamentoConversorDTO;
import negocio.util.validador.entidade.HorarioFuncionamentoAtributoValidador;
import persistencia.HorarioFuncionamentoDAO;
import dto.HorarioFuncionamentoDTO;
import entidade.HorarioFuncionamento;
import fachada.IHorarioFuncionamentoFachada;

@Stateless
@Remote(IHorarioFuncionamentoFachada.class)
public class HorarioFuncionamentoSB implements IHorarioFuncionamentoFachada{
	
	@EJB
	HorarioFuncionamentoDAO horarioFuncionamentoDAO;
	
	@Override
	public void salvar(HorarioFuncionamentoDTO horarioFuncionamentoDTO) 
			throws EntidadeAtributoIncompletoException, IntervaloHorarioInvalidoException, EntidadeJaCadastradaException {
		
		if(HorarioFuncionamentoAtributoValidador.validarAtributosPreenchidosEntidade(
				horarioFuncionamentoDTO))
		{
			if(buscar(horarioFuncionamentoDTO.getIdHorarioFuncionamento()) == null)
			{
				if(horarioFuncionamentoDTO.getHorarioFechamento().getTime() <
						horarioFuncionamentoDTO.getHorarioAbertura().getTime())
				{
					this.horarioFuncionamentoDAO.salvar(
							HorarioFuncionamentoConversorDTO.converterDTOEmEntidade(
									horarioFuncionamentoDTO));
				}
				else
				{
					throw new IntervaloHorarioInvalidoException();
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(HorarioFuncionamento.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(HorarioFuncionamento.class);
		}
	}

	@Override
	public void remover(long horarioFuncionamentoId) throws EntidadeInexistenteException {
		
		if(buscar(horarioFuncionamentoId) != null)
		{
			this.horarioFuncionamentoDAO.remover(gerarEntidadePorId(horarioFuncionamentoId));
		}
		else
		{
			throw new EntidadeInexistenteException(HorarioFuncionamento.class);
		}
	}

	@Override
	public HorarioFuncionamentoDTO atualizar(HorarioFuncionamentoDTO horarioFuncionamentoDTO) 
			throws EntidadeInexistenteException, IntervaloHorarioInvalidoException, EntidadeAtributoIncompletoException {
		
		if(buscar(horarioFuncionamentoDTO.getIdHorarioFuncionamento()) != null)
		{
			if(HorarioFuncionamentoAtributoValidador.validarAtributosPreenchidosEntidade(horarioFuncionamentoDTO))
			{
				if(horarioFuncionamentoDTO.getHorarioFechamento().getTime() <
						horarioFuncionamentoDTO.getHorarioAbertura().getTime())
				{
					return HorarioFuncionamentoConversorDTO.converterEntidadeEmDTO(
							this.horarioFuncionamentoDAO.atualizar(
									HorarioFuncionamentoConversorDTO.converterDTOEmEntidadeComId(horarioFuncionamentoDTO))
							);
				}
				else
				{
					throw new IntervaloHorarioInvalidoException();
				}
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(HorarioFuncionamento.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(HorarioFuncionamento.class);
		}
	}

	@Override
	public HorarioFuncionamentoDTO buscar(long horarioFuncionamentoId) {
		return HorarioFuncionamentoConversorDTO.converterEntidadeEmDTOComId(
				this.horarioFuncionamentoDAO.buscar(horarioFuncionamentoId));
	}

	@Override
	public List<HorarioFuncionamentoDTO> buscarTodos() {
		return HorarioFuncionamentoConversorDTO.converterEntidadesListEmDTOsList(
					this.horarioFuncionamentoDAO.buscarTodos()
				);
	}
	
	private HorarioFuncionamento gerarEntidadePorId(long horarioFuncionamentoId)
	{
		HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
		horarioFuncionamento.setIdHorarioFuncionamento(horarioFuncionamentoId);
		return horarioFuncionamento;
	}

}
