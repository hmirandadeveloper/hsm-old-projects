package dto;

import java.util.ArrayList;
import java.util.List;

public class TipoCardapioDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idTipoCardapio;
	private String nome;
	private String descricao;
	private List<CardapioDTO> cardapios = new ArrayList<CardapioDTO>();
	private EstabelecimentoDTO estabelecimentoDTO;
	private boolean ativo;

	public TipoCardapioDTO() {
	}

	public TipoCardapioDTO(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public TipoCardapioDTO(String nome, String descricao,
			List<CardapioDTO> cardapios,
			EstabelecimentoDTO estabelecimentoDTO, boolean ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.cardapios = cardapios;
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.ativo = ativo;
	}

	public Long getIdTipoCardapio() {
		return this.idTipoCardapio;
	}

	public void setIdTipoCardapio(Long idTipoCardapio) {
		this.idTipoCardapio = idTipoCardapio;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<CardapioDTO> getCardapios() {
		return this.cardapios;
	}

	public void setCardapios(List<CardapioDTO> cardapios) {
		this.cardapios = cardapios;
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public boolean equals(Object obj) {
		
		System.out.println("Comparação Cardapio...");
		
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof TipoCardapioDTO))
		{
			return false;
		}
		TipoCardapioDTO outro = (TipoCardapioDTO) obj;
		if(nome == null)
		{
			if(outro.getNome() != null)
			{
				return false;
			}
		}
		else if(!nome.equals(outro.getNome()))
		{
			return false;
		}
		
		return true;
	}
}
