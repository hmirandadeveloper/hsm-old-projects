package dto;

import java.util.ArrayList;
import java.util.List;

import constantes.ETipoUsuario;

public class UsuarioDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idUsuario;
	private String login;
	private String senha;
	private ETipoUsuario tipoUsuario;
	private List<FuncionarioDTO> funcionarioDTOs = new ArrayList<FuncionarioDTO>();
	private List<ClienteDTO> clienteDTOs = new ArrayList<ClienteDTO>();

	public UsuarioDTO() {
	}

	public UsuarioDTO(String login, String senha, ETipoUsuario tipoUsuario,
			List<FuncionarioDTO> funcionarioDTOs, List<ClienteDTO> clienteDTOs) {
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.funcionarioDTOs = funcionarioDTOs;
		this.clienteDTOs = clienteDTOs;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ETipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(ETipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<FuncionarioDTO> getFuncionarios() {
		return this.funcionarioDTOs;
	}

	public void setFuncionarios(List<FuncionarioDTO> funcionarioDTOs) {
		this.funcionarioDTOs = funcionarioDTOs;
	}

	public List<ClienteDTO> getClientes() {
		return this.clienteDTOs;
	}

	public void setClientes(List<ClienteDTO> clienteDTOs) {
		this.clienteDTOs = clienteDTOs;
	}

}
