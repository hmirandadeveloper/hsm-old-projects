package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.HorarioFuncionamentoEstabelecimentoConversorDTO;
import negocio.util.validador.entidade.HorarioFuncionamentoEstabelecimentoAtributoValidador;
import persistencia.HorarioFuncionamentoEstabelecimentoDAO;
import constantes.EDiaSemana;
import constantes.ETipoUsuario;
import dto.HorarioFuncionamentoEstabelecimentoDTO;
import entidade.HorarioFuncionamentoEstabelecimento;
import fachada.IHorarioFuncionamentoEstabelecimentoFachada;

@Stateless
@Remote(IHorarioFuncionamentoEstabelecimentoFachada.class)
public class HorarioFuncionamentoEstabelecimentoSB implements IHorarioFuncionamentoEstabelecimentoFachada{
	
	@EJB
	HorarioFuncionamentoEstabelecimentoDAO horarioFuncionamentoEstabelecimentoDAO;
	
	@Override
	public void salvar(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		if(HorarioFuncionamentoEstabelecimentoAtributoValidador.validarAtributosPreenchidosEntidade(horarioFuncionamentoEstabelecimentoDTO))
		{
			if(buscarPorDia(horarioFuncionamentoEstabelecimentoDTO.getEstabelecimento().getIdEstabelecimento(), 
					horarioFuncionamentoEstabelecimentoDTO.getHorarioFuncionamento().getDiaSemana()).getIdHorarioFuncionamentoEstabelecimento() == null)
			{
				horarioFuncionamentoEstabelecimentoDTO.setDisponivel(true);
				horarioFuncionamentoEstabelecimentoDTO.getHorarioFuncionamento().setAtivo(true);
			this.horarioFuncionamentoEstabelecimentoDAO.salvar(
					HorarioFuncionamentoEstabelecimentoConversorDTO.
					converterDTOEmEntidade(horarioFuncionamentoEstabelecimentoDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(HorarioFuncionamentoEstabelecimento.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(HorarioFuncionamentoEstabelecimento.class);
		}
	}

	@Override
	public void remover(long horarioFuncionamentoEstabelecimentoId, ETipoUsuario tipoUsuario) 
			throws EntidadeInexistenteException {
		if(buscar(horarioFuncionamentoEstabelecimentoId) != null)
		{
			this.horarioFuncionamentoEstabelecimentoDAO.remover(gerarEntidadePorId(horarioFuncionamentoEstabelecimentoId));
		}
		else
		{
			throw new EntidadeInexistenteException(HorarioFuncionamentoEstabelecimento.class);
		}
	}

	@Override
	public HorarioFuncionamentoEstabelecimentoDTO atualizar(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		if(buscar(horarioFuncionamentoEstabelecimentoDTO.getIdHorarioFuncionamentoEstabelecimento()) != null)
		{
			if(HorarioFuncionamentoEstabelecimentoAtributoValidador.validarAtributosPreenchidosEntidade(horarioFuncionamentoEstabelecimentoDTO))
			{
				return HorarioFuncionamentoEstabelecimentoConversorDTO.converterEntidadeEmDTO(
						this.horarioFuncionamentoEstabelecimentoDAO.atualizar(
								HorarioFuncionamentoEstabelecimentoConversorDTO.converterDTOEmEntidadeComId(horarioFuncionamentoEstabelecimentoDTO))
						);
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(HorarioFuncionamentoEstabelecimento.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(HorarioFuncionamentoEstabelecimento.class);
		}
	}

	@Override
	public HorarioFuncionamentoEstabelecimentoDTO buscar(long horarioFuncionamentoEstabelecimentoId) {
		return HorarioFuncionamentoEstabelecimentoConversorDTO.converterEntidadeEmDTOComId(
				this.horarioFuncionamentoEstabelecimentoDAO.buscar(horarioFuncionamentoEstabelecimentoId));
	}

	@Override
	public List<HorarioFuncionamentoEstabelecimentoDTO> buscarTodos(Long idEstabelecimento) {
		return HorarioFuncionamentoEstabelecimentoConversorDTO.converterEntidadesListEmDTOsList(
					this.horarioFuncionamentoEstabelecimentoDAO.buscarHorarioFuncionamentoEstabelecimentosPeloEstabelecimento(idEstabelecimento)
				);
	}
	
	@Override
	public HorarioFuncionamentoEstabelecimentoDTO buscarPorDia(Long idEstabelecimento, EDiaSemana diaSemana) {
		return HorarioFuncionamentoEstabelecimentoConversorDTO.converterEntidadeEmDTOComId(
				this.horarioFuncionamentoEstabelecimentoDAO.buscarHorarioFuncionamentoEstabelecimentoPeloEstabelecimentoEDia(idEstabelecimento, diaSemana.name()));
	}
	
	private HorarioFuncionamentoEstabelecimento gerarEntidadePorId(long horarioFuncionamentoEstabelecimentoId)
	{
		HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento = new HorarioFuncionamentoEstabelecimento();
		horarioFuncionamentoEstabelecimento.setIdHorarioFuncionamentoEstabelecimento(horarioFuncionamentoEstabelecimentoId);
		return horarioFuncionamentoEstabelecimento;
	}

}
