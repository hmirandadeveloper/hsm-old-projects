package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.ValorInvalidoException;
import negocio.util.conversor.dto.FreteConversorDTO;
import negocio.util.validador.entidade.FreteAtributoValidador;
import persistencia.FreteDAO;
import constantes.ETipoUsuario;
import dto.FreteDTO;
import entidade.Frete;
import fachada.IFreteFachada;

@Stateless
@Remote(IFreteFachada.class)
public class FreteSB implements IFreteFachada{
	
	@EJB
	FreteDAO freteDAO;
	
	@Override
	public void salvar(FreteDTO freteDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, 
			ValorInvalidoException {
		
		if(FreteAtributoValidador.validarAtributosPreenchidosEntidade(freteDTO))
		{
			if(buscarFretePorBairro(freteDTO.getBairro(), 
					freteDTO.getEstabelecimentoDTO().getIdEstabelecimento()) == null)
			{
				if(freteDTO.getValor() > 0)
				{
				freteDTO.setAtivo(true);
					this.freteDAO.salvar(
							FreteConversorDTO.converterDTOEmEntidade(freteDTO));
				}
				else
				{
					throw new ValorInvalidoException();
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(Frete.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Frete.class);
		}
	}

	@Override
	public void remover(long freteId, ETipoUsuario admin) throws EntidadeInexistenteException {
		
		if(buscar(freteId) != null)
		{
			if(admin == ETipoUsuario.ADMIN)
			{
				this.freteDAO.remover(gerarEntidadePorId(freteId));
			}
			else
			{
				Frete frete = this.freteDAO.buscar(freteId);
				frete.setAtivo(false);
				this.freteDAO.atualizar(frete);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Frete.class);
		}
	}

	@Override
	public FreteDTO atualizar(FreteDTO freteDTO) 
			throws EntidadeInexistenteException, ValorInvalidoException, EntidadeAtributoIncompletoException {
		
		if(buscar(freteDTO.getIdFrete()) != null)
		{
			if(FreteAtributoValidador.validarAtributosPreenchidosEntidade(freteDTO))
			{
				if(freteDTO.getValor() > 0)
				{
					return FreteConversorDTO.converterEntidadeEmDTO(
							this.freteDAO.atualizar(
									FreteConversorDTO.converterDTOEmEntidadeComId(freteDTO))
							);
				}
				else
				{
					throw new ValorInvalidoException();
				}
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(Frete.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Frete.class);
		}
	}

	@Override
	public FreteDTO buscar(long freteId) {
		return FreteConversorDTO.converterEntidadeEmDTOComId(
				this.freteDAO.buscar(freteId));
	}

	@Override
	public List<FreteDTO> buscarTodos(Long idEstabelecimento) {
		return FreteConversorDTO.converterEntidadesListEmDTOsList(
					this.freteDAO.buscarFretesPeloEstabelecimento(idEstabelecimento)
				);
	}
	
	private Frete gerarEntidadePorId(long freteId)
	{
		Frete frete = new Frete();
		frete.setIdFrete(freteId);
		return frete;
	}

	@Override
	public FreteDTO buscarFretePorBairro(String bairro, Long idEstabelecimento) {
		FreteDTO freteDTOBusca = FreteConversorDTO.converterEntidadeEmDTOComId(
				this.freteDAO.buscarFretePorBairro(
				bairro, idEstabelecimento)
				);
		if(freteDTOBusca.getBairro() == null)
		{
			freteDTOBusca = null;
		}
		
		return freteDTOBusca;
	}

}
