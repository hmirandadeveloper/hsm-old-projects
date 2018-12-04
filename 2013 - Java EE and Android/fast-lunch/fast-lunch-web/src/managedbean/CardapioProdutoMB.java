package managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import constantes.ETipoUsuario;
import dto.CardapioDTO;
import dto.CardapioProdutoDTO;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.ICardapioProdutoFachada;

@ManagedBean
@SessionScoped
public class CardapioProdutoMB {
	@EJB
	private ICardapioProdutoFachada cardapioProdutoSB;

	private CardapioProdutoDTO cardapioProdutoDTO;
	private CardapioProdutoDTO cardapioProdutoDTOSelecionado;
	private CardapioDTO cardapioSelecionado;

	private EstabelecimentoDTO estabelecimentoDTO;



	public CardapioProdutoMB()
	{
		System.out.println("Costruindo Cardapio Produto...");
		getEstabelecimentoDTO();
	}

	public String salvarCardapioProduto()
	{
		System.out.println("Salvando CardapioProduto... ");

		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.cardapioProdutoDTO.getProduto().setDisponibilidadeFidelidade(true);
			this.cardapioProdutoSB.salvar(this.cardapioProdutoDTO);
			enviarMenssagemInformativa(FastLunchMensagem.MSG_CADASTRO_SUCESSO);

		} catch(EntidadeJaCadastradaException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		this.cardapioProdutoDTO = null;
		return "";
	}

	public void atualizarCardapioProduto(Long id)
	{
		this.cardapioProdutoDTOSelecionado = 
				this.cardapioProdutoSB.buscar(id);
	}

	public String atualizarCardapioProduto()
	{
		try
		{
			this.cardapioProdutoSB.atualizar(this.cardapioProdutoDTOSelecionado);

			enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);

		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		} catch (EntidadeJaCadastradaException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}

		this.cardapioProdutoDTOSelecionado = null;

		return "";
	}

	public void removerCardapioProduto(Long id)
	{
		this.cardapioProdutoDTOSelecionado = this.cardapioProdutoSB.buscar(id);
		removerCardapioProduto();
	}

	public String removerCardapioProduto()
	{
		try
		{
			this.cardapioProdutoSB.remover(cardapioProdutoDTOSelecionado.getIdcardapioProduto(), ETipoUsuario.ADMIN);

			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);

		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}

		this.cardapioProdutoDTOSelecionado = null;

		return "";
	}

	public List<CardapioProdutoDTO> getCardapioProdutosPeloNomeProduto()
	{		List<CardapioProdutoDTO> cardapioProdutoDTOs = null;
		try {
			 cardapioProdutoDTOs = this.cardapioProdutoSB.buscarProdutosPeloNome(
				this.estabelecimentoDTO.getIdEstabelecimento(), this.cardapioProdutoDTO.getProduto().getNome());

		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		return cardapioProdutoDTOs;
	}	


	private boolean atributoNaoPreenchido()
	{
		return true;
	}

	private EstabelecimentoDTO getEstabelecimentoUsuariologado()
	{
		FacesContext context = getContext();
		HttpSession session = 
				(HttpSession)context.getExternalContext().getSession(false);

		return 	((FuncionarioDTO)session.getAttribute("funcionario")).
				getCargo().getEstabelecimento();
	}

	public String getUsuarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);

		System.out.println("Carregando ID..." + ((UsuarioDTO)session.getAttribute("usuario_id")).getIdUsuario());

		return ((UsuarioDTO)session.getAttribute("usuario_id")).getLogin();
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

	public List<CardapioProdutoDTO> getCardapioProdutos()
	{
		return this.cardapioProdutoSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}
	
	public List<CardapioProdutoDTO> getProdutos()
	{
		System.out.println("Validando Lista de Produtos...");
		System.out.println("----> Cardapio Selecionado: " + this.cardapioSelecionado.getNome());
		System.out.println("ID do Estabelecimento: " + this.estabelecimentoDTO.getIdEstabelecimento());
		System.out.println(" --- " + this.cardapioProdutoSB.buscarPeloCardapio(this.estabelecimentoDTO.getIdEstabelecimento(), 
				"Bebidas").size());
		return this.cardapioProdutoSB.buscarPeloCardapio(this.estabelecimentoDTO.getIdEstabelecimento(), 
				this.cardapioSelecionado.getNome());
	}
	
	public String atualizarPagina(AjaxBehaviorEvent ev)
	{
		System.out.println("Entrou no método...");
		return "";
	}

	public CardapioProdutoDTO getCardapioProdutoDTO() {
		if(this.cardapioProdutoDTO == null)
		{
			this.cardapioProdutoDTO = FastLunchDTOFactory.
					getCardapioProdutoDTO();

		}

		return cardapioProdutoDTO;
	}

	public void setCardapioProdutoDTO(CardapioProdutoDTO cardapioProdutoDTO) {
		this.cardapioProdutoDTO = cardapioProdutoDTO;
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		if(this.estabelecimentoDTO == null)
		{
			this.estabelecimentoDTO = getEstabelecimentoUsuariologado();
		}

		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public CardapioProdutoDTO getCardapioProdutoDTOSelecionado() {
		if(this.cardapioProdutoDTOSelecionado == null)
		{
			this.cardapioProdutoDTOSelecionado = FastLunchDTOFactory.
					getCardapioProdutoDTO();

		}

		return cardapioProdutoDTOSelecionado;
	}

	public void setCardapioProdutoDTOSelecionado(CardapioProdutoDTO cardapioProdutoDTOSelecionado) {
		this.cardapioProdutoDTOSelecionado = cardapioProdutoDTOSelecionado;
	}

	public CardapioDTO getCardapioSelecionado() {
		if(this.cardapioSelecionado == null)
		{
			this.cardapioSelecionado = FastLunchDTOFactory.getCardapioDTO();
		}
		
		return cardapioSelecionado;
	}

	public void setCardapioSelecionado(CardapioDTO cardapioSelecionado) {
		this.cardapioSelecionado = cardapioSelecionado;
	}
}
