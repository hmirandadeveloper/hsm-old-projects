package persistencia;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.HorarioFuncionamento;

@Stateless
public class HorarioFuncionamentoDAO extends GenericDAO<HorarioFuncionamento> {

	public HorarioFuncionamentoDAO() {
		super(HorarioFuncionamento.class);
	}
	
	public void remover(HorarioFuncionamento horarioFuncionamento)
	{
		super.remover(horarioFuncionamento.getIdHorarioFuncionamento(), HorarioFuncionamento.class);
	}
}
