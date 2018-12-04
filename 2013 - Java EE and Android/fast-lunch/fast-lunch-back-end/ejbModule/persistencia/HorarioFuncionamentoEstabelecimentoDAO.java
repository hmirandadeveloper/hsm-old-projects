package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.HorarioFuncionamentoEstabelecimento;

@Stateless
public class HorarioFuncionamentoEstabelecimentoDAO extends GenericDAO<HorarioFuncionamentoEstabelecimento> {

	public HorarioFuncionamentoEstabelecimentoDAO() {
		super(HorarioFuncionamentoEstabelecimento.class);
	}
	
	public void remover(HorarioFuncionamentoEstabelecimento horarioFuncionamentoEstabelecimento)
	{
		super.remover(horarioFuncionamentoEstabelecimento.getIdHorarioFuncionamentoEstabelecimento(), HorarioFuncionamentoEstabelecimento.class);
	}
	
	public List<HorarioFuncionamentoEstabelecimento> buscarHorarioFuncionamentoEstabelecimentosPeloEstabelecimento(Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarResultados(HorarioFuncionamentoEstabelecimento.BUSCA_HORARIOS_ESTABELECIMENTO_POR_ESTABELECIMENTO, parametros);
	}
	
	public HorarioFuncionamentoEstabelecimento buscarHorarioFuncionamentoEstabelecimentoPeloEstabelecimentoEDia(Long idEstabelecimento, String diaSemana)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		parametros.put("diaSemana", diaSemana);
		
		return super.buscarUmResultado(HorarioFuncionamentoEstabelecimento.BUSCA_HORARIO_ESTABELECIMENTO_POR_ESTABELECIMENTO_E_DIA, parametros);
	}
}
