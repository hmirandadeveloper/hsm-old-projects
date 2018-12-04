package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.CargoDTO;
import entidade.Cargo;

public abstract class CargoConversorDTO {

	public static Cargo converterDTOEmEntidade(CargoDTO cargoDTO)
	{	
		Cargo cargo = new Cargo();
		if(cargoDTO != null)
		{
			cargo.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterDTOEmEntidade(cargoDTO.getEstabelecimento()));
			cargo.setDescricao(cargoDTO.getDescricao());
			cargo.setAtivo(cargoDTO.getAtivo());
		}
		return cargo;
	}

	public static Cargo converterDTOEmEntidadeComId(CargoDTO cargoDTO)
	{	
		Cargo cargo = new Cargo();
		if(cargoDTO != null)
		{
			cargo.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(cargoDTO.getEstabelecimento()));
			cargo.setDescricao(cargoDTO.getDescricao());
			cargo.setAtivo(cargoDTO.getAtivo());
			cargo.setIdCargo(cargoDTO.getIdCargo());
		}
		return cargo;
	}

	public static CargoDTO converterEntidadeEmDTO(Cargo cargo)
	{	
		CargoDTO cargoDTO = new CargoDTO();
		if(cargo != null)
		{
			cargoDTO.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(cargo.getEstabelecimento()));
			cargoDTO.setDescricao(cargo.getDescricao());
			cargoDTO.setAtivo(cargo.getAtivo());
		}
		return cargoDTO;
	}

	public static CargoDTO converterEntidadeEmDTOComId(Cargo cargo)
	{	
		CargoDTO cargoDTO = new CargoDTO();
		if(cargo != null)
		{
			cargoDTO.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(cargo.getEstabelecimento()));
			cargoDTO.setDescricao(cargo.getDescricao());
			cargoDTO.setAtivo(cargo.getAtivo());
			cargoDTO.setIdCargo(cargo.getIdCargo());
		}
		return cargoDTO;
	}

	public static List<CargoDTO> converterEntidadesSetEmDTOsList(Set<Cargo> cargosSet)
	{
		List<CargoDTO> cargosDTOList = new ArrayList<CargoDTO>();
		if(cargosSet != null)
		{
			for(Cargo cargo : cargosSet)
			{
				cargosDTOList.add(converterEntidadeEmDTOComId(cargo));
			}
		}
		return cargosDTOList;
	}

	public static Set<Cargo> converterDTOsListEmEntidadesSet(List<CargoDTO> cargosDTOList)
	{
		Set<Cargo> cargosSet = new HashSet<Cargo>(0);
		if(cargosDTOList != null)
		{
			for(CargoDTO cargoDTO : cargosDTOList)
			{
				cargosSet.add(converterDTOEmEntidade(cargoDTO));
			}
		}
		return cargosSet;
	}

	public static List<CargoDTO> converterEntidadesListEmDTOsList(List<Cargo> cargosList)
	{
		List<CargoDTO> cargosDTOList = new ArrayList<CargoDTO>();
		if(cargosList != null)
		{
			for(Cargo cargo : cargosList)
			{
				cargosDTOList.add(converterEntidadeEmDTOComId(cargo));
			}
		}
		return cargosDTOList;
	}
}
