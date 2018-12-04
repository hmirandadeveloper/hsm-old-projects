package negocio.util.validador.entidade;

import java.util.List;

import dto.HorarioFuncionamentoDeliveryDTO;

public abstract class HorarioFuncionamentoDeliveryAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO)
	{
		boolean atributosCorretos = true;

		if(horarioFuncionamentoDeliveryDTO == null)
		{
			return false;
		}
		
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						horarioFuncionamentoDeliveryDTO.getEstabelecimentoDTO()))
		{
			atributosCorretos = false;
		}
		if(!HorarioFuncionamentoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						horarioFuncionamentoDeliveryDTO.getHorarioFuncionamento()))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs)
	{
		boolean atributosCorretos = true;
		
		for(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO : horarioFuncionamentoDeliveryDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(horarioFuncionamentoDeliveryDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
