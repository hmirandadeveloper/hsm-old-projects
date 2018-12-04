package negocio.util.validador.entidade;

import java.util.List;

import dto.CargoDTO;

public abstract class CargoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(CargoDTO cargoDTO)
	{
		boolean atributosCorretos = true;
		
		if(cargoDTO == null)
		{
			return false;
		}
		
		if(!EstabelecimentoAtributoValidador.
				validarAtributosPreenchidosEntidade(
						cargoDTO.getEstabelecimento()))
		{
			atributosCorretos = false;
		}
		if(cargoDTO.getDescricao() == null || cargoDTO.getDescricao().equals(""))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<CargoDTO> cargoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(CargoDTO cargoDTO : cargoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(cargoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
