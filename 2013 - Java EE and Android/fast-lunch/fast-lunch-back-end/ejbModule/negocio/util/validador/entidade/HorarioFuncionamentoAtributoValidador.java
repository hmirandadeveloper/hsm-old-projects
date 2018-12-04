package negocio.util.validador.entidade;

import java.util.List;

import dto.HorarioFuncionamentoDTO;

public abstract class HorarioFuncionamentoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(HorarioFuncionamentoDTO horarioFuncionamentoDTO)
	{
		boolean atributosCorretos = true;
		
		if(horarioFuncionamentoDTO == null)
		{
			return false;
		}
		
		if(horarioFuncionamentoDTO.getHorarioAbertura() == null)
		{
			atributosCorretos = false;
		}
		if(horarioFuncionamentoDTO.getHorarioFechamento() == null)
		{
			atributosCorretos = false;
		}
		if(horarioFuncionamentoDTO.getDiaSemana() == null)
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<HorarioFuncionamentoDTO> horarioFuncionamentoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(HorarioFuncionamentoDTO horarioFuncionamentoDTO : horarioFuncionamentoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(horarioFuncionamentoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
