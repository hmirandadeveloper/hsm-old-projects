package negocio.util.validador.entidade;

import java.util.List;

import dto.FreteDTO;

public abstract class FreteAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(FreteDTO freteDTO)
	{
		boolean atributosCorretos = true;
		
		if(freteDTO == null)
		{
			return false;
		}
		
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						freteDTO.getEstabelecimentoDTO()))
		{
			atributosCorretos = false;
		}
		if(freteDTO.getBairro() == null || 
				freteDTO.getBairro().equals(""))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<FreteDTO> freteDTOs)
	{
		boolean atributosCorretos = true;
		
		for(FreteDTO freteDTO : freteDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(freteDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
