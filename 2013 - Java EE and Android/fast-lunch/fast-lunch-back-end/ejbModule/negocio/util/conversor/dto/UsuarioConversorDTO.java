package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import constantes.ETipoUsuario;
import dto.UsuarioDTO;
import entidade.Usuario;

public abstract class UsuarioConversorDTO {

	public static Usuario converterDTOEmEntidade(UsuarioDTO usuarioDTO)
	{	
		Usuario usuario = new Usuario();
		if(usuarioDTO != null)
		{
			usuario.setLogin(usuarioDTO.getLogin());
			usuario.setSenha(usuarioDTO.getSenha());
			usuario.setTipoUsuario(usuarioDTO.getTipoUsuario().name());
		}
		return usuario;
	}

	public static Usuario converterDTOEmEntidadeComId(UsuarioDTO usuarioDTO)
	{	

		Usuario usuario = new Usuario();
		if(usuarioDTO != null)
		{
			usuario.setLogin(usuarioDTO.getLogin());
			usuario.setSenha(usuarioDTO.getSenha());
			usuario.setTipoUsuario(usuarioDTO.getTipoUsuario().name());
			usuario.setIdUsuario(usuarioDTO.getIdUsuario());
		}
		return usuario;
	}

	public static UsuarioDTO converterEntidadeEmDTO(Usuario usuario)
	{			
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		if(usuario != null)
		{
			usuarioDTO.setLogin(usuario.getLogin());
			usuarioDTO.setSenha(usuario.getSenha());
			usuarioDTO.setTipoUsuario(ETipoUsuario.valueOf(usuario.getTipoUsuario()));
		}
		return usuarioDTO;
	}

	public static UsuarioDTO converterEntidadeEmDTOComId(Usuario usuario)
	{	
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		if(usuario != null)
		{
			usuarioDTO.setLogin(usuario.getLogin());
			usuarioDTO.setSenha(usuario.getSenha());
			usuarioDTO.setTipoUsuario(ETipoUsuario.valueOf(usuario.getTipoUsuario()));
			usuarioDTO.setIdUsuario(usuario.getIdUsuario());
		}
		return usuarioDTO;
	}

	public static List<UsuarioDTO> converterEntidadesSetEmDTOsList(Set<Usuario> usuariosSet)
	{
		List<UsuarioDTO> usuariosDTOList = new ArrayList<UsuarioDTO>();
		if(usuariosSet != null)
		{
			for(Usuario usuario : usuariosSet)
			{
				usuariosDTOList.add(converterEntidadeEmDTO(usuario));
			}
		}
		return usuariosDTOList;
	}

	public static Set<Usuario> converterDTOsListEmEntidadesSet(List<UsuarioDTO> usuariosDTOList)
	{
		Set<Usuario> usuariosSet = new HashSet<Usuario>(0);
		if(usuariosDTOList != null)
		{
			for(UsuarioDTO usuarioDTO : usuariosDTOList)
			{
				usuariosSet.add(converterDTOEmEntidadeComId(usuarioDTO));
			}
		}
		return usuariosSet;
	}

	public static List<UsuarioDTO> converterEntidadesListEmDTOsList(List<Usuario> usuariosList)
	{
		List<UsuarioDTO> usuariosDTOList = new ArrayList<UsuarioDTO>();
		if(usuariosList != null)
		{
			for(Usuario usuario : usuariosList)
			{
				usuariosDTOList.add(converterEntidadeEmDTO(usuario));
			}
		}
		return usuariosDTOList;
	}
}
