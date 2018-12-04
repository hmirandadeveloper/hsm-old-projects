package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.ProdutoDTO;
import entidade.Produto;

public abstract class ProdutoConversorDTO {

	public static Produto converterDTOEmEntidade(ProdutoDTO produtoDTO)
	{	
		Produto produto = new Produto();
		if(produtoDTO != null)
		{
			produto.setNome(produtoDTO.getNome());
			produto.setDescricao(produtoDTO.getDescricao());
			produto.setPreco(produtoDTO.getPreco());
			produto.setImagem(produtoDTO.getImagem());
			produto.setAtivo(produtoDTO.isAtivo());
			produto.setPrecoPromocional(produtoDTO.getPrecoPromocional());
			produto.setPontuacaoFidelidade(produtoDTO.getPontuacaoFidelidade());
			produto.setDisponibilidadeFidelidade(produtoDTO.getDisponibilidadeFidelidade());
		}
		return produto;
	}

	public static Produto converterDTOEmEntidadeComId(ProdutoDTO produtoDTO)
	{	

		Produto produto = new Produto();
		if(produtoDTO != null)
		{
			produto.setNome(produtoDTO.getNome());
			produto.setDescricao(produtoDTO.getDescricao());
			produto.setPreco(produtoDTO.getPreco());
			produto.setImagem(produtoDTO.getImagem());
			produto.setAtivo(produtoDTO.isAtivo());
			produto.setPrecoPromocional(produtoDTO.getPrecoPromocional());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ - O Mistério do 666: " +  produtoDTO.getPontuacaoFidelidade());
			produto.setPontuacaoFidelidade(produtoDTO.getPontuacaoFidelidade());
			produto.setDisponibilidadeFidelidade(produtoDTO.getDisponibilidadeFidelidade());
			produto.setIdProduto(produtoDTO.getIdProduto());
		}
		return produto;
	}

	public static ProdutoDTO converterEntidadeEmDTO(Produto produto)
	{	
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if(produto != null)
		{
			produtoDTO.setNome(produto.getNome());
			produtoDTO.setDescricao(produto.getDescricao());
			produtoDTO.setPreco(produto.getPreco());
			produtoDTO.setImagem(produto.getImagem());
			produtoDTO.setAtivo(produto.isAtivo());
			produtoDTO.setPrecoPromocional(produto.getPrecoPromocional());
			produtoDTO.setPontuacaoFidelidade(produto.getPontuacaoFidelidade());
			produtoDTO.setDisponibilidadeFidelidade(produto.getDisponibilidadeFidelidade());
		}
		return produtoDTO;
	}

	public static ProdutoDTO converterEntidadeEmDTOComId(Produto produto)
	{	
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if(produto != null)
		{
			produtoDTO.setNome(produto.getNome());
			produtoDTO.setDescricao(produto.getDescricao());
			produtoDTO.setPreco(produto.getPreco());
			produtoDTO.setImagem(produto.getImagem());
			produtoDTO.setAtivo(produto.isAtivo());
			produtoDTO.setPrecoPromocional(produto.getPrecoPromocional());
			produtoDTO.setPontuacaoFidelidade(produto.getPontuacaoFidelidade());
			produtoDTO.setDisponibilidadeFidelidade(produto.getDisponibilidadeFidelidade());
			produtoDTO.setIdProduto(produto.getIdProduto());
		}
		return produtoDTO;
	}

	public static List<ProdutoDTO> converterEntidadesSetEmDTOsList(Set<Produto> produtosSet)
	{
		List<ProdutoDTO> produtosDTOList = new ArrayList<ProdutoDTO>();
		if(produtosSet != null)
		{
			for(Produto produto : produtosSet)
			{
				produtosDTOList.add(converterEntidadeEmDTO(produto));
			}
		}
		return produtosDTOList;
	}

	public static Set<Produto> converterDTOsListEmEntidadesSet(List<ProdutoDTO> produtosDTOList)
	{
		Set<Produto> produtosSet = new HashSet<Produto>(0);
		if(produtosDTOList != null)
		{
			for(ProdutoDTO produtoDTO : produtosDTOList)
			{
				produtosSet.add(converterDTOEmEntidadeComId(produtoDTO));
			}
		}
		return produtosSet;
	}

	public static List<ProdutoDTO> converterEntidadesListEmDTOsList(List<Produto> produtosList)
	{
		List<ProdutoDTO> produtosDTOList = new ArrayList<ProdutoDTO>();
		if(produtosList != null)
		{
			for(Produto produto : produtosList)
			{
				produtosDTOList.add(converterEntidadeEmDTOComId(produto));
			}
		}
		return produtosDTOList;
	}
}
