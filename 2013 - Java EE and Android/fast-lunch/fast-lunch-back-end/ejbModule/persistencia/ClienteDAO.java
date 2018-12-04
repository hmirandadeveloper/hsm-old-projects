package persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Cliente;

@Stateless
public class ClienteDAO extends GenericDAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}
	
	public void remover(Cliente cliente)
	{
		super.remover(cliente.getIdCliente(), Cliente.class);
	}
	
	public Cliente buscarClientePeloCpf(String cpf)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("cpf", cpf);
		
		return super.buscarUmResultado(Cliente.BUSCA_CLIENTE_POR_CPF, parametros);
	}
	
	public Cliente buscarClientePeloEmail(String email)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("email", email);
		
		return super.buscarUmResultado(Cliente.BUSCA_CLIENTE_POR_EMAIL, parametros);
	}
	
	public Cliente buscarClientePeloUsuario(Long idUsuario)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idUsuario", idUsuario);
		
		return super.buscarUmResultado(Cliente.BUSCA_CLIENTE_POR_USUARIO, parametros);
	}
}
