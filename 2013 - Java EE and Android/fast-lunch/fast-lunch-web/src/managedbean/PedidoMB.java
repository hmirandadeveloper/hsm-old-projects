package managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import negocio.exception.CpfInvalidoException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import constantes.EOrigemPedido;
import dto.ClienteDTO;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.ItemPedidoDTO;
import dto.PedidoDTO;
import dto.TipoPagamentoDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IClienteFachada;
import fachada.IPedidoFachada;

@ManagedBean
@SessionScoped
public class PedidoMB {
	
	@EJB
	private IPedidoFachada pedidoSB;
	@EJB
	private IClienteFachada clienteSB;
	
	private PedidoDTO pedidoDTO;
	private PedidoDTO pedidoDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	private List<ItemPedidoDTO> itemPedidoDTOs;
	private String cpfCliente;
	private Date data;
	private ItemPedidoDTO itemPedidoDTO;
	private TipoPagamentoDTO tipoPagamentoDTO;
	
	public PedidoMB()
	{
		getEstabelecimentoDTO();
		getFuncionarioLogado();
	}
		
	public String salvarPedido()
	{
		System.out.println("Salvando Pedido... ");
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			
			this.pedidoDTO.setFuncionario(getFuncionarioLogado());
			this.pedidoDTO.setEstabelecimento(getEstabelecimentoDTO());
			this.pedidoDTO.setDataPedido(this.data);
			this.pedidoDTO.setValorTotal(getValorTotal());
			this.pedidoDTO.setOrigemPedido(EOrigemPedido.LOCAL);
			
			//Exibição de Dados:
			System.out.println("Funcionario: " + this.pedidoDTO.getFuncionario().getIdFuncionario());
			System.out.println("Funcionario: " + this.pedidoDTO.getFuncionario().getNome());
			System.out.println("Cliente: " + this.pedidoDTO.getCliente().getIdCliente());
			System.out.println("Cliente: " + this.pedidoDTO.getCliente().getNome());
			System.out.println("Estabelecimento: " + this.pedidoDTO.getEstabelecimento().getIdEstabelecimento());
			System.out.println("Estabelecimento: " + this.pedidoDTO.getEstabelecimento().getRazaoSocial());
			System.out.println("Data : " + this.pedidoDTO.getDataPedido());
			System.out.println("Valor Total do Pedido : " + this.pedidoDTO.getValorTotal());
			System.out.println("Total de Itens: " + this.pedidoDTO.getItemPedidos().size());
			System.out.println("Exibindo Itens do Pedido: ");
			if(this.pedidoDTO.getItemPedidos().size() > 0)
			{
				for(ItemPedidoDTO ip: this.pedidoDTO.getItemPedidos())
				{
					System.out.println("ID Cardapio produto: " + ip.getCardapioProduto().getIdcardapioProduto());
					System.out.println("Produto: " + ip.getCardapioProduto().getProduto().getNome());
					System.out.println("Cardapio: " + ip.getCardapioProduto().getCardapio().getNome());
					System.out.println("Quantidade: " + ip.getQuantidade());
					System.out.println("Total: " + (ip.getCardapioProduto().getProduto().getPreco() * ip.getQuantidade()));
				}
			}
			
			
			this.pedidoSB.salvar(this.pedidoDTO);
			enviarMenssagemInformativa(FastLunchMensagem.MSG_CADASTRO_SUCESSO);
			
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		this.itemPedidoDTOs = null;
		this.pedidoDTO = null;
		this.data = null;
		return "";
	}
	
	public void atualizarPedido(Long id)
	{
		this.pedidoDTO = 
				this.pedidoSB.buscar(id);
	}
	
	public String atualizarPedido()
	{
		try
		{
			this.pedidoSB.atualizar(this.pedidoDTO);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.pedidoDTO = null;
		
		return "";
	}
	
	public void removerPedido(Long id)
	{
		System.out.println("ID do Pedido Selecionado: " + id);
		this.pedidoDTOSelecionado = 
				this.pedidoSB.buscar(id);
		removerPedido();
	}
	
	public String removerPedido()
	{
		try
		{
			this.pedidoSB.remover(pedidoDTOSelecionado.getIdPedido(), getFuncionarioLogado().getUsuario().getTipoUsuario());
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.pedidoDTOSelecionado = null;
		
		return "";
	}
	
	private boolean atributoNaoPreenchido()
	{
		return true;
	}
	
	public String getUsuarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
		System.out.println("Carregando ID..." + ((UsuarioDTO)session.getAttribute("usuario_id")).getIdUsuario());
		
		getFuncionarioLogado();
		
		return ((UsuarioDTO)session.getAttribute("usuario_id")).getLogin();
	}
	
	
	public String buscarCliente()
	{
		System.out.println("Buscar Cliente pelo CPF: " + cpfCliente);
		if(!this.cpfCliente.equals("") || this.cpfCliente != null)
		{
			ClienteDTO clienteDTO = null;
			
			try 
			{
				clienteDTO = this.clienteSB.buscaPeloCpf(this.cpfCliente);

			} catch (CpfInvalidoException e) {
				e.printStackTrace();
			}

			this.pedidoDTO.setCliente(clienteDTO);
		}
		return "";
	}
	
	public String adicionarProdutoNocarrinho()
	{
		if(!this.itemPedidoDTO.getCardapioProduto().getProduto().getNome().equals(""))
		{
			if(this.itemPedidoDTO.getQuantidade() > 0)
			{
				if(!this.pedidoDTO.getItemPedidos().contains(this.itemPedidoDTO))
				{
					this.itemPedidoDTO.setPedido(this.pedidoDTO);
					this.pedidoDTO.getItemPedidos().add(this.itemPedidoDTO);
					this.itemPedidoDTO = null;
					enviarMenssagemInformativa(FastLunchMensagem.MSG_ITEM_ADICIONADO);
				}
				else
				{
					enviarMenssagemErro(FastLunchMensagem.MSG_INFORMACAO_DUPLICADA);
				}
			}
			else
			{
				enviarMenssagemErro(FastLunchMensagem.MSG_QUANTIDADE_VAZIA);
			}
		}
		else
		{
			enviarMenssagemErro(FastLunchMensagem.MSG_INFORMACAO_VAZIA);
		}
		
		return "";
	}
	
	public String removerProdutoDoCarrinho(ItemPedidoDTO itemPedidoRemover)
	{
		this.pedidoDTO.getItemPedidos().remove(itemPedidoRemover);
		
		enviarMenssagemInformativa(FastLunchMensagem.MSG_ITEM_REMOVIDO);
		
		return "";
	}
	
	public String adicionarTipoDePagamento()
	{
		if(!this.tipoPagamentoDTO.getDescricao().equals(""))
		{
			if(!this.pedidoDTO.getTipoPagamentos().contains(this.tipoPagamentoDTO))
			{
				this.pedidoDTO.getTipoPagamentos().add(this.tipoPagamentoDTO);
				this.tipoPagamentoDTO = null;
				enviarMenssagemInformativa(FastLunchMensagem.MSG_ITEM_ADICIONADO);
			}
			else
			{
				enviarMenssagemErro(FastLunchMensagem.MSG_INFORMACAO_DUPLICADA);
			}
		}
		else
		{
			enviarMenssagemErro(FastLunchMensagem.MSG_INFORMACAO_VAZIA);
		}
		
		return "";
	}
	
	public String removerTipoPagamento(TipoPagamentoDTO tipoPagamentoRemover)
	{
		this.pedidoDTO.getTipoPagamentos().remove(tipoPagamentoRemover);
		
		enviarMenssagemInformativa(FastLunchMensagem.MSG_ITEM_REMOVIDO);
		return "";
	}
	
	public double getValorTotal()
	{
		double valorTotalPedido = 0;
		for(ItemPedidoDTO ip : this.pedidoDTO.getItemPedidos())
		{
			valorTotalPedido = (valorTotalPedido + ((
					ip.getCardapioProduto().getProduto().getPrecoPromocional() > 0 ? 
							ip.getCardapioProduto().getProduto().getPrecoPromocional() : 
								ip.getCardapioProduto().getProduto().getPreco()
								) * ip.getQuantidade()));
		}
		
		return valorTotalPedido;
	}
	
	private FacesContext getContext()
	{
		return FacesContext.getCurrentInstance();
	}
	
	private void enviarMenssagemInformativa(String msg)
	{
		FacesContext context = getContext();
		
		context.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	private void enviarMenssagemAlerta(String msg)
	{
		FacesContext context = getContext();
		
		context.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_WARN, msg, msg));
	}
	
	private void enviarMenssagemErro(String msg)
	{
		FacesContext context = getContext();
		
		context.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	private void enviarMenssagemErroGrave(String msg)
	{
		FacesContext context = getContext();
		
		context.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_FATAL, msg, msg));
	}
	
	public FuncionarioDTO getFuncionarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = 
				(HttpSession)context.getExternalContext().getSession(false);
		
		return 	((FuncionarioDTO)session.getAttribute("funcionario"));
	}
	
	public List<PedidoDTO> getPedidos()
	{
		return this.pedidoSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public PedidoDTO getPedidoDTO() {
		if(this.pedidoDTO == null)
		{
			this.pedidoDTO = FastLunchDTOFactory.
			getPedidoDTOAtributosCarregados();
	
		}
		
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		if(this.estabelecimentoDTO == null)
		{
			this.estabelecimentoDTO = getFuncionarioLogado().getCargo().getEstabelecimento();
		}
		
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public PedidoDTO getPedidoDTOSelecionado() {
		if(this.pedidoDTOSelecionado == null)
		{
			this.pedidoDTOSelecionado = FastLunchDTOFactory.
			getPedidoDTOAtributosCarregados();
	
		}
		
		return pedidoDTOSelecionado;
	}

	public void setPedidoDTOSelecionado(PedidoDTO pedidoDTOSelecionado) {
		this.pedidoDTOSelecionado = pedidoDTOSelecionado;
	}

	public List<ItemPedidoDTO> getItemPedidoDTOs() {
		if(this.itemPedidoDTOs == null)
		{
			this.itemPedidoDTOs = new ArrayList<ItemPedidoDTO>();
		}
		return itemPedidoDTOs;
	}

	public void setItemPedidoDTOs(List<ItemPedidoDTO> itemPedidoDTOs) {
		this.itemPedidoDTOs = itemPedidoDTOs;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Date getData() {
		if(this.data == null)
		{
			this.data = new Date();
		}
		
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public ItemPedidoDTO getItemPedidoDTO() {
		if(this.itemPedidoDTO == null)
		{
			this.itemPedidoDTO = FastLunchDTOFactory.getItemPedidoDTO();
		}
		
		return itemPedidoDTO;
	}

	public void setItemPedidoDTO(ItemPedidoDTO itemPedidoDTO) {
		this.itemPedidoDTO = itemPedidoDTO;
	}

	public TipoPagamentoDTO getTipoPagamentoDTO() {
		return tipoPagamentoDTO;
	}

	public void setTipoPagamentoDTO(TipoPagamentoDTO tipoPagamentoDTO) {
		this.tipoPagamentoDTO = tipoPagamentoDTO;
	}
}
