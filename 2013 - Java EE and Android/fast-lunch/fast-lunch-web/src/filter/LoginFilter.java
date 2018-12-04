package filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import negocio.exception.EntidadeInexistenteException;

import org.jboss.security.SecurityAssociation;

import constantes.ETipoUsuario;
import dto.ClienteDTO;
import dto.FuncionarioDTO;
import dto.UsuarioDTO;
import fachada.IClienteFachada;
import fachada.IFuncionarioFachada;
import fachada.IUsuarioFachada;

public class LoginFilter implements Filter{

	@EJB
	private IUsuarioFachada usuarioSB;

	@EJB
	private IFuncionarioFachada funcionarioSB;

	@EJB
	private IClienteFachada clienteSB;

	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {		

		HttpServletRequest servletRequest = (HttpServletRequest) request;

		HttpSession session = servletRequest.getSession();

		String userName = SecurityAssociation.getPrincipal().getName();
		System.out.println("Usuário logado: " + userName);

		UsuarioDTO usuarioDTO = this.usuarioSB.buscarUsuarioPeloLogin(userName);	

		session.setAttribute("usuario", usuarioDTO);

		FuncionarioDTO funcionarioDTO = null;
		ClienteDTO clienteDTO = null;

		System.out.println("Iniciando Verificação!!!" + usuarioDTO.getTipoUsuario());
		
		if(usuarioDTO.getTipoUsuario() != ETipoUsuario.ADMIN)
		{
			if(session.getAttribute("funcionario") == null && session.getAttribute("cliente") == null)
			{
				if((usuarioDTO.getTipoUsuario() == ETipoUsuario.GER ||
						usuarioDTO.getTipoUsuario() == ETipoUsuario.FUNC ||
						usuarioDTO.getTipoUsuario() == ETipoUsuario.TEMP))
				{
					try {
						System.out.println("É funcionario!!!");
						funcionarioDTO = 
								this.funcionarioSB.buscaPeloUsuario(usuarioDTO.getIdUsuario());
						System.out.println("Funcionario: " + funcionarioDTO.getNome());
						System.out.println("Cargo: " + funcionarioDTO.getCargo().getDescricao());
						System.out.println("ID Est.: " + funcionarioDTO.getCargo().getEstabelecimento().getIdEstabelecimento());
					} catch (EntidadeInexistenteException e) {
						e.printStackTrace();
					}
				}
				else if(usuarioDTO.getTipoUsuario() == ETipoUsuario.USER)
				{
					try {
						System.out.println("É Cliente!!!");
						clienteDTO = 
								this.clienteSB.buscaPeloUsuario(usuarioDTO.getIdUsuario());
						System.out.println("Cliente: " + clienteDTO.getNome());
						System.out.println("ID Est.: " + clienteDTO.getCpf());
					} catch (EntidadeInexistenteException e) {
						e.printStackTrace();
					}
				}
			}
			if(funcionarioDTO != null && session.getAttribute("funcionario") == null)
			{
				System.out.println("Atributo de funcionario setado!!! "+ 
						funcionarioDTO.getCargo().getEstabelecimento().getIdEstabelecimento());
				session.setAttribute("funcionario", funcionarioDTO);

			}

			if(clienteDTO != null && session.getAttribute("cliente") == null)
			{
				System.out.println("Atributo de cliente setado!!!");
				session.setAttribute("cliente", clienteDTO);

			}
		}
		System.out.println("Acessando o MB...");
		filterChain.doFilter(request, response);

		System.out.println("Processo de autenticacao finalizado!");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
