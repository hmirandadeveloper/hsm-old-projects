package dto;

import java.util.ArrayList;
import java.util.List;


public class FuncionarioDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idFuncionario;
	private UsuarioDTO usuarioDTO;
	private CargoDTO cargoDTO;
	private String nome;
	private String sobrenome;
	private String matricula;
	private Boolean ativo;
	private List<PedidoDTO> pedidoDTOs = new ArrayList<PedidoDTO>();

	public FuncionarioDTO() {
	}

	public FuncionarioDTO(UsuarioDTO usuarioDTO, CargoDTO cargoDTO, String nome,
			String sobrenome, String matricula) {
		this.usuarioDTO = usuarioDTO;
		this.cargoDTO = cargoDTO;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.matricula = matricula;
	}

	public FuncionarioDTO(UsuarioDTO usuarioDTO, CargoDTO cargoDTO, String nome,
			String sobrenome, String matricula, Boolean ativo,
			List<PedidoDTO> pedidoDTOs) {
		this.usuarioDTO = usuarioDTO;
		this.cargoDTO = cargoDTO;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.matricula = matricula;
		this.ativo = ativo;
		this.pedidoDTOs = pedidoDTOs;
	}


	public Long getIdFuncionario() {
		return this.idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public UsuarioDTO getUsuario() {
		return this.usuarioDTO;
	}

	public void setUsuario(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public CargoDTO getCargo() {
		return this.cargoDTO;
	}

	public void setCargo(CargoDTO cargoDTO) {
		this.cargoDTO = cargoDTO;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<PedidoDTO> getPedidos() {
		return this.pedidoDTOs;
	}

	public void setPedidos(List<PedidoDTO> pedidoDTOs) {
		this.pedidoDTOs = pedidoDTOs;
	}

}
