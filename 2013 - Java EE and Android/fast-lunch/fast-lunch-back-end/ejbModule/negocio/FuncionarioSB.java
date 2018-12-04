package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeAtributoUnicoDuplicadoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.FuncionarioConversorDTO;
import negocio.util.validador.entidade.FuncionarioAtributoValidador;
import persistencia.FuncionarioDAO;
import persistencia.UsuarioDAO;
import constantes.ETipoUsuario;
import dto.FuncionarioDTO;
import entidade.Funcionario;
import entidade.Usuario;
import fachada.IFuncionarioFachada;

@Stateless
@Remote(IFuncionarioFachada.class)
public class FuncionarioSB implements IFuncionarioFachada{
	
	@EJB
	FuncionarioDAO funcionarioDAO;
	
	@EJB
	UsuarioDAO usuarioDAO;
	
	@Override
	public void salvar(FuncionarioDTO funcionarioDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeInexistenteException, 
			EntidadeJaCadastradaException {
		
		if(FuncionarioAtributoValidador.validarAtributosPreenchidosEntidade(funcionarioDTO))
		{
			
			FuncionarioDTO funcionarioDTObusca = buscarFuncionarioPorMatricula(funcionarioDTO.getMatricula(), funcionarioDTO.getCargo().getEstabelecimento().getIdEstabelecimento());
			
			if(funcionarioDTObusca == null)
			{
				System.out.println("Verificando usuario: " + funcionarioDTO.getUsuario().getLogin());
				if(!validarExistenciaLoginUsuario(funcionarioDTO))
				{
					funcionarioDTO.setAtivo(true);
					this.funcionarioDAO.salvar(
							FuncionarioConversorDTO.converterDTOEmEntidade(funcionarioDTO));
				}
				else
				{
					throw new EntidadeJaCadastradaException(Usuario.class);
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(Funcionario.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Funcionario.class);
		}
	}

	@Override
	public void remover(long funcionarioId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		
		if(buscar(funcionarioId) != null)
		{
			if(tipoUsuario == ETipoUsuario.ADMIN)
			{
				this.funcionarioDAO.remover(gerarEntidadePorId(funcionarioId));
			}
			else
			{
				Funcionario funcionario = this.funcionarioDAO.buscar(funcionarioId);
				funcionario.setAtivo(false);
				this.funcionarioDAO.atualizar(funcionario);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Funcionario.class);
		}
	}

	@Override
	public FuncionarioDTO atualizar(FuncionarioDTO funcionarioDTO, String matriculaAnterior) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException, EntidadeAtributoUnicoDuplicadoException{
		
		FuncionarioDTO funcionarioDTOBusca = buscarFuncionarioPorMatricula(funcionarioDTO.getMatricula(), 
				funcionarioDTO.getCargo().getEstabelecimento().
				getIdEstabelecimento());
		
		if(buscar(funcionarioDTO.getIdFuncionario()) != null)
		{
			if(FuncionarioAtributoValidador.validarAtributosPreenchidosEntidade(funcionarioDTO))
			{

				if(funcionarioDTOBusca == null || funcionarioDTOBusca.getMatricula().equals(matriculaAnterior))
				{
					return FuncionarioConversorDTO.converterEntidadeEmDTO(
							this.funcionarioDAO.atualizar(
									FuncionarioConversorDTO.converterDTOEmEntidadeComId(funcionarioDTO))
							);
				}
				else
				{
					throw new EntidadeAtributoUnicoDuplicadoException("Matrícula");
				}
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(Funcionario.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Funcionario.class);
		}
	}

	@Override
	public FuncionarioDTO buscar(long funcionarioId) {
		System.out.println("ID do Funcionario Selecionado (BUSCA): " + funcionarioId);
		return FuncionarioConversorDTO.converterEntidadeEmDTOComId(
				this.funcionarioDAO.buscar(funcionarioId));
	}

	@Override
	public List<FuncionarioDTO> buscarTodos(Long idEstabelecimento) {
		return FuncionarioConversorDTO.converterEntidadesListEmDTOsList(
					this.funcionarioDAO.buscarFuncionariosPeloEstabelecimento(idEstabelecimento)
				);
	}
	
	private Funcionario gerarEntidadePorId(long funcionarioId)
	{
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(funcionarioId);
		return funcionario;
	}
	
	private boolean validarExistenciaLoginUsuario(FuncionarioDTO funcionarioDTO)
	{
		if(usuarioDAO.buscarUsuarioPeloLogin(funcionarioDTO.getUsuario().getLogin()) != null)
		{
			System.out.println("Ja existe usuario...");
			return true;
		}
		System.out.println("Nao existe usuario...");
		return false;
	}

	@Override
	public FuncionarioDTO buscarFuncionarioPorMatricula(String matricula, Long id_estabelecimento)
		{
		FuncionarioDTO funcionarioDTObusca = FuncionarioConversorDTO.converterEntidadeEmDTOComId(
				this.funcionarioDAO.buscarFuncionarioPelaMatricula(matricula, id_estabelecimento));;
		if(funcionarioDTObusca != null ? funcionarioDTObusca.getMatricula() == null : false)
		{
			funcionarioDTObusca = null;
		}
		return funcionarioDTObusca;
	}

	@Override
	public FuncionarioDTO buscaPeloUsuario(Long id_usuario)
			throws EntidadeInexistenteException {
		return FuncionarioConversorDTO.converterEntidadeEmDTOComId(this.funcionarioDAO.buscarFuncionarioPeloUsuario(id_usuario));
	}

	@Override
	public void controleAtivacaoFuncionario(FuncionarioDTO funcionarioDTO,
			boolean situacao) {
		// TODO Auto-generated method stub
		
	}

}
