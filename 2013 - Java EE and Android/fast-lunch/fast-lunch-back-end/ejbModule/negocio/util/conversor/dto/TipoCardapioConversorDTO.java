package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.TipoCardapioDTO;
import entidade.TipoCardapio;

public abstract class TipoCardapioConversorDTO {

	public static TipoCardapio converterDTOEmEntidade(TipoCardapioDTO tipoCardapioDTO)
	{	
		TipoCardapio tipoCardapio = new TipoCardapio();
		if(tipoCardapioDTO != null)
		{
			tipoCardapio.setNome(tipoCardapioDTO.getNome());
			tipoCardapio.setDescricao(tipoCardapioDTO.getDescricao());
			tipoCardapio.setAtivo(tipoCardapioDTO.isAtivo());
			tipoCardapio.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(tipoCardapioDTO.getEstabelecimentoDTO()));
		}
		return tipoCardapio;
	}

	public static TipoCardapio converterDTOEmEntidadeComId(TipoCardapioDTO tipoCardapioDTO)
	{	

		TipoCardapio tipoCardapio = new TipoCardapio();
		if(tipoCardapioDTO != null)
		{
			tipoCardapio.setIdTipoCardapio(tipoCardapioDTO.getIdTipoCardapio());
			tipoCardapio.setNome(tipoCardapioDTO.getNome());
			tipoCardapio.setDescricao(tipoCardapioDTO.getDescricao());
			tipoCardapio.setAtivo(tipoCardapioDTO.isAtivo());
			tipoCardapio.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(tipoCardapioDTO.getEstabelecimentoDTO()));
		}
		return tipoCardapio;
	}

	public static TipoCardapioDTO converterEntidadeEmDTO(TipoCardapio tipoCardapio)
	{	
		TipoCardapioDTO tipoCardapioDTO = new TipoCardapioDTO();
		if(tipoCardapio != null)
		{
			tipoCardapioDTO.setNome(tipoCardapio.getNome());
			tipoCardapioDTO.setDescricao(tipoCardapio.getDescricao());
			tipoCardapioDTO.setAtivo(tipoCardapio.isAtivo());
			tipoCardapioDTO.setEstabelecimentoDTO(
					EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(tipoCardapio.getEstabelecimento()));
		}
		return tipoCardapioDTO;
	}

	public static TipoCardapioDTO converterEntidadeEmDTOComId(TipoCardapio tipoCardapio)
	{	
		TipoCardapioDTO tipoCardapioDTO = new TipoCardapioDTO();
		if(tipoCardapio != null)
		{
			tipoCardapioDTO.setIdTipoCardapio(tipoCardapio.getIdTipoCardapio());
			tipoCardapioDTO.setNome(tipoCardapio.getNome());
			tipoCardapioDTO.setDescricao(tipoCardapio.getDescricao());
			tipoCardapioDTO.setAtivo(tipoCardapio.isAtivo());
			tipoCardapioDTO.setEstabelecimentoDTO(
					EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(tipoCardapio.getEstabelecimento()));
		}
		return tipoCardapioDTO;
	}

	public static List<TipoCardapioDTO> converterEntidadesSetEmDTOsList(Set<TipoCardapio> tipoCardapiosSet)
	{
		List<TipoCardapioDTO> tipoCardapiosDTOList = new ArrayList<TipoCardapioDTO>();
		if(tipoCardapiosSet != null)
		{
			for(TipoCardapio tipoCardapio : tipoCardapiosSet)
			{
				tipoCardapiosDTOList.add(converterEntidadeEmDTO(tipoCardapio));
			}
		}
		return tipoCardapiosDTOList;
	}

	public static Set<TipoCardapio> converterDTOsListEmEntidadesSet(List<TipoCardapioDTO> tipoCardapiosDTOList)
	{
		Set<TipoCardapio> tipoCardapiosSet = new HashSet<TipoCardapio>(0);
		if(tipoCardapiosDTOList != null)
		{
			for(TipoCardapioDTO tipoCardapioDTO : tipoCardapiosDTOList)
			{
				tipoCardapiosSet.add(converterDTOEmEntidadeComId(tipoCardapioDTO));
			}
		}
		return tipoCardapiosSet;
	}

	public static List<TipoCardapioDTO> converterEntidadesListEmDTOsList(List<TipoCardapio> tipoCardapiosList)
	{
		List<TipoCardapioDTO> tipoCardapiosDTOList = new ArrayList<TipoCardapioDTO>();
		if(tipoCardapiosList != null)
		{
			for(TipoCardapio tipoCardapio : tipoCardapiosList)
			{
				tipoCardapiosDTOList.add(converterEntidadeEmDTOComId(tipoCardapio));
			}
		}
		return tipoCardapiosDTOList;
	}
}
