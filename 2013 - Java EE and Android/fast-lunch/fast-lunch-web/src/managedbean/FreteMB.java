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
import dto.FreteDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IFreteFachada;

@ManagedBean
@SessionScoped
public class FreteMB {
	@EJB
	private IFreteFachada freteSB;
	
	private FreteDTO freteDTO;
	private FreteDTO freteDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public FreteMB()
	{
		getEstabelecimentoDTO();
	}
		
	public String salvarFrete()
	{
		System.out.println("Salvando Frete... ");
		
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.freteDTO.setEstabelecimentoDTO(this.estabelecimentoDTO);

			this.freteSB.salvar(this.freteDTO);
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
		this.freteDTO = null;
		return "";
	}
	
	public void atualizarFrete(Long id)
	{
		this.freteDTOSelecionado = 
				this.freteSB.buscar(id);
	}
	
	public String atualizarFrete()
	{
		try
		{
			this.freteSB.atualizar(freteDTOSelecionado);
			
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
		
		this.freteDTOSelecionado = null;
		
		return "";
	}
	
	public void removerFrete(Long id)
	{
		this.freteDTOSelecionado = this.freteSB.buscar(id);
		removerFrete();
	}
	
	public String removerFrete()
	{
		try
		{
			this.freteSB.remover(freteDTOSelecionado.getIdFrete(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.freteDTOSelecionado = null;
		
		return "";
	}
	
	public void buscarFretePeloBairro()
	{		
		try {
			this.freteDTO = this.freteSB.
					buscarFretePorBairro(this.freteDTO.getBairro(), this.estabelecimentoDTO.getIdEstabelecimento());
			
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
	
	public List<FreteDTO> getFretes()
	{
		return this.freteSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public FreteDTO getFreteDTO() {
		if(this.freteDTO == null)
		{
			this.freteDTO = FastLunchDTOFactory.
			getFreteDTO();
	
		}
		
		return freteDTO;
	}

	public void setFreteDTO(FreteDTO freteDTO) {
		this.freteDTO = freteDTO;
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

	public FreteDTO getFreteDTOSelecionado() {
		if(this.freteDTOSelecionado == null)
		{
			this.freteDTOSelecionado = FastLunchDTOFactory.
			getFreteDTO();
	
		}
		
		return freteDTOSelecionado;
	}

	public void setFreteDTOSelecionado(FreteDTO freteDTOSelecionado) {
		this.freteDTOSelecionado = freteDTOSelecionado;
	}
}
