package persistencia;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.FidelidadeTroca;

@Stateless
public class FidelidadeTrocaDAO extends GenericDAO<FidelidadeTroca> {

	public FidelidadeTrocaDAO() {
		super(FidelidadeTroca.class);
	}
	
	public void remover(FidelidadeTroca fidelidadeTroca)
	{
		super.remover(fidelidadeTroca.getIdFidelidadeTroca(), FidelidadeTroca.class);
	}
}
