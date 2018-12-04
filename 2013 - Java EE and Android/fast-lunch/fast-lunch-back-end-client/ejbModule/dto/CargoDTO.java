package dto;

import java.util.ArrayList;
import java.util.List;

public class CargoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idCargo;
	private EstabelecimentoDTO estabelecimentoDTO;
	private String descricao;
	private Boolean ativo;
	private List<FuncionarioDTO> funcionarioDTOs = new ArrayList<FuncionarioDTO>();

	public CargoDTO() {
	}

	public CargoDTO(EstabelecimentoDTO estabelecimentoDTO, String descricao) {
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.descricao = descricao;
	}

	public CargoDTO(EstabelecimentoDTO estabelecimentoDTO, String descricao,
			Boolean ativo, List<FuncionarioDTO> funcionarioDTOs) {
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.descricao = descricao;
		this.ativo = ativo;
		this.funcionarioDTOs = funcionarioDTOs;
	}

	public Long getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return this.estabelecimentoDTO;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<FuncionarioDTO> getFuncionarios() {
		return this.funcionarioDTOs;
	}

	public void setFuncionarios(List<FuncionarioDTO> funcionarioDTOs) {
		this.funcionarioDTOs = funcionarioDTOs;
	}

	@Override
	public boolean equals(Object obj) {
		
		System.out.println("Comparação Cargo...");
		
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof CargoDTO))
		{
			return false;
		}
		CargoDTO outro = (CargoDTO) obj;
		if(descricao == null)
		{
			if(outro.getDescricao() != null)
			{
				return false;
			}
		}
		else if(!descricao.equals(outro.getDescricao()))
		{
			return false;
		}
		
		return true;
	}
}
