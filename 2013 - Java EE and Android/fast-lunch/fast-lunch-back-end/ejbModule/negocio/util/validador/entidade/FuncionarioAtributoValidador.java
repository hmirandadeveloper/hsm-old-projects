package negocio.util.validador.entidade;

import java.util.List;

import dto.FuncionarioDTO;

public abstract class FuncionarioAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(FuncionarioDTO funcionarioDTO)
	{
		boolean atributosCorretos = true;
		
		if(funcionarioDTO == null)
		{
			return false;
		}
		
		if(!UsuarioAtributoValidador.
				validarAtributosPreenchidosEntidade(funcionarioDTO.getUsuario()))
		{
			atributosCorretos = false;
		}
		if(!CargoAtributoValidador.
				validarAtributosPreenchidosEntidade(funcionarioDTO.getCargo()))
		{
			atributosCorretos = false;
		}
		if(funcionarioDTO.getNome() == null || funcionarioDTO.getNome().equals(""))
		{
			atributosCorretos = false;
		}
		if(funcionarioDTO.getSobrenome() == null || funcionarioDTO.getSobrenome().equals(""))
		{
			atributosCorretos = false;
		}
		if(funcionarioDTO.getMatricula() == null || funcionarioDTO.getMatricula().equals(""))
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<FuncionarioDTO> funcionarioDTOs)
	{
		boolean atributosCorretos = true;
		
		for(FuncionarioDTO funcionarioDTO : funcionarioDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(funcionarioDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
