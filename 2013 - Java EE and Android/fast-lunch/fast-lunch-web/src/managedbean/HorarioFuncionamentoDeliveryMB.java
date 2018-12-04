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
import dto.HorarioFuncionamentoDeliveryDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.IHorarioFuncionamentoDeliveryFachada;

@ManagedBean
@SessionScoped
public class HorarioFuncionamentoDeliveryMB {
	@EJB
	private IHorarioFuncionamentoDeliveryFachada horarioFuncionamentoDeliverySB;
	
	private HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO;
	private HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public HorarioFuncionamentoDeliveryMB()
	{
		getEstabelecimentoDTO();
	}
		
	public String salvarHorarioFuncionamentoDelivery()
	{
		System.out.println("Salvando Horario de Funcionamento | Delivery... ");
		
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.horarioFuncionamentoDeliveryDTO.setEstabelecimentoDTO(this.estabelecimentoDTO);
			
			System.out.println("Acessando camada SB...");
			this.horarioFuncionamentoDeliverySB.salvar(this.horarioFuncionamentoDeliveryDTO);
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
		this.horarioFuncionamentoDeliveryDTO = null;
		return "";
	}
	
	public void atualizarHorarioFuncionamentoDelivery(Long id)
	{
		this.horarioFuncionamentoDeliveryDTOSelecionado = 
				this.horarioFuncionamentoDeliverySB.buscar(id);
	}
	
	public String atualizarHorarioFuncionamentoDelivery()
	{
		try
		{
			this.horarioFuncionamentoDeliverySB.atualizar(horarioFuncionamentoDeliveryDTOSelecionado);
			
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
		
		this.horarioFuncionamentoDeliveryDTOSelecionado = null;
		
		return "";
	}
	
	public void removerHorarioFuncionamentoDelivery(Long id)
	{
		this.horarioFuncionamentoDeliveryDTOSelecionado = this.horarioFuncionamentoDeliverySB.buscar(id);
		removerHorarioFuncionamentoDelivery();
	}
	
	public String removerHorarioFuncionamentoDelivery()
	{
		try
		{
			this.horarioFuncionamentoDeliverySB.remover(horarioFuncionamentoDeliveryDTOSelecionado.getIdHorarioFuncionamentoDelivery(), ETipoUsuario.ADMIN);
			
			enviarMenssagemInformativa(FastLunchMensagem.MSG_REMOVIDO_SUCESSO);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		this.horarioFuncionamentoDeliveryDTOSelecionado = null;
		
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
	
	public List<HorarioFuncionamentoDeliveryDTO> getHorarioFuncionamentoDeliverys()
	{
		return this.horarioFuncionamentoDeliverySB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public HorarioFuncionamentoDeliveryDTO getHorarioFuncionamentoDeliveryDTO() {
		if(this.horarioFuncionamentoDeliveryDTO == null)
		{
			this.horarioFuncionamentoDeliveryDTO = FastLunchDTOFactory.
			getHorarioFuncionamentoDeliveryDTO();
	
		}
		
		return horarioFuncionamentoDeliveryDTO;
	}

	public void setHorarioFuncionamentoDeliveryDTO(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTO) {
		this.horarioFuncionamentoDeliveryDTO = horarioFuncionamentoDeliveryDTO;
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

	public HorarioFuncionamentoDeliveryDTO getHorarioFuncionamentoDeliveryDTOSelecionado() {
		if(this.horarioFuncionamentoDeliveryDTOSelecionado == null)
		{
			this.horarioFuncionamentoDeliveryDTOSelecionado = FastLunchDTOFactory.
			getHorarioFuncionamentoDeliveryDTO();
	
		}
		
		return horarioFuncionamentoDeliveryDTOSelecionado;
	}

	public void setHorarioFuncionamentoDeliveryDTOSelecionado(HorarioFuncionamentoDeliveryDTO horarioFuncionamentoDeliveryDTOSelecionado) {
		this.horarioFuncionamentoDeliveryDTOSelecionado = horarioFuncionamentoDeliveryDTOSelecionado;
	}
}
