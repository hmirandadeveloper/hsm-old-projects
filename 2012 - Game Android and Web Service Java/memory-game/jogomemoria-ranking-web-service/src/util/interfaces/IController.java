package util.interfaces;

import java.util.List;

public interface IController<Entidade> {
	
	public void cadastrar(Entidade entidade);
	
	public void remover(Entidade entidade);
	public void remover(long id);
	
	public void alterar(Entidade entidade);
	
	public Entidade buscar(long id);
	public List<Entidade> buscar();
}
