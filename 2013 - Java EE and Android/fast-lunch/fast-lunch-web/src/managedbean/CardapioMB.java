package managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import constantes.ETipoUsuario;
import dto.CardapioDTO;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.ICardapioFachada;

@ManagedBean
@SessionScoped
public class CardapioMB {
	@EJB
	private ICardapioFachada cardapioSB;

	private CardapioDTO cardapioDTO;
	private CardapioDTO cardapioDTOSelecionado;

	private EstabelecimentoDTO estabelecimentoDTO;



	public CardapioMB()
	{
		System.out.println("Classe CardapioMB carregando...");
		getEstabelecimentoDTO();
		System.out.println("Classe CardapioMB carregada!!!");
	}

	public String salvarCardapio()
	{
		System.out.println("Salvando Cardapio... ");

		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			System.out.println("Cardapio: " + cardapioDTO.getTipoCardapio().getNome() + " - " + cardapioDTO.getTipoCardapio().getIdTipoCardapio());
			this.cardapioSB.salvar(this.cardapioDTO);
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
		this.cardapioDTO = null;
		return "";
	}

	public void atualizarCardapio(Long id)
	{
		this.cardapioDTOSelecionado = 
				this.cardapioSB.buscar(id);
	}

	public String atualizarCardapio()
	{
		try
		{
			this.cardapioSB.atualizar(cardapioDTOSelecionado);

			enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);

		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}

		this.cardapioDTOSelecionado = null;

		return "";
	}

	public void removerCardapio(Long id)
	{
		this.cardapioDTOSelecionado = this.cardapioSB.buscar(id);
		removerCardapio();
	}

	public String removerCardapio()
	{
		try
		{
			this.cardapioSB.remover(cardapioDTOSelecionado.getIdCardapio(), ETipoUsuario.ADMIN);

			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);

		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}

		this.cardapioDTOSelecionado = null;

		return "";
	}

	public void buscarCardapioPeloNome()
	{		
		try {
			this.cardapioDTO = this.cardapioSB.
					buscarCardapioPorNome(this.cardapioDTO.getNome(), this.estabelecimentoDTO.getIdEstabelecimento());

		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
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

	public List<CardapioDTO> getCardapios()
	{
		System.out.println("Buscando todos os cardápios...");
		return this.cardapioSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public CardapioDTO getCardapioDTO() {
		if(this.cardapioDTO == null)
		{
			this.cardapioDTO = FastLunchDTOFactory.
					getCardapioDTO();

		}

		return cardapioDTO;
	}

	public void setCardapioDTO(CardapioDTO cardapioDTO) {
		this.cardapioDTO = cardapioDTO;
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

	public CardapioDTO getCardapioDTOSelecionado() {
		if(this.cardapioDTOSelecionado == null)
		{
			this.cardapioDTOSelecionado = FastLunchDTOFactory.
					getCardapioDTO();

		}

		return cardapioDTOSelecionado;
	}

	public void setCardapioDTOSelecionado(CardapioDTO cardapioDTOSelecionado) {
		this.cardapioDTOSelecionado = cardapioDTOSelecionado;
	}
}
