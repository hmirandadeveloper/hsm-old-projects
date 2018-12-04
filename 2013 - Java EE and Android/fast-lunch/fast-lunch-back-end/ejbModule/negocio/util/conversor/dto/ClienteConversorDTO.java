package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.ClienteDTO;
import entidade.Cliente;

public abstract class ClienteConversorDTO {

	public static Cliente converterDTOEmEntidade(ClienteDTO clienteDTO)
	{	
		Cliente cliente = new Cliente();
		if(clienteDTO != null)
		{
			cliente.setNome(clienteDTO.getNome());
			cliente.setSobrenome(clienteDTO.getSobrenome());
			cliente.setCpf(clienteDTO.getCpf());
			cliente.setTelefone(clienteDTO.getTelefone());
			cliente.setCelular(clienteDTO.getCelular());
			cliente.setEmail(clienteDTO.getEmail());
			cliente.setDataNascimento(clienteDTO.getDataNascimento());
			cliente.setSexo(clienteDTO.getSexo());
			cliente.setAtivo(clienteDTO.isAtivo());
			cliente.setDataCadastro(clienteDTO.getDataCadastro());
			cliente.setPontuacaoFidelidade(clienteDTO.getPontuacaoFidelidade());
			cliente.setUsuario(UsuarioConversorDTO.
					converterDTOEmEntidade(clienteDTO.getUsuario()));
			cliente.setEnderecos(EnderecoConversorDTO.
					converterDTOsListEmEntidadesSet(clienteDTO.getEnderecos()));
		}
		return cliente;
	}

	public static Cliente converterDTOEmEntidadeComId(ClienteDTO clienteDTO)
	{	
		Cliente cliente = new Cliente();
		if(clienteDTO != null)
		{
			cliente.setNome(clienteDTO.getNome());
			cliente.setSobrenome(clienteDTO.getSobrenome());
			cliente.setCpf(clienteDTO.getCpf());
			cliente.setTelefone(clienteDTO.getTelefone());
			cliente.setCelular(clienteDTO.getCelular());
			cliente.setEmail(clienteDTO.getEmail());
			cliente.setDataNascimento(clienteDTO.getDataNascimento());
			cliente.setSexo(clienteDTO.getSexo());
			cliente.setAtivo(clienteDTO.isAtivo());
			cliente.setDataCadastro(clienteDTO.getDataCadastro());
			cliente.setPontuacaoFidelidade(clienteDTO.getPontuacaoFidelidade());
			cliente.setUsuario(UsuarioConversorDTO.
					converterDTOEmEntidadeComId(clienteDTO.getUsuario()));
			cliente.setEnderecos(EnderecoConversorDTO.
					converterDTOsListEmEntidadesSet(clienteDTO.getEnderecos()));
			cliente.setIdCliente(clienteDTO.getIdCliente());
		}
		return cliente;
	}

	public static ClienteDTO converterEntidadeEmDTO(Cliente cliente)
	{	
		ClienteDTO clienteDTO = new ClienteDTO();

		if(cliente != null)
		{
			clienteDTO.setNome(cliente.getNome());
			clienteDTO.setSobrenome(cliente.getSobrenome());
			clienteDTO.setCpf(cliente.getCpf());
			clienteDTO.setTelefone(cliente.getTelefone());
			clienteDTO.setCelular(cliente.getCelular());
			clienteDTO.setEmail(cliente.getEmail());
			clienteDTO.setDataNascimento(cliente.getDataNascimento());
			clienteDTO.setSexo(cliente.getSexo());
			clienteDTO.setAtivo(cliente.isAtivo());
			clienteDTO.setDataCadastro(cliente.getDataCadastro());
			clienteDTO.setPontuacaoFidelidade(cliente.getPontuacaoFidelidade());
			clienteDTO.setUsuario(UsuarioConversorDTO.
					converterEntidadeEmDTO(cliente.getUsuario()));
			clienteDTO.setEnderecos(EnderecoConversorDTO.
					converterEntidadesSetEmDTOsList(cliente.getEnderecos()));
		}

		return clienteDTO;
	}

	public static ClienteDTO converterEntidadeEmDTOComId(Cliente cliente)
	{	
		ClienteDTO clienteDTO = new ClienteDTO();
		if(cliente != null)
		{
			clienteDTO.setNome(cliente.getNome());
			clienteDTO.setSobrenome(cliente.getSobrenome());
			clienteDTO.setCpf(cliente.getCpf());
			clienteDTO.setTelefone(cliente.getTelefone());
			clienteDTO.setCelular(cliente.getCelular());
			clienteDTO.setEmail(cliente.getEmail());
			clienteDTO.setDataNascimento(cliente.getDataNascimento());
			clienteDTO.setSexo(cliente.getSexo());
			clienteDTO.setAtivo(cliente.isAtivo());
			clienteDTO.setDataCadastro(cliente.getDataCadastro());
			clienteDTO.setPontuacaoFidelidade(cliente.getPontuacaoFidelidade());
			clienteDTO.setUsuario(UsuarioConversorDTO.
					converterEntidadeEmDTOComId(cliente.getUsuario()));
			clienteDTO.setEnderecos(EnderecoConversorDTO.
					converterEntidadesSetEmDTOsList(cliente.getEnderecos()));
			clienteDTO.setIdCliente(cliente.getIdCliente());
		}
		return clienteDTO;
	}

	public static List<ClienteDTO> converterEntidadesSetEmDTOsList(Set<Cliente> clientesSet)
	{
		List<ClienteDTO> clientesDTOList = new ArrayList<ClienteDTO>();
		if(clientesSet != null)
		{
			for(Cliente cliente : clientesSet)
			{
				clientesDTOList.add(converterEntidadeEmDTO(cliente));
			}
		}
		return clientesDTOList;
	}

	public static Set<Cliente> converterDTOsListEmEntidadesSet(List<ClienteDTO> clientesDTOList)
	{
		Set<Cliente> clientesSet = new HashSet<Cliente>(0);
		if(clientesDTOList != null)
		{
			for(ClienteDTO clienteDTO : clientesDTOList)
			{
				clientesSet.add(converterDTOEmEntidadeComId(clienteDTO));
			}
		}
		return clientesSet;
	}

	public static List<ClienteDTO> converterEntidadesListEmDTOsList(List<Cliente> clientesList)
	{
		List<ClienteDTO> clientesDTOList = new ArrayList<ClienteDTO>();
		if(clientesList != null)
		{
			for(Cliente cliente : clientesList)
			{
				clientesDTOList.add(converterEntidadeEmDTO(cliente));
			}
		}
		return clientesDTOList;
	}
}
