package persistencia;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Endereco;

@Stateless
public class EnderecoDAO extends GenericDAO<Endereco> {

	public EnderecoDAO() {
		super(Endereco.class);
	}
	
	public void remover(Endereco endereco)
	{
		super.remover(endereco.getIdEndereco(), Endereco.class);
	}
}
