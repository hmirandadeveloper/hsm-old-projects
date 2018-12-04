package negocio.util.validador.entidade;

import java.util.List;

import dto.FidelidadeTrocaDTO;

public abstract class FidelidadeTrocaAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(FidelidadeTrocaDTO fidelidadeTrocaDTO)
	{
		boolean atributosCorretos = true;

		if(fidelidadeTrocaDTO == null)
		{
			return false;
		}
		
		if(!ProdutoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						fidelidadeTrocaDTO.getProduto()))
		{
			atributosCorretos = false;
		}
		if(!ClienteAtributoValidador.
				validarAtributosPreenchidosEntidade(
						fidelidadeTrocaDTO.getCliente()))
		{
			atributosCorretos = false;
		}
		if(fidelidadeTrocaDTO.getDataHora() == null)
		{
			atributosCorretos = false;
		}
		
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs)
	{
		boolean atributosCorretos = true;
		
		for(FidelidadeTrocaDTO fidelidadeTrocaDTO : fidelidadeTrocaDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(fidelidadeTrocaDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
