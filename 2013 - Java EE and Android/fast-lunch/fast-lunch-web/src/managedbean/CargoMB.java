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
import dto.CargoDTO;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import dto.factory.FastLunchDTOFactory;
import dto.mensagem.FastLunchMensagem;
import fachada.ICargoFachada;

@ManagedBean
@SessionScoped
public class CargoMB {
	
	@EJB
	private ICargoFachada cargoSB;
	
	private CargoDTO cargoDTO;
	private CargoDTO cargoDTOSelecionado;
	
	private EstabelecimentoDTO estabelecimentoDTO;
	
	public CargoMB()
	{
		getEstabelecimentoDTO();
	}
	
	public String salvarCargo()
	{
		System.out.println("Salvando Cargo... ");
		try
		{
			if(!atributoNaoPreenchido())
			{
				enviarMenssagemAlerta(FastLunchMensagem.MSG_ALERTA_CADASTRO_ATRIBUTO_INCOMPLETO);
			}
			this.cargoDTO.setEstabelecimento(this.estabelecimentoDTO);
			this.cargoSB.salvar(this.cargoDTO);
			
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
		this.cargoDTO = null;
		enviarMenssagemInformativa(FastLunchMensagem.MSG_CADASTRO_SUCESSO);
		return "";
	}
	
	public void atualizarCargo(Long id)
	{
		this.setCargoDTOSelecionado(this.cargoSB.buscar(id));
	}
	
	public String atualizarCargo()
	{
		try
		{
			this.cargoSB.atualizar(this.cargoDTOSelecionado);
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);
		this.cargoDTOSelecionado = null;
		return "";
	}
	
	public void removerCargo(Long id)
	{
		this.setCargoDTOSelecionado(this.cargoSB.buscar(id));
		removerCargo();
	}
	
	public String removerCargo()
	{
		try
		{
			this.cargoSB.remover(this.cargoDTOSelecionado.getIdCargo(), getFuncionarioLogado().getUsuario().getTipoUsuario());
			
		} catch(EntidadeInexistenteException e){
			e.printStackTrace();
			enviarMenssagemErro(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			enviarMenssagemErroGrave(FastLunchMensagem.MSG_ERRO_FATAL);
		}
		
		enviarMenssagemInformativa(FastLunchMensagem.MSG_ATUALIZADO_SUCESSO);
		this.cargoDTOSelecionado = null;
		return "";
	}
	
	
	public void buscarCargoPelaDescricao()
	{		
		try {
			this.cargoDTO = this.cargoSB.buscarCargoPelaDescricao(cargoDTO.getDescricao(), this.estabelecimentoDTO.getIdEstabelecimento());
			
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
	
	private FuncionarioDTO getFuncionarioLogado()
	{
		FacesContext context = getContext();
		HttpSession session = 
				(HttpSession)context.getExternalContext().getSession(false);
		
		return 	((FuncionarioDTO)session.getAttribute("funcionario"));
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
	
	public List<CargoDTO> getCargos()
	{
		System.out.println("ID do Estabelecimento... " + this.estabelecimentoDTO.getIdEstabelecimento());
		return this.cargoSB.buscarTodos(this.estabelecimentoDTO.getIdEstabelecimento());
	}

	public CargoDTO getCargoDTO() {
		if(this.cargoDTO == null)
		{
			this.cargoDTO = FastLunchDTOFactory.
			getCargoDTO();
	
		}
		
		return cargoDTO;
	}

	public void setCargoDTO(CargoDTO cargoDTO) {
		this.cargoDTO = cargoDTO;
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

	public CargoDTO getCargoDTOSelecionado() {
		
		if(this.cargoDTOSelecionado == null)
		{
			this.cargoDTOSelecionado = FastLunchDTOFactory.getCargoDTO();
		}
		
		return this.cargoDTOSelecionado;
	}

	public void setCargoDTOSelecionado(CargoDTO cargoDTOSelecionado) {
		this.cargoDTOSelecionado = cargoDTOSelecionado;
	}
}
