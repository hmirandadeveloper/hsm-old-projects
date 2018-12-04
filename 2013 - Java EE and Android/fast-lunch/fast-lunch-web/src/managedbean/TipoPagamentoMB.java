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
import dto.TipoPagamentoDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.ITipoPagamentoFachada;

@ManagedBean
@SessionScoped
public class TipoPagamentoMB {
	@EJB
	private ITipoPagamentoFachada tipoPagamentoSB;
	
	private TipoPagamentoDTO tipoPagamentoDTO;
	private TipoPagamentoDTO tipoPagamentoDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public TipoPagamentoMB()
	{
		getEstabelecimentoDTO();
	}
		
	public String salvarTipoPagamento()
	{
		System.out.println("Salvando TipoPagamento... ");
		
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.tipoPagamentoDTO.setEstabelecimentoDTO(this.estabelecimentoDTO);

			this.tipoPagamentoSB.salvar(this.tipoPagamentoDTO);
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
		this.tipoPagamentoDTO = null;
		return "";
	}
	
	public void atualizarTipoPagamento(Long id)
	{
		this.tipoPagamentoDTOSelecionado = 
				this.tipoPagamentoSB.buscar(id);
	}
	
	public String atualizarTipoPagamento()
	{
		try
		{
			this.tipoPagamentoSB.atualizar(tipoPagamentoDTOSelecionado);
			
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
		
		this.tipoPagamentoDTOSelecionado = null;
		
		return "";
	}
	
	public void removerTipoPagamento(Long id)
	{
		this.tipoPagamentoDTOSelecionado = this.tipoPagamentoSB.buscar(id);
		removerTipoPagamento();
	}
	
	public String removerTipoPagamento()
	{
		try
		{
			this.tipoPagamentoSB.remover(tipoPagamentoDTOSelecionado.getIdTipoPagamento(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.tipoPagamentoDTOSelecionado = null;
		
		return "";
	}
	
	public void buscarTipoPagamentoPelaDescricao()
	{		
		try {
			this.tipoPagamentoDTO = this.tipoPagamentoSB.
					buscarTipoPagamentoPelaDescricao(this.tipoPagamentoDTO.getDescricao(), this.estabelecimentoDTO.getIdEstabelecimento());
			
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
	
	public List<TipoPagamentoDTO> getTipoPagamentos()
	{
		return this.tipoPagamentoSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public TipoPagamentoDTO getTipoPagamentoDTO() {
		if(this.tipoPagamentoDTO == null)
		{
			this.tipoPagamentoDTO = FastLunchDTOFactory.
			getTipoPagamentoDTO();
	
		}
		
		return tipoPagamentoDTO;
	}

	public void setTipoPagamentoDTO(TipoPagamentoDTO tipoPagamentoDTO) {
		this.tipoPagamentoDTO = tipoPagamentoDTO;
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

	public TipoPagamentoDTO getTipoPagamentoDTOSelecionado() {
		if(this.tipoPagamentoDTOSelecionado == null)
		{
			this.tipoPagamentoDTOSelecionado = FastLunchDTOFactory.
			getTipoPagamentoDTO();
	
		}
		
		return tipoPagamentoDTOSelecionado;
	}

	public void setTipoPagamentoDTOSelecionado(TipoPagamentoDTO tipoPagamentoDTOSelecionado) {
		this.tipoPagamentoDTOSelecionado = tipoPagamentoDTOSelecionado;
	}
}
