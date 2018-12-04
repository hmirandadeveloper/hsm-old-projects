package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.HorarioFuncionamentoDelivery;

@Stateless
public class HorarioFuncionamentoDeliveryDAO extends GenericDAO<HorarioFuncionamentoDelivery> {

	public HorarioFuncionamentoDeliveryDAO() {
		super(HorarioFuncionamentoDelivery.class);
	}
	
	public void remover(HorarioFuncionamentoDelivery horarioFuncionamentoDelivery)
	{
		super.remover(horarioFuncionamentoDelivery.getIdHorarioFuncionamentoDelivery(), HorarioFuncionamentoDelivery.class);
	}
	
	public List<HorarioFuncionamentoDelivery> buscarHorarioFuncionamentoDeliverysPeloEstabelecimento(Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		
		return super.buscarResultados(HorarioFuncionamentoDelivery.BUSCA_HORARIOS_DELIVERIES_POR_ESTABELECIMENTO, parametros);
	}
	
	public HorarioFuncionamentoDelivery buscarHorarioFuncionamentoDeliveryPeloEstabelecimentoEDia(Long idEstabelecimento, String diaSemana)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idEstabelecimento", idEstabelecimento);
		parametros.put("diaSemana", diaSemana);
		
		return super.buscarUmResultado(HorarioFuncionamentoDelivery.BUSCA_HORARIOS_DELIVERIES_POR_ESTABELECIMENTO_E_DIA, parametros);
	}
}
