package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.CardapioProdutoDTO;
import entidade.CardapioProduto;

public abstract class CardapioProdutoConversorDTO {

	public static CardapioProduto converterDTOEmEntidade(CardapioProdutoDTO cardapioProdutoDTO)
	{	
		CardapioProduto cardapioProduto = new CardapioProduto();
		if(cardapioProdutoDTO != null)
		{
			cardapioProduto.setAtivo(cardapioProdutoDTO.getAtivo());
			cardapioProduto.setProduto(ProdutoConversorDTO.
					converterDTOEmEntidade(cardapioProdutoDTO.getProduto()));
			cardapioProduto.setCardapio(CardapioConversorDTO.
					converterDTOEmEntidadeComId(cardapioProdutoDTO.getCardapio()));
		}
		return cardapioProduto;
	}

	public static CardapioProduto converterDTOEmEntidadeComId(CardapioProdutoDTO cardapioProdutoDTO)
	{	
		CardapioProduto cardapioProduto = new CardapioProduto();
		if(cardapioProdutoDTO != null)
		{
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%% - O mistério do 666: " + cardapioProdutoDTO.getProduto().getPontuacaoFidelidade());
			cardapioProduto.setAtivo(cardapioProdutoDTO.getAtivo());
			cardapioProduto.setProduto(ProdutoConversorDTO.
					converterDTOEmEntidadeComId(cardapioProdutoDTO.getProduto()));
			cardapioProduto.setCardapio(CardapioConversorDTO.
					converterDTOEmEntidadeComId(cardapioProdutoDTO.getCardapio()));
			cardapioProduto.setIdcardapioProduto(cardapioProdutoDTO.getIdcardapioProduto());
		}
		return cardapioProduto;
	}

	public static CardapioProdutoDTO converterEntidadeEmDTO(CardapioProduto cardapioProduto)
	{	
		CardapioProdutoDTO cardapioProdutoDTO = new CardapioProdutoDTO();
		if(cardapioProduto != null)
		{
			cardapioProdutoDTO.setAtivo(cardapioProduto.getAtivo());
			cardapioProdutoDTO.setProduto(ProdutoConversorDTO.
					converterEntidadeEmDTOComId(cardapioProduto.getProduto()));
			cardapioProdutoDTO.setCardapio(CardapioConversorDTO.
					converterEntidadeEmDTOComId(cardapioProduto.getCardapio()));
		}
		return cardapioProdutoDTO;
	}

	public static CardapioProdutoDTO converterEntidadeEmDTOComId(CardapioProduto cardapioProduto)
	{	
		CardapioProdutoDTO cardapioProdutoDTO = new CardapioProdutoDTO();
		if(cardapioProduto != null)
		{
			cardapioProdutoDTO.setAtivo(cardapioProduto.getAtivo());
			cardapioProdutoDTO.setProduto(ProdutoConversorDTO.
					converterEntidadeEmDTOComId(cardapioProduto.getProduto()));
			cardapioProdutoDTO.setCardapio(CardapioConversorDTO.
					converterEntidadeEmDTOComId(cardapioProduto.getCardapio()));
			cardapioProdutoDTO.setIdcardapioProduto(cardapioProduto.getIdcardapioProduto());
		}
		return cardapioProdutoDTO;
	}

	public static List<CardapioProdutoDTO> converterEntidadesSetEmDTOsList(Set<CardapioProduto> cardapioProdutosSet)
	{
		List<CardapioProdutoDTO> cardapioProdutosDTOList = new ArrayList<CardapioProdutoDTO>();
		if(cardapioProdutosSet != null)
		{
			for(CardapioProduto cardapioProduto : cardapioProdutosSet)
			{
				cardapioProdutosDTOList.add(converterEntidadeEmDTO(cardapioProduto));
			}
		}
		return cardapioProdutosDTOList;
	}

	public static Set<CardapioProduto> converterDTOsListEmEntidadesSet(List<CardapioProdutoDTO> cardapioProdutosDTOList)
	{
		Set<CardapioProduto> cardapioProdutosSet = new HashSet<CardapioProduto>(0);
		if(cardapioProdutosDTOList != null)
		{
			for(CardapioProdutoDTO cardapioProdutoDTO : cardapioProdutosDTOList)
			{
				cardapioProdutosSet.add(converterDTOEmEntidadeComId(cardapioProdutoDTO));
			}
		}
		return cardapioProdutosSet;
	}

	public static List<CardapioProdutoDTO> converterEntidadesListEmDTOsList(List<CardapioProduto> cardapioProdutosList)
	{
		List<CardapioProdutoDTO> cardapioProdutosDTOList = new ArrayList<CardapioProdutoDTO>();
		if(cardapioProdutosList != null)
		{
			for(CardapioProduto cardapioProduto : cardapioProdutosList)
			{
				cardapioProdutosDTOList.add(converterEntidadeEmDTOComId(cardapioProduto));
			}
		}
		return cardapioProdutosDTOList;
	}
}
