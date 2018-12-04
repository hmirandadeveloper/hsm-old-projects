package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import constantes.EDiaSemana;
import constantes.ETipoUsuario;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.HorarioFuncionamentoDeliveryConversorDTO;
import negocio.util.validador.entidade.HorarioFuncionamentoDeliveryAtributoValidador;
import persistencia.HorarioFuncionamentoDeliveryDAO;
import dto.HorarioFuncionamentoDeliveryDTO;
import entidade.HorarioFuncionamentoDelivery;
import fachada.IHorarioFuncionamentoDeliveryFachada;

@Stateless
@Remote(IHorarioFuncionamentoDeliveryFachada.class)
public class HorarioFuncionamentoDeliverySB implements IHorarioFuncionamentoDeliveryFachada{
	
	@EJB
	HorarioFuncionamentoDeliveryDAO horarioFuncionamentoDeliveryDAO;
	
	@Override
	public void salvar(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO) throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		if(HorarioFuncionamentoDeliveryAtributoValidador.validarAtributosPreenchidosEntidade(horarioFuncionamentoDeliveryDTO))
		{
			System.out.println("Iniciando Busca..." + horarioFuncionamentoDeliveryDTO.getIdHorarioFuncionamentoDelivery());
			if(buscarPorDia(horarioFuncionamentoDeliveryDTO.getEstabelecimentoDTO().getIdEstabelecimento(), 
					horarioFuncionamentoDeliveryDTO.getHorarioFuncionamento().getDiaSemana()).getIdHorarioFuncionamentoDelivery() == null)
			{
				horarioFuncionamentoDeliveryDTO.getHorarioFuncionamento().setAtivo(true);
				horarioFuncionamentoDeliveryDTO.setDisponivel(true);
				System.out.println("Iniciando persistência...");
			this.horarioFuncionamentoDeliveryDAO.salvar(
					HorarioFuncionamentoDeliveryConversorDTO.
					converterDTOEmEntidade(horarioFuncionamentoDeliveryDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(HorarioFuncionamentoDelivery.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(HorarioFuncionamentoDelivery.class);
		}
	}

	@Override
	public void remover(long horarioFuncionamentoDeliveryId, ETipoUsuario tipoUsuario) 
			throws EntidadeInexistenteException {
		if(buscar(horarioFuncionamentoDeliveryId) != null)
		{
			this.horarioFuncionamentoDeliveryDAO.remover(gerarEntidadePorId(horarioFuncionamentoDeliveryId));
		}
		else
		{
			throw new EntidadeInexistenteException(HorarioFuncionamentoDelivery.class);
		}
	}

	@Override
	public HorarioFuncionamentoDeliveryDTO atualizar(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO) 
			throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		
		if(buscar(horarioFuncionamentoDeliveryDTO.getIdHorarioFuncionamentoDelivery()) != null)
		{
			if(HorarioFuncionamentoDeliveryAtributoValidador.validarAtributosPreenchidosEntidade(horarioFuncionamentoDeliveryDTO))
			{
				return HorarioFuncionamentoDeliveryConversorDTO.converterEntidadeEmDTO(
						this.horarioFuncionamentoDeliveryDAO.atualizar(
								HorarioFuncionamentoDeliveryConversorDTO.converterDTOEmEntidadeComId(horarioFuncionamentoDeliveryDTO))
						);
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(HorarioFuncionamentoDelivery.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(HorarioFuncionamentoDelivery.class);
		}
	}

	@Override
	public HorarioFuncionamentoDeliveryDTO buscar(long horarioFuncionamentoDeliveryId) {
		return HorarioFuncionamentoDeliveryConversorDTO.converterEntidadeEmDTOComId(
				this.horarioFuncionamentoDeliveryDAO.buscar(horarioFuncionamentoDeliveryId));
	}
	
	@Override
	public HorarioFuncionamentoDeliveryDTO buscarPorDia(Long idEstabelecimento, EDiaSemana diaSemana) {
		return HorarioFuncionamentoDeliveryConversorDTO.converterEntidadeEmDTOComId(
				this.horarioFuncionamentoDeliveryDAO.buscarHorarioFuncionamentoDeliveryPeloEstabelecimentoEDia(idEstabelecimento, diaSemana.name()));
	}

	@Override
	public List<HorarioFuncionamentoDeliveryDTO> buscarTodos(Long idEstabelecimento) {
		return HorarioFuncionamentoDeliveryConversorDTO.converterEntidadesListEmDTOsList(
					this.horarioFuncionamentoDeliveryDAO.buscarHorarioFuncionamentoDeliverysPeloEstabelecimento(idEstabelecimento)
				);
	}
	
	private HorarioFuncionamentoDelivery gerarEntidadePorId(long horarioFuncionamentoDeliveryId)
	{
		HorarioFuncionamentoDelivery horarioFuncionamentoDelivery = new HorarioFuncionamentoDelivery();
		horarioFuncionamentoDelivery.setIdHorarioFuncionamentoDelivery(horarioFuncionamentoDeliveryId);
		return horarioFuncionamentoDelivery;
	}

}
