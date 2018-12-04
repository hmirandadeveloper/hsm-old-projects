package entidade;

// Generated 15/09/2013 23:36:41 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * TipoPagamento generated by hbm2java
 */
@Entity
@Table(name = "tb_tipo_pagamento", catalog = "dbfastlunch")
@NamedQueries({
	@NamedQuery(name="TipoPagamento.buscaPorDescricao",
			query="SELECT tp " +
					"FROM TipoPagamento tp, Estabelecimento e " +
			"WHERE tp.descricao = :descricao" +
			" AND tp.estabelecimento.idEstabelecimento = e.idEstabelecimento" +
			" AND e.idEstabelecimento = :idEstabelecimento" +
			" AND tp.ativo = true"),
			
	@NamedQuery(name="TipoPagamento.buscaTipoPagamentosPorEstabelecimento",
			query="SELECT tp " +
					"FROM TipoPagamento tp, Estabelecimento e " +
					"WHERE tp.estabelecimento.idEstabelecimento = e.idEstabelecimento" +
					" AND e.idEstabelecimento = :idEstabelecimento" +
					" AND tp.ativo = true")
})
public class TipoPagamento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String BUSCA_TIPO_PAGAMENTO_POR_DESCRICAO = "TipoPagamento.buscaPorDescricao";
	public static final String BUSCA_TIPO_PAGAMENTOS_POR_ESTABELECIMENTO = "TipoPagamento.buscaTipoPagamentosPorEstabelecimento";
	
	private Long idTipoPagamento;
	private String descricao;
	private Boolean ativo;
	private Set<Pedido> pedidos = new HashSet<Pedido>(0);
	private Estabelecimento estabelecimento;

	public TipoPagamento() {
	}

	public TipoPagamento(String descricao, Boolean ativo,
			Set<Pedido> pedidos, Estabelecimento estabelecimento) {
		this.descricao = descricao;
		this.ativo = ativo;
		this.pedidos = pedidos;
		this.estabelecimento = estabelecimento;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_tipo_pagamento", unique = true, nullable = false)
	public Long getIdTipoPagamento() {
		return this.idTipoPagamento;
	}

	public void setIdTipoPagamento(Long idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}

	@Column(name = "descricao", length = 50)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "ativo")
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tipoPagamentos")
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estabelecimento",nullable = false)
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

}
