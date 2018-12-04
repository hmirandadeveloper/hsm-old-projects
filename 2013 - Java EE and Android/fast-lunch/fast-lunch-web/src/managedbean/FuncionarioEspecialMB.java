package managedbean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeJaCadastradaException;
import constantes.ETipoUsuario;
import dto.FuncionarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.ICargoFachada;
import fachada.IFuncionarioFachada;

@ManagedBean
@SessionScoped
public class FuncionarioEspecialMB {
	
	@EJB
	private IFuncionarioFachada funcionarioSB;
	@EJB
	private ICargoFachada cargoSB;
	
	private FuncionarioDTO funcionarioDTO;
		
	public String salvarFuncionario()
	{
		System.out.println("Salvando Funcionario... ");
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.funcionarioDTO.getUsuario().setTipoUsuario(ETipoUsuario.TEMP);
			this.funcionarioDTO.setCargo(this.cargoSB.buscar(new Long(1)));
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
	
	private boolean atributoNaoPreenchido()
	{
		return true;
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
}
