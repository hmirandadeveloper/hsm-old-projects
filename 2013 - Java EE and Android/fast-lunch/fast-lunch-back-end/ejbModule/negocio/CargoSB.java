package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.CargoConversorDTO;
import negocio.util.validador.entidade.CargoAtributoValidador;
import persistencia.CargoDAO;
import constantes.ETipoUsuario;
import dto.CargoDTO;
import entidade.Cargo;
import fachada.ICargoFachada;

@Stateless
@Remote(ICargoFachada.class)
public class CargoSB implements ICargoFachada{
	
	@EJB
	CargoDAO cargoDAO;
	
	@Override
	public void salvar(CargoDTO cargoDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		if(CargoAtributoValidador.validarAtributosPreenchidosEntidade(cargoDTO))
		{
			
			CargoDTO cargoCompartivo = buscarCargoPelaDescricao(cargoDTO.getDescricao(), cargoDTO.getEstabelecimento().getIdEstabelecimento());

			if(cargoCompartivo == null)
			{
				cargoDTO.setAtivo(true);
				this.cargoDAO.salvar(CargoConversorDTO.converterDTOEmEntidadeComId(cargoDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(Cargo.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Cargo.class);
		}
	}

	@Override
	public void remover(long cargoId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		
		if(buscar(cargoId) != null)
		{
			if(tipoUsuario == ETipoUsuario.ADMIN)
			{
				this.cargoDAO.remover(gerarEntidadePorId(cargoId));
			}
			else
			{
				Cargo cargo = this.cargoDAO.buscar(cargoId);
				cargo.setAtivo(false);
				this.cargoDAO.atualizar(cargo);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Cargo.class);
		}
	}

	@Override
	public CargoDTO atualizar(CargoDTO cargoDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		
		if(CargoAtributoValidador.validarAtributosPreenchidosEntidade(cargoDTO))
		{
			if(buscar(cargoDTO.getIdCargo()) != null)
			{
				return CargoConversorDTO.converterEntidadeEmDTO(
						this.cargoDAO.atualizar(
								CargoConversorDTO.converterDTOEmEntidadeComId(cargoDTO))
						);
			}
			else
			{
				throw new EntidadeInexistenteException(Cargo.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Cargo.class);
		}
	}

	@Override
	public CargoDTO buscar(long cargoId) {
		return CargoConversorDTO.converterEntidadeEmDTOComId(
				this.cargoDAO.buscar(cargoId));
	}

	@Override
	public List<CargoDTO> buscarTodos(Long id_estabelecimento) {
		System.out.println("ID do Estabel.: " + id_estabelecimento);
		return CargoConversorDTO.converterEntidadesListEmDTOsList(
					this.cargoDAO.buscarCargosPeloEstabelecimento(id_estabelecimento)
				);
	}
	
	private Cargo gerarEntidadePorId(long cargoId)
	{
		Cargo cargo = new Cargo();
		cargo.setIdCargo(cargoId);
		return cargo;
	}

	@Override
	public CargoDTO buscarCargoPelaDescricao(String descricao, Long idEstabelecimento) {
		
		CargoDTO cargoDTOBusca = CargoConversorDTO.converterEntidadeEmDTOComId(
				this.cargoDAO.buscarCargoPelaDescricao(descricao, idEstabelecimento));
		
		if(cargoDTOBusca != null ? cargoDTOBusca.getDescricao() == null : false)
		{
			cargoDTOBusca = null;
		}
		return cargoDTOBusca;
	}

}
