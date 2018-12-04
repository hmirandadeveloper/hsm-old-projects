package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import constantes.EDiaSemana;
import dto.HorarioFuncionamentoDTO;
import entidade.HorarioFuncionamento;

public abstract class HorarioFuncionamentoConversorDTO {

	public static HorarioFuncionamento converterDTOEmEntidade(HorarioFuncionamentoDTO horarioFuncionamentoDTO)
	{	
		HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
		if(horarioFuncionamentoDTO != null)
		{
			horarioFuncionamento.setHorarioAbertura(horarioFuncionamentoDTO.getHorarioAbertura());
			horarioFuncionamento.setHorarioFechamento(horarioFuncionamentoDTO.getHorarioFechamento());
			horarioFuncionamento.setDiaSemana(horarioFuncionamentoDTO.getDiaSemana().name());
			horarioFuncionamento.setAtivo(horarioFuncionamentoDTO.getAtivo());
		}
		return horarioFuncionamento;
	}

	public static HorarioFuncionamento converterDTOEmEntidadeComId(HorarioFuncionamentoDTO horarioFuncionamentoDTO)
	{	
		HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
		if(horarioFuncionamentoDTO != null)
		{
			horarioFuncionamento.setHorarioAbertura(horarioFuncionamentoDTO.getHorarioAbertura());
			horarioFuncionamento.setHorarioFechamento(horarioFuncionamentoDTO.getHorarioFechamento());
			horarioFuncionamento.setDiaSemana(horarioFuncionamentoDTO.getDiaSemana().name());
			horarioFuncionamento.setAtivo(horarioFuncionamentoDTO.getAtivo());
			horarioFuncionamento.setIdHorarioFuncionamento(horarioFuncionamentoDTO.getIdHorarioFuncionamento());
		}
		return horarioFuncionamento;
	}

	public static HorarioFuncionamentoDTO converterEntidadeEmDTO(HorarioFuncionamento horarioFuncionamento)
	{	
		HorarioFuncionamentoDTO horarioFuncionamentoDTO = new HorarioFuncionamentoDTO();
		if(horarioFuncionamento != null)
		{
			horarioFuncionamentoDTO.setHorarioAbertura(horarioFuncionamento.getHorarioAbertura());
			horarioFuncionamentoDTO.setHorarioFechamento(horarioFuncionamento.getHorarioFechamento());
			horarioFuncionamentoDTO.setDiaSemana(EDiaSemana.valueOf(horarioFuncionamento.getDiaSemana()));
			horarioFuncionamentoDTO.setAtivo(horarioFuncionamento.getAtivo());
		}
		return horarioFuncionamentoDTO;
	}

	public static HorarioFuncionamentoDTO converterEntidadeEmDTOComId(HorarioFuncionamento horarioFuncionamento)
	{	
		HorarioFuncionamentoDTO horarioFuncionamentoDTO = new HorarioFuncionamentoDTO();
		if(horarioFuncionamento != null)
		{
			horarioFuncionamentoDTO.setHorarioAbertura(horarioFuncionamento.getHorarioAbertura());
			horarioFuncionamentoDTO.setHorarioFechamento(horarioFuncionamento.getHorarioFechamento());
			horarioFuncionamentoDTO.setDiaSemana(EDiaSemana.valueOf(horarioFuncionamento.getDiaSemana()));
			horarioFuncionamentoDTO.setAtivo(horarioFuncionamento.getAtivo());
			horarioFuncionamentoDTO.setIdHorarioFuncionamento(horarioFuncionamento.getIdHorarioFuncionamento());
		}
		return horarioFuncionamentoDTO;
	}

	public static List<HorarioFuncionamentoDTO> converterEntidadesSetEmDTOsList(Set<HorarioFuncionamento> horarioFuncionamentosSet)
	{
		List<HorarioFuncionamentoDTO> horarioFuncionamentosDTOList = new ArrayList<HorarioFuncionamentoDTO>();
		if(horarioFuncionamentosSet != null)
		{
			for(HorarioFuncionamento horarioFuncionamento : horarioFuncionamentosSet)
			{
				horarioFuncionamentosDTOList.add(converterEntidadeEmDTO(horarioFuncionamento));
			}
		}
		return horarioFuncionamentosDTOList;
	}

	public static Set<HorarioFuncionamento> converterDTOsListEmEntidadesSet(List<HorarioFuncionamentoDTO> horarioFuncionamentosDTOList)
	{
		Set<HorarioFuncionamento> horarioFuncionamentosSet = new HashSet<HorarioFuncionamento>(0);
		if(horarioFuncionamentosDTOList != null)
		{
			for(HorarioFuncionamentoDTO horarioFuncionamentoDTO : horarioFuncionamentosDTOList)
			{
				horarioFuncionamentosSet.add(converterDTOEmEntidadeComId(horarioFuncionamentoDTO));
			}
		}
		return horarioFuncionamentosSet;
	}

	public static List<HorarioFuncionamentoDTO> converterEntidadesListEmDTOsList(List<HorarioFuncionamento> horarioFuncionamentosList)
	{
		List<HorarioFuncionamentoDTO> horarioFuncionamentosDTOList = new ArrayList<HorarioFuncionamentoDTO>();
		if(horarioFuncionamentosList != null)
		{
			for(HorarioFuncionamento horarioFuncionamento : horarioFuncionamentosList)
			{
				horarioFuncionamentosDTOList.add(converterEntidadeEmDTO(horarioFuncionamento));
			}
		}
		return horarioFuncionamentosDTOList;
	}
}
