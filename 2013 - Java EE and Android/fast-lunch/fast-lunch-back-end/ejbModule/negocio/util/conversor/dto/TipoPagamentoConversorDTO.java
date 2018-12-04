package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.TipoPagamentoDTO;
import entidade.TipoPagamento;

public abstract class TipoPagamentoConversorDTO {

	public static TipoPagamento converterDTOEmEntidade(TipoPagamentoDTO tipoPagamentoDTO)
	{	
		TipoPagamento tipoPagamento = new TipoPagamento();
		if(tipoPagamentoDTO != null)
		{
			tipoPagamento.setDescricao(tipoPagamentoDTO.getDescricao());
			tipoPagamento.setAtivo(tipoPagamentoDTO.getAtivo());
			tipoPagamento.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(tipoPagamentoDTO.getEstabelecimentoDTO()));
		}
		return tipoPagamento;
	}

	public static TipoPagamento converterDTOEmEntidadeComId(TipoPagamentoDTO tipoPagamentoDTO)
	{	

		TipoPagamento tipoPagamento = new TipoPagamento();
		if(tipoPagamentoDTO != null)
		{
			tipoPagamento.setDescricao(tipoPagamentoDTO.getDescricao());
			tipoPagamento.setAtivo(tipoPagamentoDTO.getAtivo());
			tipoPagamento.setIdTipoPagamento(tipoPagamentoDTO.getIdTipoPagamento());
			tipoPagamento.setEstabelecimento(
					EstabelecimentoConversorDTO.
					converterDTOEmEntidadeComId(tipoPagamentoDTO.getEstabelecimentoDTO()));
		}
		return tipoPagamento;
	}

	public static TipoPagamentoDTO converterEntidadeEmDTO(TipoPagamento tipoPagamento)
	{	
		TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO();
		if(tipoPagamento != null)
		{
			tipoPagamentoDTO.setDescricao(tipoPagamento.getDescricao());
			tipoPagamentoDTO.setAtivo(tipoPagamento.getAtivo());
			tipoPagamentoDTO.setEstabelecimentoDTO(
					EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(tipoPagamento.getEstabelecimento()));
		}
		return tipoPagamentoDTO;
	}

	public static TipoPagamentoDTO converterEntidadeEmDTOComId(TipoPagamento tipoPagamento)
	{	
		TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO();
		if(tipoPagamento != null)
		{
			tipoPagamentoDTO.setDescricao(tipoPagamento.getDescricao());
			tipoPagamentoDTO.setAtivo(tipoPagamento.getAtivo());
			tipoPagamentoDTO.setIdTipoPagamento(tipoPagamento.getIdTipoPagamento());
			tipoPagamentoDTO.setEstabelecimentoDTO(
					EstabelecimentoConversorDTO.
					converterEntidadeEmDTOComId(tipoPagamento.getEstabelecimento()));
		}
		return tipoPagamentoDTO;
	}

	public static List<TipoPagamentoDTO> converterEntidadesSetEmDTOsList(Set<TipoPagamento> tipoPagamentosSet)
	{
		List<TipoPagamentoDTO> tipoPagamentosDTOList = new ArrayList<TipoPagamentoDTO>();
		if(tipoPagamentosSet != null)
		{
			for(TipoPagamento tipoPagamento : tipoPagamentosSet)
			{
				tipoPagamentosDTOList.add(converterEntidadeEmDTO(tipoPagamento));
			}
		}
		return tipoPagamentosDTOList;
	}

	public static Set<TipoPagamento> converterDTOsListEmEntidadesSet(List<TipoPagamentoDTO> tipoPagamentosDTOList)
	{
		Set<TipoPagamento> tipoPagamentosSet = new HashSet<TipoPagamento>(0);
		if(tipoPagamentosDTOList != null)
		{
			for(TipoPagamentoDTO tipoPagamentoDTO : tipoPagamentosDTOList)
			{
				tipoPagamentosSet.add(converterDTOEmEntidadeComId(tipoPagamentoDTO));
			}
		}
		return tipoPagamentosSet;
	}

	public static List<TipoPagamentoDTO> converterEntidadesListEmDTOsList(List<TipoPagamento> tipoPagamentosList)
	{
		List<TipoPagamentoDTO> tipoPagamentosDTOList = new ArrayList<TipoPagamentoDTO>();
		if(tipoPagamentosList != null)
		{
			for(TipoPagamento tipoPagamento : tipoPagamentosList)
			{
				tipoPagamentosDTOList.add(converterEntidadeEmDTOComId(tipoPagamento));
			}
		}
		return tipoPagamentosDTOList;
	}
}
