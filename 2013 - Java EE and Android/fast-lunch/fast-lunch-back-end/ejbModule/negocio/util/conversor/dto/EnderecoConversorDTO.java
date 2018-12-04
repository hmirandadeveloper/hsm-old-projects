package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.EnderecoDTO;
import entidade.Endereco;

public abstract class EnderecoConversorDTO {

	public static Endereco converterDTOEmEntidade(EnderecoDTO enderecoDTO)
	{	
		Endereco endereco = new Endereco();
		if(enderecoDTO != null)
		{
			endereco.setCep(enderecoDTO.getCep());
			endereco.setLogradouro(enderecoDTO.getLogradouro());
			endereco.setNumero(enderecoDTO.getNumero());
			endereco.setComplemento(enderecoDTO.getComplemento());
			endereco.setBairro(enderecoDTO.getBairro());
			endereco.setCidade(enderecoDTO.getCidade());
			endereco.setUf(enderecoDTO.getUf());
			endereco.setPontoReferencia(enderecoDTO.getPontoReferencia());
		}
		return endereco;
	}

	public static Endereco converterDTOEmEntidadeComId(EnderecoDTO enderecoDTO)
	{	
		Endereco endereco = new Endereco();
		if(enderecoDTO != null)
		{
			endereco.setCep(enderecoDTO.getCep());
			endereco.setLogradouro(enderecoDTO.getLogradouro());
			endereco.setNumero(enderecoDTO.getNumero());
			endereco.setComplemento(enderecoDTO.getComplemento());
			endereco.setBairro(enderecoDTO.getBairro());
			endereco.setCidade(enderecoDTO.getCidade());
			endereco.setUf(enderecoDTO.getUf());
			endereco.setPontoReferencia(enderecoDTO.getPontoReferencia());
			endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		}
		return endereco;
	}

	public static EnderecoDTO converterEntidadeEmDTO(Endereco endereco)
	{	
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		if(endereco != null)
		{
			enderecoDTO.setCep(endereco.getCep());
			enderecoDTO.setLogradouro(endereco.getLogradouro());
			enderecoDTO.setNumero(endereco.getNumero());
			enderecoDTO.setComplemento(endereco.getComplemento());
			enderecoDTO.setBairro(endereco.getBairro());
			enderecoDTO.setCidade(endereco.getCidade());
			enderecoDTO.setUf(endereco.getUf());
			enderecoDTO.setPontoReferencia(endereco.getPontoReferencia());
		}
		return enderecoDTO;
	}

	public static EnderecoDTO converterEntidadeEmDTOComId(Endereco endereco)
	{	
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		if(endereco != null)
		{
			enderecoDTO.setLogradouro(endereco.getLogradouro());
			enderecoDTO.setNumero(endereco.getNumero());
			enderecoDTO.setComplemento(endereco.getComplemento());
			enderecoDTO.setBairro(endereco.getBairro());
			enderecoDTO.setCidade(endereco.getCidade());
			enderecoDTO.setUf(endereco.getUf());
			enderecoDTO.setCep(endereco.getCep());
			enderecoDTO.setPontoReferencia(endereco.getPontoReferencia());
			enderecoDTO.setIdEndereco(endereco.getIdEndereco());
		}
		return enderecoDTO;
	}

	public static List<EnderecoDTO> converterEntidadesSetEmDTOsList(Set<Endereco> enderecosSet)
	{
		List<EnderecoDTO> enderecosDTOList = new ArrayList<EnderecoDTO>();
		if(enderecosSet != null)
		{
			for(Endereco endereco : enderecosSet)
			{
				enderecosDTOList.add(converterEntidadeEmDTOComId(endereco));
			}
		}
		return enderecosDTOList;
	}

	public static Set<Endereco> converterDTOsListEmEntidadesSet(List<EnderecoDTO> enderecosDTOList)
	{
		Set<Endereco> enderecosSet = new HashSet<Endereco>(0);
		if(enderecosDTOList != null)
		{
			for(EnderecoDTO enderecoDTO : enderecosDTOList)
			{
				enderecosSet.add(converterDTOEmEntidadeComId(enderecoDTO));
			}
		}
		return enderecosSet;
	}

	public static List<EnderecoDTO> converterEntidadesListEmDTOsList(List<Endereco> enderecosList)
	{
		List<EnderecoDTO> enderecosDTOList = new ArrayList<EnderecoDTO>();
		if(enderecosList != null)
		{
			for(Endereco endereco : enderecosList)
			{
				enderecosDTOList.add(converterEntidadeEmDTO(endereco));
			}
		}
		return enderecosDTOList;
	}
}
