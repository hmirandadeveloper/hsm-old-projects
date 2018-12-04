package entidade;

// Generated 17/09/2013 17:24:37 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItemPedido generated by hbm2java
 */
@Entity
@Table(name = "tb_item_pedido", catalog = "dbfastlunch")
public class ItemPedido implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idItemPedido;
	private CardapioProduto cardapioProduto;
	private Pedido pedido;
	private int quantidade;

	public ItemPedido() {
	}

	public ItemPedido(Long idItemPedido, CardapioProduto cardapioProduto,
			Pedido pedido, int quantidade) {
		this.idItemPedido = idItemPedido;
		this.cardapioProduto = cardapioProduto;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_item_pedido", unique = true, nullable = false)
	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name = "id_cardapio_produto", nullable = false)
	public CardapioProduto getCardapioProduto() {
		return this.cardapioProduto;
	}

	public void setCardapioProduto(CardapioProduto cardapioProduto) {
		this.cardapioProduto = cardapioProduto;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_pedido", nullable = false)
	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Column(name = "quantidade", nullable = false)
	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
