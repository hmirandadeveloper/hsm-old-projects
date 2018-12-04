package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.FidelidadeTrocaDTO;
import entidade.FidelidadeTroca;

public abstract class FidelidadeTrocaConversorDTO {

	public static FidelidadeTroca converterDTOEmEntidade(FidelidadeTrocaDTO fidelidadeTrocaDTO)
	{	
		FidelidadeTroca fidelidadeTroca = new FidelidadeTroca();
		if(fidelidadeTrocaDTO != null)
		{
			fidelidadeTroca.setProduto(ProdutoConversorDTO.converterDTOEmEntidade(
					fidelidadeTrocaDTO.getProduto()));
			fidelidadeTroca.setCliente(ClienteConversorDTO.converterDTOEmEntidade(
					fidelidadeTrocaDTO.getCliente()));
			fidelidadeTroca.setDataHora(fidelidadeTrocaDTO.getDataHora());
			fidelidadeTroca.setPontos(fidelidadeTrocaDTO.getPontos());
		}
		return fidelidadeTroca;
	}

	public static FidelidadeTroca converterDTOEmEntidadeComId(FidelidadeTrocaDTO fidelidadeTrocaDTO)
	{	
		FidelidadeTroca fidelidadeTroca = new FidelidadeTroca();
		if(fidelidadeTrocaDTO != null)
		{
			fidelidadeTroca.setProduto(ProdutoConversorDTO.converterDTOEmEntidade(
					fidelidadeTrocaDTO.getProduto()));
			fidelidadeTroca.setCliente(ClienteConversorDTO.converterDTOEmEntidade(
					fidelidadeTrocaDTO.getCliente()));
			fidelidadeTroca.setDataHora(fidelidadeTrocaDTO.getDataHora());
			fidelidadeTroca.setPontos(fidelidadeTrocaDTO.getPontos());
			fidelidadeTroca.setIdFidelidadeTroca(fidelidadeTrocaDTO.getIdFidelidadeTroca());
		}
		return fidelidadeTroca;
	}

	public static FidelidadeTrocaDTO converterEntidadeEmDTO(FidelidadeTroca fidelidadeTroca)
	{	
		FidelidadeTrocaDTO fidelidadeTrocaDTO = new FidelidadeTrocaDTO();
		if(fidelidadeTroca != null)
		{
			fidelidadeTrocaDTO.setProduto(ProdutoConversorDTO.converterEntidadeEmDTO(
					fidelidadeTroca.getProduto()));
			fidelidadeTrocaDTO.setCliente(ClienteConversorDTO.converterEntidadeEmDTO(
					fidelidadeTroca.getCliente()));
			fidelidadeTrocaDTO.setDataHora(fidelidadeTroca.getDataHora());
			fidelidadeTrocaDTO.setPontos(fidelidadeTroca.getPontos());
		}
		return fidelidadeTrocaDTO;
	}

	public static FidelidadeTrocaDTO converterEntidadeEmDTOComId(FidelidadeTroca fidelidadeTroca)
	{	
		FidelidadeTrocaDTO fidelidadeTrocaDTO = new FidelidadeTrocaDTO();
		if(fidelidadeTroca != null)
		{
			fidelidadeTrocaDTO.setProduto(ProdutoConversorDTO.converterEntidadeEmDTO(
					fidelidadeTroca.getProduto()));
			fidelidadeTrocaDTO.setCliente(ClienteConversorDTO.converterEntidadeEmDTO(
					fidelidadeTroca.getCliente()));
			fidelidadeTrocaDTO.setDataHora(fidelidadeTroca.getDataHora());
			fidelidadeTrocaDTO.setPontos(fidelidadeTroca.getPontos());
			fidelidadeTrocaDTO.setIdFidelidadeTroca(fidelidadeTroca.getIdFidelidadeTroca());
		}
		return fidelidadeTrocaDTO;
	}

	public static List<FidelidadeTrocaDTO> converterEntidadesSetEmDTOsList(Set<FidelidadeTroca> fidelidadeTrocasSet)
	{
		List<FidelidadeTrocaDTO> fidelidadeTrocasDTOList = new ArrayList<FidelidadeTrocaDTO>();
		if(fidelidadeTrocasSet != null)
		{
			for(FidelidadeTroca fidelidadeTroca : fidelidadeTrocasSet)
			{
				fidelidadeTrocasDTOList.add(converterEntidadeEmDTO(fidelidadeTroca));
			}
		}
		return fidelidadeTrocasDTOList;
	}

	public static Set<FidelidadeTroca> converterDTOsListEmEntidadesSet(List<FidelidadeTrocaDTO> fidelidadeTrocasDTOList)
	{
		Set<FidelidadeTroca> fidelidadeTrocasSet = new HashSet<FidelidadeTroca>(0);
		if(fidelidadeTrocasDTOList != null)
		{
			for(FidelidadeTrocaDTO fidelidadeTrocaDTO : fidelidadeTrocasDTOList)
			{
				fidelidadeTrocasSet.add(converterDTOEmEntidadeComId(fidelidadeTrocaDTO));
			}
		}
		return fidelidadeTrocasSet;
	}

	public static List<FidelidadeTrocaDTO> converterEntidadesListEmDTOsList(List<FidelidadeTroca> fidelidadeTrocasList)
	{
		List<FidelidadeTrocaDTO> fidelidadeTrocasDTOList = new ArrayList<FidelidadeTrocaDTO>();
		if(fidelidadeTrocasList != null)
		{
			for(FidelidadeTroca fidelidadeTroca : fidelidadeTrocasList)
			{
				fidelidadeTrocasDTOList.add(converterEntidadeEmDTO(fidelidadeTroca));
			}
		}
		return fidelidadeTrocasDTOList;
	}
}
