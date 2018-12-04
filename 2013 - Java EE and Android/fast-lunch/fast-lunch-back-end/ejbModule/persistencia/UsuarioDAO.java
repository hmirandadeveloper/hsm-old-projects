package persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Usuario;


@Stateless
public class UsuarioDAO extends GenericDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public void remover(Usuario usuario)
	{
		super.remover(usuario.getIdUsuario(), Usuario.class);
	}
	
	public Usuario buscarUsuarioPeloLogin(String login)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("login", login);
		
		return super.buscarUmResultado(Usuario.BUSCA_USUARIO_POR_LOGIN, parametros);
	}
}
