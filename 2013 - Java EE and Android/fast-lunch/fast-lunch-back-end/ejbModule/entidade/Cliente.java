package entidade;

// Generated 15/09/2013 23:36:41 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Cliente generated by hbm2java
 */
@Entity
@Table(name = "tb_cliente", catalog = "dbfastlunch", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "cpf") })
@NamedQueries({
	@NamedQuery(name="Cliente.buscaPorCpf", 
				query="SELECT c " +
				"FROM Cliente c WHERE c.cpf = :cpf" +
				" AND c.ativo = true"),
				
	@NamedQuery(name="Cliente.buscaPorEmail",
				query="SELECT c " +
				"FROM Cliente c WHERE c.email = :email" +
				" AND c.ativo = true"),
				
	@NamedQuery(name="Cliente.buscaPorUsuario",
	query="SELECT c " +
		"FROM Cliente c, Usuario u " +
		"WHERE c.usuario.idUsuario = u.idUsuario" +
		" AND u.idUsuario = :idUsuario" +
		" AND c.ativo = true")
})
public class Cliente implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String BUSCA_CLIENTE_POR_CPF = "Cliente.buscaPorCpf";
	public static final String BUSCA_CLIENTE_POR_EMAIL = "Cliente.buscaPorEmail";
	public static final String BUSCA_CLIENTE_POR_USUARIO = "Cliente.buscaPorUsuario";
	
	private Long idCliente;
	private Usuario usuario;
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
	private Set<Endereco> enderecos = new HashSet<Endereco>(0);
	private Set<Pedido> pedidos = new HashSet<Pedido>(0);
	private Set<FidelidadeTroca> fidelidadeTrocas = new HashSet<FidelidadeTroca>(
			0);

	public Cliente() {
	}

	public Cliente(Usuario usuario, String nome,
			String sobrenome, String cpf, String telefone, String email,
			Date dataNascimento, char sexo, boolean ativo, Date dataCadastro) {
		this.usuario = usuario;
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

	public Cliente(Usuario usuario, String nome,
			String sobrenome, String cpf, String telefone, String celular,
			String email, Date dataNascimento, char sexo, boolean ativo,
			Date dataCadastro, Integer pontuacaoFidelidade,
			Set<Endereco> enderecos, Set<Pedido> pedidos,
			Set<FidelidadeTroca> fidelidadeTrocas) {
		this.usuario = usuario;
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
		this.enderecos = enderecos;
		this.pedidos = pedidos;
		this.fidelidadeTrocas = fidelidadeTrocas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_cliente", unique = true, nullable = false)
	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_usuario",nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "nome", nullable = false, length = 50)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "sobrenome", nullable = false, length = 150)
	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Column(name = "cpf", unique = true, nullable = false, length = 11)
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "telefone", nullable = false, length = 11)
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "celular", length = 11)
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "email", unique = true, nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false, length = 10)
	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(name = "sexo", nullable = false, length = 1)
	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Column(name = "ativo", nullable = false)
	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false, length = 10)
	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Column(name = "pontuacao_fidelidade")
	public Integer getPontuacaoFidelidade() {
		return this.pontuacaoFidelidade;
	}

	public void setPontuacaoFidelidade(Integer pontuacaoFidelidade) {
		this.pontuacaoFidelidade = pontuacaoFidelidade;
	}

	
	@ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "tb_cliente_endereco", catalog = "dbfastlunch", joinColumns = { @JoinColumn(name = "id_cliente", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_endereco", nullable = false, updatable = false) })
	public Set<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade=CascadeType.ALL)
	public Set<FidelidadeTroca> getFidelidadeTrocas() {
		return this.fidelidadeTrocas;
	}

	public void setFidelidadeTrocas(Set<FidelidadeTroca> fidelidadeTrocas) {
		this.fidelidadeTrocas = fidelidadeTrocas;
	}

}
