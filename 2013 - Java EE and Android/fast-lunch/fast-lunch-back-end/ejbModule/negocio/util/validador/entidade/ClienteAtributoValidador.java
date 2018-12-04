package negocio.util.validador.entidade;

import java.util.List;

import dto.ClienteDTO;

public abstract class ClienteAtributoValidador {
	public static boolean validarAtributosPreenchidosEntidade(ClienteDTO clienteDTO)
	{
		boolean atributosCorretos = true;
		if(clienteDTO == null)
		{
			return false;
		}
		if(clienteDTO.getNome() == null || clienteDTO.getNome().equals(""))
		{
			atributosCorretos = false;
		}
		if(clienteDTO.getSobrenome() == null || clienteDTO.getSobrenome().equals(""))
		{
			atributosCorretos = false;
		}
		if(clienteDTO.getCpf() == null || clienteDTO.getCpf().equals(""))
		{
			atributosCorretos = false;
		}
		if(clienteDTO.getTelefone() == null || clienteDTO.getTelefone().equals(""))
		{
			atributosCorretos = false;
		}
		if(clienteDTO.getEmail() == null || clienteDTO.getEmail().equals(""))
		{
			atributosCorretos = false;
		}
		if(clienteDTO.getDataNascimento() == null)
		{
			atributosCorretos = false;
		}
		
		if(clienteDTO.getSexo() != 'M' && clienteDTO.getSexo() != 'F')
		{
			atributosCorretos = false;
		}
		if(!UsuarioAtributoValidador.
				validarAtributosPreenchidosEntidade(clienteDTO.getUsuario()))
		{
			atributosCorretos = false;
		}
		if(!EnderecoAtributoValidador.
				validarListEntidades(clienteDTO.getEnderecos()))
		{
			atributosCorretos = false;
		}
		return atributosCorretos;
	}
	
	public static boolean validarListEntidades(List<ClienteDTO> clienteDTOs)
	{
		boolean atributosCorretos = true;
		
		for(ClienteDTO clienteDTO : clienteDTOs)
		{
			if(!validarAtributosPreenchidosEntidade(clienteDTO))
			{
				atributosCorretos = false;
				break;
			}
		}
		
		return atributosCorretos;
	}
}
