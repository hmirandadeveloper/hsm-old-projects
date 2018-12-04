package managedbean;

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
import negocio.exception.EntidadeJaCadastradaException;
import constantes.ETipoUsuario;
import dto.ClienteDTO;
import dto.EnderecoDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IClienteFachada;

@ManagedBean
@SessionScoped
public class ClienteMB {
	
	@EJB
	private IClienteFachada clienteSB;
	
	private ClienteDTO clienteDTO;
	
	private EnderecoDTO enderecoDTO = FastLunchDTOFactory.getEnderecoDTO();
	private EnderecoDTO enderecoDTOSelecionado = FastLunchDTOFactory.getEnderecoDTO();
	
	public String salvarCliente()
	{
		System.out.println("Salvando Cliente... ");
		try
		{
			if(atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.clienteDTO.getEnderecos().add(enderecoDTO);
			this.clienteDTO.setDataNascimento(new Date());
			this.clienteDTO.getUsuario().setTipoUsuario(ETipoUsuario.USER);
			this.clienteSB.salvar(this.clienteDTO);
			enviarMenssagemInformativa(FastLunchMensagem.MSG_CADASTRO_SUCESSO);
			
		} catch(EntidadeJaCadastradaException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (EntidadeAtributoIncompletoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (CpfInvalidoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		this.clienteDTO = null;
		return "";
	}
	
	public String atualizarCliente()
	{
		try
		{
			this.clienteSB.atualizar(getClienteDTOLogado());
			enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (CpfInvalidoException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}

		return "";
	}
	
	public String removerCliente()
	{
		try
		{
			this.clienteSB.remover(clienteDTO.getIdCliente(), ETipoUsuario.USER);
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		return "";
	}
	
	public String removerEndereco(Long id)
	{
		System.out.println("ID do Endereço a ser Removido: " + id);
		EnderecoDTO enderecoDTORemover = FastLunchDTOFactory.getEnderecoDTO();
		enderecoDTORemover.setIdEndereco(id);
		System.out.println();
		if(getClienteDTOLogado().getEnderecos().indexOf(enderecoDTORemover) > 0)
		{
			getClienteDTOLogado().getEnderecos().remove(enderecoDTORemover);
		}
		else
		{
			System.out.println("Não pode ser removido!");
		}
		
		return "";
	}
	
	public String adicionarEndereco()
	{
		
		System.out.println("Endereço: " + enderecoDTO);
		System.out.println("Endereço: " + enderecoDTO.getBairro());
		
		getClienteDTOLogado().getEnderecos().add(this.enderecoDTO);
		
		this.enderecoDTO = FastLunchDTOFactory.getEnderecoDTO();
		
		return "";
	}
	
	public void buscarClientePeloUsuario()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
		try {
			this.clienteDTO = this.clienteSB.buscaPeloUsuario(
					((UsuarioDTO)session.getAttribute("usuario")).getIdUsuario()
					);
			this.enderecoDTO = clienteDTO.getEnderecos().get(0);
			
		} catch (EntidadeInexistenteException e) {
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
	}
	
	public void buscarClientePeloCpf()
	{		
		try {
			this.clienteDTO = this.clienteSB.buscaPeloCpf(clienteDTO.getCpf());
			
		}catch (CpfInvalidoException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
	}
	
	public void buscarClientePeloEmail()
	{		
		try {
			this.clienteDTO = this.clienteSB.buscaPeloEmail(clienteDTO.getEmail());
			
		}catch (EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
	}
	
	public ClienteDTO getClienteDTOLogado()
	{
		FacesContext context = getContext();
		HttpSession session = 
				(HttpSession)context.getExternalContext().getSession(false);
		
		return 	((ClienteDTO)session.getAttribute("cliente"));
	}
	
	private boolean atributoNaoPreenchido()
	{
		return true;
	}
	
	public String getUsuarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
		System.out.println("Carregando ID..." + ((UsuarioDTO)session.getAttribute("usuario")).getIdUsuario());
		
		buscarClientePeloUsuario();
		
		return ((UsuarioDTO)session.getAttribute("usuario")).getLogin();
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
	
	public List<ClienteDTO> getClientes()
	{
		return this.clienteSB.buscarTodos();
	}

	public ClienteDTO getClienteDTO() {
		if(this.clienteDTO == null)
		{
			this.clienteDTO = FastLunchDTOFactory.
			getClienteDTOAtributosCarregados();
	
		}
		
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	public EnderecoDTO getEnderecoDTOSelecionado() {
		if(this.enderecoDTOSelecionado == null)
		{
			enderecoDTOSelecionado = FastLunchDTOFactory.getEnderecoDTO();
		}
		return enderecoDTOSelecionado;
	}

	public void setEnderecoDTOSelecionado(EnderecoDTO enderecoDTOSelecionado) {
		this.enderecoDTOSelecionado = enderecoDTOSelecionado;
	}
}
