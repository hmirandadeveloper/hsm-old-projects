package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.EstabelecimentoDTO;
import entidade.Estabelecimento;

public abstract class EstabelecimentoConversorDTO {

	public static Estabelecimento converterDTOEmEntidade(EstabelecimentoDTO estabelecimentoDTO)
	{	
		Estabelecimento estabelecimento = new Estabelecimento();
		if(estabelecimentoDTO != null)
		{
			if(estabelecimentoDTO.getEstabelecimento() != null)
			{
				if(estabelecimentoDTO.getEstabelecimento().getIdEstabelecimento() != null &&
						estabelecimentoDTO.getTipoEstabelecimento() == 'F')
				{
					estabelecimento.setEstabelecimento(EstabelecimentoConversorDTO.
							converterDTOEmEntidadeComId(estabelecimentoDTO.getEstabelecimento()));
				}
			}
			estabelecimento.setRazaoSocial(estabelecimentoDTO.getRazaoSocial());
			estabelecimento.setNomeFantasia(estabelecimentoDTO.getNomeFantasia());
			estabelecimento.setCnpj(estabelecimentoDTO.getCnpj());
			estabelecimento.setTelefone(estabelecimentoDTO.getTelefone());
			estabelecimento.setEmail(estabelecimentoDTO.getEmail());
			estabelecimento.setTipoEstabelecimento(
					estabelecimentoDTO.getTipoEstabelecimento());
			estabelecimento.setAtivo(estabelecimentoDTO.getAtivo());
			estabelecimento.setEndereco(EnderecoConversorDTO.converterDTOEmEntidade(
					estabelecimentoDTO.getEnderecoDTO()));
		}
		return estabelecimento;
	}

	public static Estabelecimento converterDTOEmEntidadeComId(EstabelecimentoDTO estabelecimentoDTO)
	{	

		Estabelecimento estabelecimento = new Estabelecimento();
		if(estabelecimentoDTO != null)
		{
			if(estabelecimentoDTO.getEstabelecimento() != null)
			{
				if(estabelecimentoDTO.getEstabelecimento().getIdEstabelecimento() != null &&
						estabelecimentoDTO.getTipoEstabelecimento() == 'F')
				{
					estabelecimento.setEstabelecimento(EstabelecimentoConversorDTO.
							converterDTOEmEntidadeComId(estabelecimentoDTO.getEstabelecimento()));
				}
			}
			estabelecimento.setRazaoSocial(estabelecimentoDTO.getRazaoSocial());
			estabelecimento.setNomeFantasia(estabelecimentoDTO.getNomeFantasia());
			estabelecimento.setCnpj(estabelecimentoDTO.getCnpj());
			estabelecimento.setTelefone(estabelecimentoDTO.getTelefone());
			estabelecimento.setEmail(estabelecimentoDTO.getEmail());
			estabelecimento.setTipoEstabelecimento(
					estabelecimentoDTO.getTipoEstabelecimento());
			estabelecimento.setAtivo(estabelecimentoDTO.getAtivo());
			estabelecimento.setIdEstabelecimento(estabelecimentoDTO.getIdEstabelecimento());
			estabelecimento.setEndereco(EnderecoConversorDTO.converterDTOEmEntidadeComId(
					estabelecimentoDTO.getEnderecoDTO()));
		}
		return estabelecimento;
	}

	public static EstabelecimentoDTO converterEntidadeEmDTO(Estabelecimento estabelecimento)
	{	
		EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
		if(estabelecimento != null)
		{
			if(estabelecimento.getEstabelecimento() != null)
			{
				if(estabelecimento.getEstabelecimento().getIdEstabelecimento() != null &&
						estabelecimento.getTipoEstabelecimento() == 'F')
				{
					estabelecimentoDTO.setEstabelecimento(EstabelecimentoConversorDTO.
							converterEntidadeEmDTOComId(estabelecimento.getEstabelecimento()));
				}
			}
			estabelecimentoDTO.setRazaoSocial(estabelecimento.getRazaoSocial());
			estabelecimentoDTO.setNomeFantasia(estabelecimento.getNomeFantasia());
			estabelecimentoDTO.setCnpj(estabelecimento.getCnpj());
			estabelecimentoDTO.setTelefone(estabelecimento.getTelefone());
			estabelecimentoDTO.setEmail(estabelecimento.getEmail());
			estabelecimentoDTO.setTipoEstabelecimento(
					estabelecimento.getTipoEstabelecimento());
			estabelecimentoDTO.setAtivo(estabelecimento.getAtivo());
			estabelecimentoDTO.setEnderecoDTO(EnderecoConversorDTO.converterEntidadeEmDTO(
					estabelecimento.getEndereco()));
		}
		return estabelecimentoDTO;
	}

	public static EstabelecimentoDTO converterEntidadeEmDTOComId(Estabelecimento estabelecimento)
	{	
		EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
		if(estabelecimento != null)
		{
			if(estabelecimento.getEstabelecimento() != null)
			{
				if(estabelecimento.getEstabelecimento().getIdEstabelecimento() != null 
						&& estabelecimento.getTipoEstabelecimento() == 'F')
				{
					estabelecimentoDTO.setEstabelecimento(EstabelecimentoConversorDTO.
							converterEntidadeEmDTOComId(estabelecimento.getEstabelecimento()));
				}
			}
			estabelecimentoDTO.setRazaoSocial(estabelecimento.getRazaoSocial());
			estabelecimentoDTO.setNomeFantasia(estabelecimento.getNomeFantasia());
			estabelecimentoDTO.setCnpj(estabelecimento.getCnpj());
			estabelecimentoDTO.setTelefone(estabelecimento.getTelefone());
			estabelecimentoDTO.setEmail(estabelecimento.getEmail());
			estabelecimentoDTO.setTipoEstabelecimento(
					estabelecimento.getTipoEstabelecimento());
			estabelecimentoDTO.setAtivo(estabelecimento.getAtivo());
			estabelecimentoDTO.setIdEstabelecimento(estabelecimento.getIdEstabelecimento());
			estabelecimentoDTO.setEnderecoDTO(EnderecoConversorDTO.converterEntidadeEmDTOComId(
					estabelecimento.getEndereco()));
		}
		return estabelecimentoDTO;
	}

	public static List<EstabelecimentoDTO> converterEntidadesSetEmDTOsList(Set<Estabelecimento> estabelecimentosSet)
	{
		List<EstabelecimentoDTO> estabelecimentosDTOList = new ArrayList<EstabelecimentoDTO>();
		if(estabelecimentosSet != null)
		{
			for(Estabelecimento estabelecimento : estabelecimentosSet)
			{
				estabelecimentosDTOList.add(converterEntidadeEmDTO(estabelecimento));
			}
		}
		return estabelecimentosDTOList;
	}

	public static Set<Estabelecimento> converterDTOsListEmEntidadesSet(List<EstabelecimentoDTO> estabelecimentosDTOList)
	{
		Set<Estabelecimento> estabelecimentosSet = new HashSet<Estabelecimento>(0);
		if(estabelecimentosDTOList != null)
		{
			for(EstabelecimentoDTO estabelecimentoDTO : estabelecimentosDTOList)
			{
				estabelecimentosSet.add(converterDTOEmEntidadeComId(estabelecimentoDTO));
			}
		}
		return estabelecimentosSet;
	}

	public static List<EstabelecimentoDTO> converterEntidadesListEmDTOsList(List<Estabelecimento> estabelecimentosList)
	{
		List<EstabelecimentoDTO> estabelecimentosDTOList = new ArrayList<EstabelecimentoDTO>();
		if(estabelecimentosList != null)
		{
			for(Estabelecimento estabelecimento : estabelecimentosList)
			{
				estabelecimentosDTOList.add(converterEntidadeEmDTO(estabelecimento));
			}
		}
		return estabelecimentosDTOList;
	}
}
