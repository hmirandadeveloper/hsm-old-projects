package negocio.util.validador.entidade;

import java.util.List;

import dto.HorarioFuncionamentoEstabelecimentoDTO;

public abstract class HorarioFuncionamentoEstabelecimentoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO)
	{
		boolean atributosCorretos = true;
		
		if(horarioFuncionamentoEstabelecimentoDTO == null)
		{
			return false;
		}
		
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						horarioFuncionamentoEstabelecimentoDTO.getEstabelecimento()))
		{
			atributosCorretos = false;
		}
		if(!HorarioFuncionamentoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						horarioFuncionamentoEstabelecimentoDTO.getHorarioFuncionamento()))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO : horarioFuncionamentoEstabelecimentoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(horarioFuncionamentoEstabelecimentoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
