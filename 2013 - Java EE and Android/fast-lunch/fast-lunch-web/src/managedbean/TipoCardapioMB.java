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
import dto.TipoCardapioDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.ITipoCardapioFachada;

@ManagedBean
@SessionScoped
public class TipoCardapioMB {
	@EJB
	private ITipoCardapioFachada tipoCardapioSB;
	
	private TipoCardapioDTO tipoCardapioDTO;
	private TipoCardapioDTO tipoCardapioDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public TipoCardapioMB()
	{
		getEstabelecimentoDTO();
	}
		
	public String salvarTipoCardapio()
	{
		System.out.println("Salvando TipoCardapio... ");
		
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.tipoCardapioDTO.setEstabelecimentoDTO(this.estabelecimentoDTO);

			this.tipoCardapioSB.salvar(this.tipoCardapioDTO);
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
		this.tipoCardapioDTO = null;
		return "";
	}
	
	public void atualizarTipoCardapio(Long id)
	{
		this.tipoCardapioDTOSelecionado = 
				this.tipoCardapioSB.buscar(id);
	}
	
	public String atualizarTipoCardapio()
	{
		try
		{
			this.tipoCardapioSB.atualizar(tipoCardapioDTOSelecionado);
			
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
		
		this.tipoCardapioDTOSelecionado = null;
		
		return "";
	}
	
	public void removerTipoCardapio(Long id)
	{
		this.tipoCardapioDTOSelecionado = this.tipoCardapioSB.buscar(id);
		removerTipoCardapio();
	}
	
	public String removerTipoCardapio()
	{
		try
		{
			this.tipoCardapioSB.remover(tipoCardapioDTOSelecionado.getIdTipoCardapio(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.tipoCardapioDTOSelecionado = null;
		
		return "";
	}
	
	public void buscarTipoCardapioPeloNome()
	{		
		try {
			this.tipoCardapioDTO = this.tipoCardapioSB.
					buscarTipoCardapioPeloNome(this.tipoCardapioDTO.getNome(), this.estabelecimentoDTO.getIdEstabelecimento());
			
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
	
	public List<TipoCardapioDTO> getTipoCardapios()
	{
		return this.tipoCardapioSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public TipoCardapioDTO getTipoCardapioDTO() {
		if(this.tipoCardapioDTO == null)
		{
			this.tipoCardapioDTO = FastLunchDTOFactory.
			getTipoCardapioDTO();
	
		}
		
		return tipoCardapioDTO;
	}

	public void setTipoCardapioDTO(TipoCardapioDTO tipoCardapioDTO) {
		this.tipoCardapioDTO = tipoCardapioDTO;
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

	public TipoCardapioDTO getTipoCardapioDTOSelecionado() {
		if(this.tipoCardapioDTOSelecionado == null)
		{
			this.tipoCardapioDTOSelecionado = FastLunchDTOFactory.
			getTipoCardapioDTO();
	
		}
		
		return tipoCardapioDTOSelecionado;
	}

	public void setTipoCardapioDTOSelecionado(TipoCardapioDTO tipoCardapioDTOSelecionado) {
		this.tipoCardapioDTOSelecionado = tipoCardapioDTOSelecionado;
	}
}
