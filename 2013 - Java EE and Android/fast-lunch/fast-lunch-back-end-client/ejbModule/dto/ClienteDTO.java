package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	
	private Long idCliente;
	private UsuarioDTO usuarioDTO;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String telefone;
	private String celular;
	private String email;
	private Date dataNascimento;
	private char sexo;
	private boolean ativo;
	private Date dataCadastro;
	private Integer pontuacaoFidelidade;
	private List<EnderecoDTO> enderecoDTOs = new ArrayList<EnderecoDTO>();
	private List<PedidoDTO> pedidoDTOs = new ArrayList<PedidoDTO>();
	private List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs = new ArrayList<FidelidadeTrocaDTO>();

	public ClienteDTO() {
	}

	public ClienteDTO(UsuarioDTO usuarioDTO, String nome,
			String sobrenome, String cpf, String telefone, String email,
			Date dataNascimento, char sexo, boolean ativo, Date dataCadastro) {
		this.usuarioDTO = usuarioDTO;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.ativo = ativo;
		this.dataCadastro = dataCadastro;
	}

	public ClienteDTO(UsuarioDTO usuarioDTO, String nome,
			String sobrenome, String cpf, String telefone, String celular,
			String email, Date dataNascimento, char sexo, boolean ativo,
			Date dataCadastro, Integer pontuacaoFidelidade,
			List<EnderecoDTO> enderecoDTOs, List<PedidoDTO> pedidoDTOs,
			List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs) {
		this.usuarioDTO = usuarioDTO;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.ativo = ativo;
		this.dataCadastro = dataCadastro;
		this.pontuacaoFidelidade = pontuacaoFidelidade;
		this.enderecoDTOs = enderecoDTOs;
		this.pedidoDTOs = pedidoDTOs;
		this.fidelidadeTrocaDTOs = fidelidadeTrocaDTOs;
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public UsuarioDTO getUsuario() {
		return this.usuarioDTO;
	}

	public void setUsuario(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
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

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getPontuacaoFidelidade() {
		return this.pontuacaoFidelidade;
	}

	public void setPontuacaoFidelidade(Integer pontuacaoFidelidade) {
		this.pontuacaoFidelidade = pontuacaoFidelidade;
	}

	public List<EnderecoDTO> getEnderecos() {
		return this.enderecoDTOs;
	}

	public void setEnderecos(List<EnderecoDTO> enderecoDTOs) {
		this.enderecoDTOs = enderecoDTOs;
	}

	public List<PedidoDTO> getPedidos() {
		return this.pedidoDTOs;
	}

	public void setPedidos(List<PedidoDTO> pedidoDTOs) {
		this.pedidoDTOs = pedidoDTOs;
	}

	public List<FidelidadeTrocaDTO> getFidelidadeTrocas() {
		return this.fidelidadeTrocaDTOs;
	}

	public void setFidelidadeTrocas(List<FidelidadeTrocaDTO> fidelidadeTrocaDTOs) {
		this.fidelidadeTrocaDTOs = fidelidadeTrocaDTOs;
	}

}
