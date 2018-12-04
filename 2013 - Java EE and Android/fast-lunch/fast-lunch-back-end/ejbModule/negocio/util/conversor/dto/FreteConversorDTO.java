package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.FreteDTO;
import entidade.Frete;

public abstract class FreteConversorDTO {

	public static Frete converterDTOEmEntidade(FreteDTO freteDTO)
	{	
		Frete frete = new Frete();
		if(freteDTO != null)
		{
			frete.setBairro(freteDTO.getBairro());
			frete.setValor(freteDTO.getValor());
			frete.setAtivo(freteDTO.getAtivo());
			frete.setEstabelecimento(
					EstabelecimentoConversorDTO.converterDTOEmEntidadeComId(
							freteDTO.getEstabelecimentoDTO()));
		}
		return frete;
	}

	public static Frete converterDTOEmEntidadeComId(FreteDTO freteDTO)
	{	
		Frete frete = new Frete();
		if(freteDTO != null)
		{
			frete.setBairro(freteDTO.getBairro());
			frete.setValor(freteDTO.getValor());
			frete.setAtivo(freteDTO.getAtivo());
			frete.setEstabelecimento(
					EstabelecimentoConversorDTO.converterDTOEmEntidadeComId(
							freteDTO.getEstabelecimentoDTO()));
			frete.setIdFrete(freteDTO.getIdFrete());
		}
		return frete;
	}

	public static FreteDTO converterEntidadeEmDTO(Frete frete)
	{	
		FreteDTO freteDTO = new FreteDTO();
		if(frete != null)
		{
			freteDTO.setBairro(frete.getBairro());
			freteDTO.setValor(frete.getValor());
			freteDTO.setAtivo(frete.getAtivo());
			freteDTO.setEstabelecimentoDTO(
					EstabelecimentoConversorDTO.converterEntidadeEmDTOComId(
							frete.getEstabelecimento()));
		}
		return freteDTO;
	}

	public static FreteDTO converterEntidadeEmDTOComId(Frete frete)
	{	
		FreteDTO freteDTO = new FreteDTO();
		if(frete != null)
		{
			freteDTO.setBairro(frete.getBairro());
			freteDTO.setValor(frete.getValor());
			freteDTO.setAtivo(frete.getAtivo());
			freteDTO.setEstabelecimentoDTO(
					EstabelecimentoConversorDTO.converterEntidadeEmDTOComId(
							frete.getEstabelecimento()));
			freteDTO.setIdFrete(frete.getIdFrete());
		}
		return freteDTO;
	}

	public static List<FreteDTO> converterEntidadesSetEmDTOsList(Set<Frete> fretesSet)
	{
		List<FreteDTO> fretesDTOList = new ArrayList<FreteDTO>();
		if(fretesSet != null)
		{
			for(Frete frete : fretesSet)
			{
				fretesDTOList.add(converterEntidadeEmDTO(frete));
			}
		}
		return fretesDTOList;
	}

	public static Set<Frete> converterDTOsListEmEntidadesSet(List<FreteDTO> fretesDTOList)
	{
		Set<Frete> fretesSet = new HashSet<Frete>(0);
		if(fretesDTOList != null)
		{
			for(FreteDTO freteDTO : fretesDTOList)
			{
				fretesSet.add(converterDTOEmEntidadeComId(freteDTO));
			}
		}
		return fretesSet;
	}

	public static List<FreteDTO> converterEntidadesListEmDTOsList(List<Frete> fretesList)
	{
		List<FreteDTO> usuariosDTOList = new ArrayList<FreteDTO>();
		if(fretesList != null)
		{
			for(Frete frete : fretesList)
			{
				usuariosDTOList.add(converterEntidadeEmDTOComId(frete));
			}
		}
		return usuariosDTOList;
	}
}
