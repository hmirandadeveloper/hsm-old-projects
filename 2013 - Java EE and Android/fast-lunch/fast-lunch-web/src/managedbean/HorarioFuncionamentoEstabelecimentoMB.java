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
import constantes.EDiaSemana;
import constantes.ETipoUsuario;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.HorarioFuncionamentoEstabelecimentoDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IHorarioFuncionamentoEstabelecimentoFachada;

@ManagedBean
@SessionScoped
public class HorarioFuncionamentoEstabelecimentoMB {
	@EJB
	private IHorarioFuncionamentoEstabelecimentoFachada horarioFuncionamentoEstabelecimentoSB;
	
	private HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO;
	private HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public HorarioFuncionamentoEstabelecimentoMB()
	{
		getEstabelecimentoDTO();
	}
		
	public String salvarHorarioFuncionamentoEstabelecimento()
	{
		System.out.println("Salvando Horario de Funcionamento | Estabelecimento... ");
		
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.horarioFuncionamentoEstabelecimentoDTO.setEstabelecimento(this.estabelecimentoDTO);
			
			System.out.println("Acessando camada SB...");
			this.horarioFuncionamentoEstabelecimentoSB.salvar(this.horarioFuncionamentoEstabelecimentoDTO);
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
		this.horarioFuncionamentoEstabelecimentoDTO = null;
		return "";
	}
	
	public void atualizarHorarioFuncionamentoEstabelecimento(Long id)
	{
		this.horarioFuncionamentoEstabelecimentoDTOSelecionado = 
				this.horarioFuncionamentoEstabelecimentoSB.buscar(id);
	}
	
	public String atualizarHorarioFuncionamentoEstabelecimento()
	{
		try
		{
			this.horarioFuncionamentoEstabelecimentoSB.atualizar(horarioFuncionamentoEstabelecimentoDTOSelecionado);
			
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
		
		this.horarioFuncionamentoEstabelecimentoDTOSelecionado = null;
		
		return "";
	}
	
	public void removerHorarioFuncionamentoEstabelecimento(Long id)
	{
		this.horarioFuncionamentoEstabelecimentoDTOSelecionado = this.horarioFuncionamentoEstabelecimentoSB.buscar(id);
		removerHorarioFuncionamentoEstabelecimento();
	}
	
	public String removerHorarioFuncionamentoEstabelecimento()
	{
		try
		{
			this.horarioFuncionamentoEstabelecimentoSB.remover(horarioFuncionamentoEstabelecimentoDTOSelecionado.getIdHorarioFuncionamentoEstabelecimento(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.horarioFuncionamentoEstabelecimentoDTOSelecionado = null;
		
		return "";
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
	
	public EDiaSemana[] getDiasSemana()
	{
		return EDiaSemana.values();
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
	
	public List<HorarioFuncionamentoEstabelecimentoDTO> getHorarioFuncionamentoEstabelecimentos()
	{
		return this.horarioFuncionamentoEstabelecimentoSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public HorarioFuncionamentoEstabelecimentoDTO getHorarioFuncionamentoEstabelecimentoDTO() {
		if(this.horarioFuncionamentoEstabelecimentoDTO == null)
		{
			this.horarioFuncionamentoEstabelecimentoDTO = FastLunchDTOFactory.
			getHorarioFuncionamentoEstabelecimentoDTO();
	
		}
		
		return horarioFuncionamentoEstabelecimentoDTO;
	}

	public void setHorarioFuncionamentoEstabelecimentoDTO(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTO) {
		this.horarioFuncionamentoEstabelecimentoDTO = horarioFuncionamentoEstabelecimentoDTO;
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

	public HorarioFuncionamentoEstabelecimentoDTO getHorarioFuncionamentoEstabelecimentoDTOSelecionado() {
		if(this.horarioFuncionamentoEstabelecimentoDTOSelecionado == null)
		{
			this.horarioFuncionamentoEstabelecimentoDTOSelecionado = FastLunchDTOFactory.
			getHorarioFuncionamentoEstabelecimentoDTO();
	
		}
		
		return horarioFuncionamentoEstabelecimentoDTOSelecionado;
	}

	public void setHorarioFuncionamentoEstabelecimentoDTOSelecionado(HorarioFuncionamentoEstabelecimentoDTO horarioFuncionamentoEstabelecimentoDTOSelecionado) {
		this.horarioFuncionamentoEstabelecimentoDTOSelecionado = horarioFuncionamentoEstabelecimentoDTOSelecionado;
	}
}
