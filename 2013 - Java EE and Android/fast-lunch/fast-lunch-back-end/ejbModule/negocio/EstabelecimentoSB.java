package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.CnpjInvalidoException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.CargoConversorDTO;
import negocio.util.conversor.dto.EstabelecimentoConversorDTO;
import negocio.util.conversor.dto.FuncionarioConversorDTO;
import negocio.util.validador.ValidadorAtributosEspeciais;
import negocio.util.validador.entidade.EstabelecimentoAtributoValidador;
import persistencia.CargoDAO;
import persistencia.EstabelecimentoDAO;
import persistencia.FuncionarioDAO;
import constantes.ETipoUsuario;
import dto.CargoDTO;
import dto.EstabelecimentoDTO;
import dto.FuncionarioDTO;
import entidade.Cargo;
import entidade.Estabelecimento;
import entidade.Funcionario;
import fachada.IEstabelecimentoFachada;

@Stateless
@Remote(IEstabelecimentoFachada.class)
public class EstabelecimentoSB implements IEstabelecimentoFachada{
	
	@EJB
	EstabelecimentoDAO estabelecimentoDAO;
	@EJB
	FuncionarioDAO funcionarioDAO;
	@EJB
	CargoDAO cargoDAO;
	
	@Override
	public void salvar(EstabelecimentoDTO estabelecimentoDTO, FuncionarioDTO funcionarioDTO) 
			throws EntidadeAtributoIncompletoException, CnpjInvalidoException, EntidadeJaCadastradaException {
		
		if(EstabelecimentoAtributoValidador.validarAtributosPreenchidosEntidade(estabelecimentoDTO))
		{
			if(ValidadorAtributosEspeciais.validarCnpj(estabelecimentoDTO.getCnpj()))
			{
				if(buscarEstabelecimentoPorCnpj(estabelecimentoDTO.getCnpj()).getIdEstabelecimento() == null)
				{
					estabelecimentoDTO.setAtivo(true);
					System.out.println("Salvando estabelecimento pela primeira vez...");
					
					CargoDTO cargoDTO = new CargoDTO();
					cargoDTO.setDescricao("Administrador");
					cargoDTO.setAtivo(true);
					cargoDTO.setEstabelecimento(estabelecimentoDTO);
					this.cargoDAO.salvar(CargoConversorDTO.converterDTOEmEntidade(cargoDTO));
				}
				else
				{
					throw new EntidadeJaCadastradaException(Estabelecimento.class);
				}
			}
			else
			{
				throw new CnpjInvalidoException();
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Estabelecimento.class);
		}
		
		alterarDadosFuncionario(funcionarioDTO, 
		cargoDAO.buscarCargoPelaDescricao("Administrador", this.estabelecimentoDAO.buscarEstabelecimentoPeloCnpj(estabelecimentoDTO.getCnpj()).getIdEstabelecimento())
		);
	}
	
	private void alterarDadosFuncionario(FuncionarioDTO funcionarioDTO, Cargo cargo)
	{
		Funcionario funcionario = FuncionarioConversorDTO.converterDTOEmEntidadeComId(funcionarioDTO);
		funcionario.setCargo(cargo);
		funcionario.getUsuario().setTipoUsuario(ETipoUsuario.GER.name());
		this.funcionarioDAO.atualizar(funcionario);
	}
	
	@Override
	public void remover(long estabelecimentoId, ETipoUsuario tipoUsuario) throws EntidadeInexistenteException {
		if(buscar(estabelecimentoId) != null)
		{
			if(tipoUsuario == ETipoUsuario.ADMIN)
			{
				this.estabelecimentoDAO.remover(gerarEntidadePorId(estabelecimentoId));
			}
			else
			{
				Estabelecimento estabelecimento = this.estabelecimentoDAO.buscar(estabelecimentoId);
				estabelecimento.setAtivo(false);
				this.estabelecimentoDAO.atualizar(estabelecimento);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Estabelecimento.class);
		}
	}

	@Override
	public EstabelecimentoDTO atualizar(EstabelecimentoDTO estabelecimentoDTO) 
			throws EntidadeInexistenteException, EntidadeAtributoIncompletoException, CnpjInvalidoException {
		
		if(buscar(estabelecimentoDTO.getIdEstabelecimento()) != null)
		{
			if(EstabelecimentoAtributoValidador.validarAtributosPreenchidosEntidade(estabelecimentoDTO))
			{
				if(ValidadorAtributosEspeciais.validarCnpj(estabelecimentoDTO.getCnpj()))
				{
					return EstabelecimentoConversorDTO.converterEntidadeEmDTO(
							this.estabelecimentoDAO.atualizar(
									EstabelecimentoConversorDTO.converterDTOEmEntidadeComId(estabelecimentoDTO))
							);
				}
				else
				{
					throw new CnpjInvalidoException();
				}
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(Estabelecimento.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Estabelecimento.class);
		}
		
	}

	@Override
	public EstabelecimentoDTO buscar(long estabelecimentoId) {
		return EstabelecimentoConversorDTO.converterEntidadeEmDTOComId(
				this.estabelecimentoDAO.buscar(estabelecimentoId));
	}

	@Override
	public List<EstabelecimentoDTO> buscarTodos() {
		return EstabelecimentoConversorDTO.converterEntidadesListEmDTOsList(
					this.estabelecimentoDAO.buscarTodos()
				);
	}
	
	private Estabelecimento gerarEntidadePorId(long estabelecimentoId)
	{
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setIdEstabelecimento(estabelecimentoId);
		return estabelecimento;
	}

	@Override
	public EstabelecimentoDTO buscarEstabelecimentoPorCnpj(String cnpj)
			throws CnpjInvalidoException {
		if(ValidadorAtributosEspeciais.validarCnpj(cnpj))
		{
			return EstabelecimentoConversorDTO.converterEntidadeEmDTO(
					this.estabelecimentoDAO.buscarEstabelecimentoPeloCnpj(cnpj));
		}
		else
		{
			throw new CnpjInvalidoException();
		}
	}

	@Override
	public void controleAtivacaoEstabelecimento(
			EstabelecimentoDTO estabelecimentoDTO, boolean situacao) {
		// TODO Auto-generated method stub
		
	}

}
