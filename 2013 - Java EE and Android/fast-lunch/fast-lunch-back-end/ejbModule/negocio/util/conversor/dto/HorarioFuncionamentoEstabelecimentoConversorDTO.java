package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.HorarioFuncionamentoEstabelecimentoDTO;
import entidade.HorarioFuncionamentoEstabelecimento;

public abstract class HorarioFuncionamentoEstabelecimentoConversorDTO {

	public static HorarioFuncionamentoEstabelecimento converterDTOEmEntidade(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO)
	{	
		HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento = 
				new HorarioFuncionamentoEstabelecimento();
		if(horarioFuncionamentoEstabelecimentoDTO != null)
		{
			horarioFuncionamentoEstabelecimento.setEstabelecimento(
					EstabelecimentoConversorDTO.converterDTOEmEntidadeComId(
							horarioFuncionamentoEstabelecimentoDTO.getEstabelecimento()));
			horarioFuncionamentoEstabelecimento.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.converterDTOEmEntidade(
							horarioFuncionamentoEstabelecimentoDTO.getHorarioFuncionamento()));
			horarioFuncionamentoEstabelecimento.setDisponivel(horarioFuncionamentoEstabelecimentoDTO.isDisponivel());
		}
		return horarioFuncionamentoEstabelecimento;
	}

	public static HorarioFuncionamentoEstabelecimento converterDTOEmEntidadeComId(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO)
	{	
		HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento = 
				new HorarioFuncionamentoEstabelecimento();
		if(horarioFuncionamentoEstabelecimentoDTO != null)
		{
			horarioFuncionamentoEstabelecimento.setEstabelecimento(
					EstabelecimentoConversorDTO.converterDTOEmEntidadeComId(
							horarioFuncionamentoEstabelecimentoDTO.getEstabelecimento()));
			horarioFuncionamentoEstabelecimento.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.converterDTOEmEntidade(
							horarioFuncionamentoEstabelecimentoDTO.getHorarioFuncionamento()));
			horarioFuncionamentoEstabelecimento.setDisponivel(horarioFuncionamentoEstabelecimentoDTO.isDisponivel());
			horarioFuncionamentoEstabelecimento.setIdHorarioFuncionamentoEstabelecimento(
					horarioFuncionamentoEstabelecimentoDTO.getIdHorarioFuncionamentoEstabelecimento());
		}
		return horarioFuncionamentoEstabelecimento;
	}

	public static HorarioFuncionamentoEstabelecimentoDTO converterEntidadeEmDTO(HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento)
	{	
		HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO = 
				new HorarioFuncionamentoEstabelecimentoDTO();
		if(horarioFuncionamentoEstabelecimento != null)
		{
			horarioFuncionamentoEstabelecimentoDTO.setEstabelecimento(
					EstabelecimentoConversorDTO.converterEntidadeEmDTO(
							horarioFuncionamentoEstabelecimento.getEstabelecimento()));
			horarioFuncionamentoEstabelecimentoDTO.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.converterEntidadeEmDTO(
							horarioFuncionamentoEstabelecimento.getHorarioFuncionamento()));
			horarioFuncionamentoEstabelecimentoDTO.setDisponivel(horarioFuncionamentoEstabelecimento.isDisponivel());
		}
		return horarioFuncionamentoEstabelecimentoDTO;
	}

	public static HorarioFuncionamentoEstabelecimentoDTO converterEntidadeEmDTOComId(HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento)
	{	
		HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO = 
				new HorarioFuncionamentoEstabelecimentoDTO();
		if(horarioFuncionamentoEstabelecimento != null)
		{
			horarioFuncionamentoEstabelecimentoDTO.setEstabelecimento(
					EstabelecimentoConversorDTO.converterEntidadeEmDTOComId(
							horarioFuncionamentoEstabelecimento.getEstabelecimento()));
			horarioFuncionamentoEstabelecimentoDTO.setHorarioFuncionamento(
					HorarioFuncionamentoConversorDTO.converterEntidadeEmDTO(
							horarioFuncionamentoEstabelecimento.getHorarioFuncionamento()));
			horarioFuncionamentoEstabelecimentoDTO.setDisponivel(horarioFuncionamentoEstabelecimento.isDisponivel());
			horarioFuncionamentoEstabelecimentoDTO.setIdHorarioFuncionamentoEstabelecimento(
					horarioFuncionamentoEstabelecimento.getIdHorarioFuncionamentoEstabelecimento());
		}
		return horarioFuncionamentoEstabelecimentoDTO;
	}

	public static List<HorarioFuncionamentoEstabelecimentoDTO> converterEntidadesSetEmDTOsList(Set<HorarioFuncionamentoEstabelecimento> horarioFuncionamentoEstabelecimentosSet)
	{
		List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentosDTOList = new ArrayList<HorarioFuncionamentoEstabelecimentoDTO>();
		if(horarioFuncionamentoEstabelecimentosSet != null)
		{
			for(HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento : horarioFuncionamentoEstabelecimentosSet)
			{
				horarioFuncionamentoEstabelecimentosDTOList.add(converterEntidadeEmDTO(horarioFuncionamentoEstabelecimento));
			}
		}
		return horarioFuncionamentoEstabelecimentosDTOList;
	}

	public static Set<HorarioFuncionamentoEstabelecimento> converterDTOsListEmEntidadesSet(List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentosDTOList)
	{
		Set<HorarioFuncionamentoEstabelecimento> horarioFuncionamentoEstabelecimentosSet = new HashSet<HorarioFuncionamentoEstabelecimento>(0);
		if(horarioFuncionamentoEstabelecimentosDTOList != null)
		{
			for(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO : horarioFuncionamentoEstabelecimentosDTOList)
			{
				horarioFuncionamentoEstabelecimentosSet.add(converterDTOEmEntidadeComId(horarioFuncionamentoEstabelecimentoDTO));
			}
		}
		return horarioFuncionamentoEstabelecimentosSet;
	}

	public static List<HorarioFuncionamentoEstabelecimentoDTO> converterEntidadesListEmDTOsList(List<HorarioFuncionamentoEstabelecimento> horarioFuncionamentoEstabelecimentosList)
	{
		List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentosDTOList = new ArrayList<HorarioFuncionamentoEstabelecimentoDTO>();
		if(horarioFuncionamentoEstabelecimentosList != null)
		{
			for(HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento : horarioFuncionamentoEstabelecimentosList)
			{
				horarioFuncionamentoEstabelecimentosDTOList.add(converterEntidadeEmDTO(horarioFuncionamentoEstabelecimento));
			}
		}
		return horarioFuncionamentoEstabelecimentosDTOList;
	}
}
