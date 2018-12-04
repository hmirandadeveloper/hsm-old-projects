package negocio.util.validador.entidade;

import java.util.List;

import dto.UsuarioDTO;

public abstract class UsuarioAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(UsuarioDTO usuarioDTO)
	{
		boolean atributosCorretos = true;
		
		if(usuarioDTO == null)
		{
			return false;
		}
		
		if(usuarioDTO.getLogin() == null || usuarioDTO.getLogin().equals(""))
		{
			atributosCorretos = false;
		}
		if(usuarioDTO.getSenha() == null || usuarioDTO.getSenha().equals(""))
		{
			atributosCorretos = false;
		}
		if(usuarioDTO.getTipoUsuario() == null)
		{
			atributosCorretos = false;
		}
				
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<UsuarioDTO> usuarioDTOs)
	{
		boolean atributosCorretos = true;
		
		for(UsuarioDTO usuarioDTO : usuarioDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(usuarioDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
