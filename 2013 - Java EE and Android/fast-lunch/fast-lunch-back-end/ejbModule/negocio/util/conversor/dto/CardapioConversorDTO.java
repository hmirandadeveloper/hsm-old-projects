package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.CardapioDTO;
import entidade.Cardapio;

public abstract class CardapioConversorDTO {

	public static Cardapio converterDTOEmEntidade(CardapioDTO cardapioDTO)
	{	
		Cardapio cardapio = new Cardapio();
		if(cardapioDTO != null)
		{
			cardapio.setNome(cardapioDTO.getNome());
			cardapio.setTipoCardapio(TipoCardapioConversorDTO.
					converterDTOEmEntidadeComId(cardapioDTO.getTipoCardapio()));
			cardapio.setValidade(cardapioDTO.getValidade());
		}
		return cardapio;
	}

	public static Cardapio converterDTOEmEntidadeComId(CardapioDTO cardapioDTO)
	{	

		Cardapio cardapio = new Cardapio();
		if(cardapioDTO != null)
		{
			cardapio.setNome(cardapioDTO.getNome());
			cardapio.setTipoCardapio(TipoCardapioConversorDTO.
					converterDTOEmEntidadeComId(cardapioDTO.getTipoCardapio()));
			cardapio.setValidade(cardapioDTO.getValidade());
			cardapio.setIdCardapio(cardapioDTO.getIdCardapio());
		}
		return cardapio;
	}

	public static CardapioDTO converterEntidadeEmDTO(Cardapio cardapio)
	{	

		CardapioDTO cardapioDTO = new CardapioDTO();
		if(cardapio != null)
		{
			cardapioDTO.setNome(cardapio.getNome());
			cardapioDTO.setTipoCardapio(TipoCardapioConversorDTO.
					converterEntidadeEmDTOComId(cardapio.getTipoCardapio()));
			cardapioDTO.setValidade(cardapio.getValidade());
		}
		return cardapioDTO;
	}

	public static CardapioDTO converterEntidadeEmDTOComId(Cardapio cardapio)
	{	

		CardapioDTO cardapioDTO = new CardapioDTO();
		if(cardapio != null)
		{
			cardapioDTO.setNome(cardapio.getNome());
			cardapioDTO.setTipoCardapio(TipoCardapioConversorDTO.
					converterEntidadeEmDTOComId(cardapio.getTipoCardapio()));
			cardapioDTO.setValidade(cardapio.getValidade());
			cardapioDTO.setIdCardapio(cardapio.getIdCardapio());
		}
		return cardapioDTO;
	}

	public static List<CardapioDTO> converterEntidadesSetEmDTOsList(Set<Cardapio> cardapiosSet)
	{
		List<CardapioDTO> cardapiosDTOList = new ArrayList<CardapioDTO>();
		if(cardapiosSet != null)
		{
			for(Cardapio cardapio : cardapiosSet)
			{
				cardapiosDTOList.add(converterEntidadeEmDTO(cardapio));
			}
		}
		return cardapiosDTOList;
	}

	public static Set<Cardapio> converterDTOsListEmEntidadesSet(List<CardapioDTO> cardapiosDTOList)
	{
		Set<Cardapio> cardapiosSet = new HashSet<Cardapio>(0);
		if(cardapiosDTOList != null)
		{
			for(CardapioDTO cardapioDTO : cardapiosDTOList)
			{
				cardapiosSet.add(converterDTOEmEntidadeComId(cardapioDTO));
			}
		}

		return cardapiosSet;
	}

	public static List<CardapioDTO> converterEntidadesListEmDTOsList(List<Cardapio> cardapiosList)
	{
		List<CardapioDTO> cardapiosDTOList = new ArrayList<CardapioDTO>();
		if(cardapiosList != null)
		{
			for(Cardapio cardapio : cardapiosList)
			{
				cardapiosDTOList.add(converterEntidadeEmDTOComId(cardapio));
			}
		}

		return cardapiosDTOList;
	}
}
