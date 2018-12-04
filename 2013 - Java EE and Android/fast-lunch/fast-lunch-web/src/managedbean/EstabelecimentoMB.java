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
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IEstabelecimentoFachada;

@ManagedBean
@SessionScoped
public class EstabelecimentoMB {
	
	@EJB
	private IEstabelecimentoFachada estabelecimentoSB;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	private EstabelecimentoDTO estabelecimentoDTOSelecionado;
		
	public EstabelecimentoMB()
	{
		getEstabelecimentoDTO();
		this.estabelecimentoDTOSelecionado = getFuncionarioLogado().getCargo().getEstabelecimento();
	}
		
	public String salvarEstabelecimento()
	{
		System.out.println("Salvando Estabelecimento... ");
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			System.out.println("Funcionario: " + getFuncionarioLogado());
			if(getFuncionarioLogado().getUsuario().getTipoUsuario() == ETipoUsuario.TEMP)
			{
				this.estabelecimentoSB.salvar(this.estabelecimentoDTO, getFuncionarioLogado());
				enviarMenssagemInformativa(FastLunchMensagem.MSG_CADASTRO_SUCESSO);
				logout();
			}
			else
			{
				enviarMenssagemErro("Usuário não tem permissão para realizar esta operação!");
			}
			
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
		this.estabelecimentoDTO = null;
		return "";
	}
	
	public void atualizarEstabelecimento(Long id)
	{
		this.estabelecimentoDTOSelecionado = 
				this.estabelecimentoSB.buscar(id);
	}
	
	public String atualizarEstabelecimento()
	{
		try
		{
			this.estabelecimentoSB.atualizar(this.estabelecimentoDTOSelecionado);
			
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
		
		return "";
	}
	
	public void removerEstabelecimento(Long id)
	{
		System.out.println("ID do Estabelecimento Selecionado: " + id);
		this.estabelecimentoDTOSelecionado = 
				this.estabelecimentoSB.buscar(id);
		System.out.println("Nome Estabelecimento selecionado: " + this.estabelecimentoDTOSelecionado.getRazaoSocial());
		removerEstabelecimento();
	}
	
	public String removerEstabelecimento()
	{
		try
		{
			this.estabelecimentoSB.remover(estabelecimentoDTOSelecionado.getIdEstabelecimento(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.estabelecimentoDTOSelecionado = null;
		
		return "";
	}
	
	public void buscarEstabelecimentoPeloCnpj()
	{		
		try {
			this.estabelecimentoDTO = this.estabelecimentoSB.
					buscarEstabelecimentoPorCnpj(this.estabelecimentoDTO.getCnpj());
			
		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
	}	

	
	private boolean atributoNaoPreenchido()
	{
		return true;
	}
	
	private FuncionarioDTO getFuncionarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = 
				(HttpSession)context.getExternalContext().getSession(false);
		
		return 	((FuncionarioDTO)session.getAttribute("funcionario"));
	}
	
	public String getUsuarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
		System.out.println("Carregando ID..." + ((UsuarioDTO)session.getAttribute("usuario_id")).getIdUsuario());
				
		return ((UsuarioDTO)session.getAttribute("usuario_id")).getLogin();
	}
	
	private void logout()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		session.invalidate();
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
	
	public List<EstabelecimentoDTO> getEstabelecimentos()
	{
		return this.estabelecimentoSB.buscarTodos();
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		if(this.estabelecimentoDTO == null)
		{
			this.estabelecimentoDTO = FastLunchDTOFactory.
			getEstabelecimentoDTO();
	
		}
		
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public EstabelecimentoDTO getEstabelecimentoDTOSelecionado() {
		if(this.estabelecimentoDTOSelecionado == null)
		{
			this.estabelecimentoDTOSelecionado = FastLunchDTOFactory.
			getEstabelecimentoDTO();
	
		}
		
		return estabelecimentoDTOSelecionado;
	}

	public void setEstabelecimentoDTOSelecionado(EstabelecimentoDTO estabelecimentoDTOSelecionado) {
		this.estabelecimentoDTOSelecionado = estabelecimentoDTOSelecionado;
	}
	
}
