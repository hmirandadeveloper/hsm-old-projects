package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.HorarioFuncionamentoDeliveryDTO;
import entidade.HorarioFuncionamentoDelivery;

public abstract class HorarioFuncionamentoDeliveryConversorDTO {

	public static HorarioFuncionamentoDelivery converterDTOEmEntidade(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO)
	{	
		HorarioFuncionamentoDelivery horarioFuncionamentoDelivery = new HorarioFuncionamentoDelivery();
		if(horarioFuncionamentoDeliveryDTO != null)
		{
			horarioFuncionamentoDelivery.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.
					converterDTOEmEntidade(horarioFuncionamentoDeliveryDTO.getHorarioFuncionamento()));
			horarioFuncionamentoDelivery.setEstabelecimento(EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(horarioFuncionamentoDeliveryDTO.getEstabelecimentoDTO()));
			horarioFuncionamentoDelivery.setDisponivel(horarioFuncionamentoDeliveryDTO.getDisponivel());
		}
		return horarioFuncionamentoDelivery;
	}

	public static HorarioFuncionamentoDelivery converterDTOEmEntidadeComId(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO)
	{	
		HorarioFuncionamentoDelivery horarioFuncionamentoDelivery = new HorarioFuncionamentoDelivery();
		if(horarioFuncionamentoDeliveryDTO != null)
		{
			horarioFuncionamentoDelivery.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.
					converterDTOEmEntidade(horarioFuncionamentoDeliveryDTO.getHorarioFuncionamento()));
			horarioFuncionamentoDelivery.setEstabelecimento(EstabelecimentoConversorDTO.
					converterDTOEmEntidade(horarioFuncionamentoDeliveryDTO.getEstabelecimentoDTO()));
			horarioFuncionamentoDelivery.setDisponivel(horarioFuncionamentoDeliveryDTO.getDisponivel());
			horarioFuncionamentoDelivery.setIdHorarioFuncionamentoDelivery(horarioFuncionamentoDeliveryDTO.
					getIdHorarioFuncionamentoDelivery());
		}
		return horarioFuncionamentoDelivery;
	}

	public static HorarioFuncionamentoDeliveryDTO converterEntidadeEmDTO(HorarioFuncionamentoDelivery horarioFuncionamentoDelivery)
	{	
		HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO = new HorarioFuncionamentoDeliveryDTO();
		if(horarioFuncionamentoDelivery != null)
		{
			horarioFuncionamentoDeliveryDTO.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.
					converterEntidadeEmDTO(horarioFuncionamentoDelivery.getHorarioFuncionamento()));
			horarioFuncionamentoDeliveryDTO.setEstabelecimentoDTO(EstabelecimentoConversorDTO.
					converterEntidadeEmDTO(horarioFuncionamentoDelivery.getEstabelecimento()));
			horarioFuncionamentoDeliveryDTO.setDisponivel(horarioFuncionamentoDelivery.getDisponivel());
		}
		return horarioFuncionamentoDeliveryDTO;
	}

	public static HorarioFuncionamentoDeliveryDTO converterEntidadeEmDTOComId(HorarioFuncionamentoDelivery horarioFuncionamentoDelivery)
	{	
		HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO = new HorarioFuncionamentoDeliveryDTO();
		if(horarioFuncionamentoDelivery != null)
		{
			horarioFuncionamentoDeliveryDTO.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.
					converterEntidadeEmDTO(horarioFuncionamentoDelivery.getHorarioFuncionamento()));
			horarioFuncionamentoDeliveryDTO.setEstabelecimentoDTO(EstabelecimentoConversorDTO.
					converterEntidadeEmDTO(horarioFuncionamentoDelivery.getEstabelecimento()));
			horarioFuncionamentoDeliveryDTO.setDisponivel(horarioFuncionamentoDelivery.getDisponivel());
			horarioFuncionamentoDeliveryDTO.setIdHorarioFuncionamentoDelivery(horarioFuncionamentoDelivery.
					getIdHorarioFuncionamentoDelivery());
		}
		return horarioFuncionamentoDeliveryDTO;
	}

	public static List<HorarioFuncionamentoDeliveryDTO> converterEntidadesSetEmDTOsList(Set<HorarioFuncionamentoDelivery> horarioFuncionamentoDeliverysSet)
	{
		List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliverysDTOList = new ArrayList<HorarioFuncionamentoDeliveryDTO>();
		if(horarioFuncionamentoDeliverysSet != null)
		{
			for(HorarioFuncionamentoDelivery horarioFuncionamentoDelivery : horarioFuncionamentoDeliverysSet)
			{
				horarioFuncionamentoDeliverysDTOList.add(converterEntidadeEmDTO(horarioFuncionamentoDelivery));
			}
		}
		return horarioFuncionamentoDeliverysDTOList;
	}

	public static Set<HorarioFuncionamentoDelivery> converterDTOsListEmEntidadesSet(List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliverysDTOList)
	{
		Set<HorarioFuncionamentoDelivery> horarioFuncionamentoDeliverysSet = new HashSet<HorarioFuncionamentoDelivery>(0);
		if(horarioFuncionamentoDeliverysDTOList != null)
		{
			for(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO : horarioFuncionamentoDeliverysDTOList)
			{
				horarioFuncionamentoDeliverysSet.add(converterDTOEmEntidadeComId(horarioFuncionamentoDeliveryDTO));
			}
		}
		return horarioFuncionamentoDeliverysSet;
	}

	public static List<HorarioFuncionamentoDeliveryDTO> converterEntidadesListEmDTOsList(List<HorarioFuncionamentoDelivery> horarioFuncionamentoDeliverysList)
	{
		List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliverysDTOList = new ArrayList<HorarioFuncionamentoDeliveryDTO>();
		if(horarioFuncionamentoDeliverysList != null)
		{
			for(HorarioFuncionamentoDelivery horarioFuncionamentoDelivery : horarioFuncionamentoDeliverysList)
			{
				horarioFuncionamentoDeliverysDTOList.add(converterEntidadeEmDTO(horarioFuncionamentoDelivery));
			}
		}
		return horarioFuncionamentoDeliverysDTOList;
	}
}
