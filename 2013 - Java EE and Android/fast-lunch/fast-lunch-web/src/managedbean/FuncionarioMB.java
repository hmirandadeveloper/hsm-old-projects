package managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeAtributoUnicoDuplicadoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import constantes.ETipoUsuario;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IFuncionarioFachada;

@ManagedBean
@SessionScoped
public class FuncionarioMB {
	
	@EJB
	private IFuncionarioFachada funcionarioSB;
	
	private FuncionarioDTO funcionarioDTO;
	private FuncionarioDTO funcionarioDTOSelecionado;
	private String matriculaAnterior;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public FuncionarioMB()
	{
		getEstabelecimentoDTO();
	}
		
	public String salvarFuncionario()
	{
		System.out.println("Salvando Funcionario... ");
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			
			this.funcionarioSB.salvar(this.funcionarioDTO);
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
		this.funcionarioDTO = null;
		return "";
	}
	
	public void atualizarFuncionario(Long id)
	{
		this.funcionarioDTOSelecionado = 
				this.funcionarioSB.buscar(id);
		this.matriculaAnterior = this.funcionarioDTOSelecionado.getMatricula();
	}
	
	public String atualizarFuncionario()
	{
		try
		{
			this.funcionarioSB.atualizar(this.funcionarioDTOSelecionado, this.matriculaAnterior);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		}catch (EntidadeAtributoUnicoDuplicadoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());	
		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.funcionarioDTOSelecionado = null;
		this.matriculaAnterior = null;
		
		return "";
	}
	
	public void removerFuncionario(Long id)
	{
		System.out.println("ID do Funcionario Selecionado: " + id);
		this.funcionarioDTOSelecionado = 
				this.funcionarioSB.buscar(id);
		System.out.println("Nome Funcionario selecionado: " + this.funcionarioDTOSelecionado.getNome());
		removerFuncionario();
	}
	
	public String removerFuncionario()
	{
		try
		{
			this.funcionarioSB.remover(funcionarioDTOSelecionado.getIdFuncionario(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.funcionarioDTOSelecionado = null;
		
		return "";
	}
	
	public void buscarFuncionarioPeloUsuario()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
		try {
			this.funcionarioDTO = this.funcionarioSB.buscaPeloUsuario(
					((UsuarioDTO)session.getAttribute("usuario_id")).getIdUsuario()
					);
			
		} catch (EntidadeInexistenteException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
	}
	
	public void buscarFuncionarioPelaMatricula()
	{		
		try {
			this.funcionarioDTO = this.funcionarioSB.
					buscarFuncionarioPorMatricula(this.funcionarioDTO.getMatricula(), this.estabelecimentoDTO.getIdEstabelecimento());
			
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
		
		buscarFuncionarioPeloUsuario();
		
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
	
	public FuncionarioDTO getFuncionarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = 
				(HttpSession)context.getExternalContext().getSession(false);
		
		return 	((FuncionarioDTO)session.getAttribute("funcionario"));
	}
	
	public List<FuncionarioDTO> getFuncionarios()
	{
		return this.funcionarioSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public FuncionarioDTO getFuncionarioDTO() {
		if(this.funcionarioDTO == null)
		{
			this.funcionarioDTO = FastLunchDTOFactory.
			getFuncionarioDTOAtributosCarregados();
	
		}
		
		return funcionarioDTO;
	}

	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
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

	public FuncionarioDTO getFuncionarioDTOSelecionado() {
		if(this.funcionarioDTOSelecionado == null)
		{
			this.funcionarioDTOSelecionado = FastLunchDTOFactory.
			getFuncionarioDTOAtributosCarregados();
	
		}
		
		return funcionarioDTOSelecionado;
	}

	public void setFuncionarioDTOSelecionado(FuncionarioDTO funcionarioDTOSelecionado) {
		this.funcionarioDTOSelecionado = funcionarioDTOSelecionado;
	}
}
