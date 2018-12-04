package negocio.util.validador.entidade;

import java.util.List;

import dto.EnderecoDTO;


public abstract class EnderecoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(EnderecoDTO enderecoDTO)
	{
		boolean atributosCorretos = true;
		
		if(enderecoDTO == null)
		{
			return false;
		}
		
		if(enderecoDTO.getLogradouro() == null || enderecoDTO.getLogradouro().equals(""))
		{
			atributosCorretos = false;
		}
		if(enderecoDTO.getNumero() == 0)
		{
			atributosCorretos = false;
		}
		if(enderecoDTO.getBairro() == null || enderecoDTO.getBairro().equals(""))
		{
			atributosCorretos = false;
		}
		if(enderecoDTO.getCidade() == null || enderecoDTO.getCidade().equals(""))
		{
			atributosCorretos = false;
		}
		if(enderecoDTO.getCep() == null || enderecoDTO.getCep().equals(""))
		{
			atributosCorretos = false;
		}
		if(enderecoDTO.getUf() == null || enderecoDTO.getUf().equals(""))
		{
			atributosCorretos = false;
		}

				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<EnderecoDTO> enderecoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(EnderecoDTO enderecoDTO : enderecoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(enderecoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
