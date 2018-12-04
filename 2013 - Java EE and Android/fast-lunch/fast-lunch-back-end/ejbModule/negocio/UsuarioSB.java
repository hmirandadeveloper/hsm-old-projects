package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.UsuarioConversorDTO;
import negocio.util.validador.entidade.UsuarioAtributoValidador;
import persistencia.UsuarioDAO;
import dto.UsuarioDTO;
import entidade.Usuario;
import fachada.IUsuarioFachada;

@Stateless
@Local(IUsuarioFachada.class)
public class UsuarioSB implements IUsuarioFachada{
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	@Override
	public void salvar(UsuarioDTO usuarioDTO) throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException {
		
		if(UsuarioAtributoValidador.validarAtributosPreenchidosEntidade(usuarioDTO))
		{
			if(buscarUsuarioPeloLogin(usuarioDTO.getLogin()) == null)
			{
				this.usuarioDAO.salvar(UsuarioConversorDTO.converterDTOEmEntidade(usuarioDTO));
			}
			else
			{
				throw new EntidadeJaCadastradaException(Usuario.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Usuario.class);
		}
	}

	@Override
	public void remover(long usuarioId) throws EntidadeInexistenteException {
		if(buscar(usuarioId) != null)
		{
			this.usuarioDAO.remover(gerarEntidadePorId(usuarioId));
		}
		else
		{
			throw new EntidadeInexistenteException(Usuario.class);
		}
	}

	@Override
	public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		
		if(buscar(usuarioDTO.getIdUsuario()) != null)
		{

			if(UsuarioAtributoValidador.validarAtributosPreenchidosEntidade(usuarioDTO))
			{
				return UsuarioConversorDTO.converterEntidadeEmDTO(
						this.usuarioDAO.atualizar(
								UsuarioConversorDTO.converterDTOEmEntidadeComId(usuarioDTO))
						);
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(Usuario.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Usuario.class);
		}
	}

	@Override
	public UsuarioDTO buscar(long usuarioId) {
		return UsuarioConversorDTO.converterEntidadeEmDTOComId(
				this.usuarioDAO.buscar(usuarioId));
	}

	@Override
	public List<UsuarioDTO> buscarTodos() {
		return UsuarioConversorDTO.converterEntidadesListEmDTOsList(
					this.usuarioDAO.buscarTodos()
				);
	}
	
	private Usuario gerarEntidadePorId(long usuarioId)
	{
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(usuarioId);
		return usuario;
	}

	@Override
	public UsuarioDTO buscarUsuarioPeloLogin(String login) {
		return UsuarioConversorDTO.converterEntidadeEmDTOComId(
				this.usuarioDAO.buscarUsuarioPeloLogin(login)
				);
	}
	
	

}
