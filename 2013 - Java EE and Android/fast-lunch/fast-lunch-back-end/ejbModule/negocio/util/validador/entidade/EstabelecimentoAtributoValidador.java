package negocio.util.validador.entidade;

import java.util.List;

import dto.EstabelecimentoDTO;

public abstract class EstabelecimentoAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(EstabelecimentoDTO estabelecimentoDTO)
	{
		boolean atributosCorretos = true;

		if(estabelecimentoDTO == null)
		{
			return false;
		}
		
		if(estabelecimentoDTO.getTipoEstabelecimento() == 'F' && 
				!EstabelecimentoAtributoValidador.validarAtributosPreenchidosEntidade(
						estabelecimentoDTO.getEstabelecimento()))
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getNomeFantasia() == null || 
				estabelecimentoDTO.getNomeFantasia().equals(""))
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getRazaoSocial() == null || 
				estabelecimentoDTO.getRazaoSocial().equals(""))
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getCnpj() == null || 
				estabelecimentoDTO.getCnpj().equals(""))
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getTelefone() == null || 
				estabelecimentoDTO.getTelefone().equals(""))
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getEmail() == null || 
				estabelecimentoDTO.getEmail().equals(""))
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getTipoEstabelecimento() != 'M' && 
				estabelecimentoDTO.getTipoEstabelecimento() != 'F')
		{
			atributosCorretos = false;
		}
		if(estabelecimentoDTO.getEnderecoDTO() == null)
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<EstabelecimentoDTO> estabelecimentoDTOs)
	{
		boolean atributosCorretos = true;
		
		for(EstabelecimentoDTO estabelecimentoDTO : estabelecimentoDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(estabelecimentoDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
