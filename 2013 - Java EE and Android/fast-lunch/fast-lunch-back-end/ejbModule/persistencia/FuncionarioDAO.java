package persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import persistencia.generico.GenericDAO;
import entidade.Funcionario;

@Stateless
public class FuncionarioDAO extends GenericDAO<Funcionario> {

	public FuncionarioDAO() {
		super(Funcionario.class);
	}
	
	public void remover(Funcionario funcionario)
	{
		super.remover(funcionario.getIdFuncionario(), Funcionario.class);
	}
	
	@Override
	public void salvar(Funcionario entidade) {

		if(entidade.getCargo().getIdCargo() == null)
		{
			super.salvar(entidade);
		}
		else
		{
			super.atualizar(entidade);
		}
	}
	
	public Funcionario buscarFuncionarioPelaMatricula(String matricula, Long id_estabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("matricula", matricula);
		parametros.put("id_estabelecimento", id_estabelecimento);
		
		return super.buscarUmResultado(Funcionario.BUSCA_FUNCIONARIO_POR_MATRICULA, parametros);
	}
	
	public Funcionario buscarFuncionarioPeloUsuario(Long idUsuario)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("id_usuario", idUsuario);
		
		return super.buscarUmResultado(Funcionario.BUSCA_FUNCIONARIO_POR_USUARIO, parametros);
	}
	
	public List<Funcionario> buscarFuncionariosPeloEstabelecimento(Long idEstabelecimento)
	{
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("id_estabelecimento", idEstabelecimento);
		
		return super.buscarResultados(Funcionario.BUSCA_FUNCIONARIOS_POR_ESTABELECIMENTO, parametros);
	}
}
