package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.DataInferiorException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.ValorInvalidoException;
import negocio.util.conversor.dto.FidelidadeTrocaConversorDTO;
import negocio.util.validador.data.OperacoesData;
import negocio.util.validador.entidade.FidelidadeTrocaAtributoValidador;

import persistencia.FidelidadeTrocaDAO;

import dto.FidelidadeTrocaDTO;
import entidade.FidelidadeTroca;
import fachada.IFidelidadeTrocaFachada;

@Stateless
@Remote(IFidelidadeTrocaFachada.class)
public class FidelidadeTrocaSB implements IFidelidadeTrocaFachada{
	
	@EJB
	FidelidadeTrocaDAO fidelidadeTrocaDAO;
	
	@Override
	public void salvar(FidelidadeTrocaDTO fidelidadeTrocaDTO) 
			throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, 
			ValorInvalidoException, DataInferiorException {
		
		if(FidelidadeTrocaAtributoValidador.validarAtributosPreenchidosEntidade(fidelidadeTrocaDTO))
		{
			if(buscar(fidelidadeTrocaDTO.getIdFidelidadeTroca()) == null)
			{
				if(fidelidadeTrocaDTO.getPontos() <= 0)
				{
					if(OperacoesData.validacaoComDataAtual(fidelidadeTrocaDTO.getDataHora()))
					{
						this.fidelidadeTrocaDAO.salvar(
								FidelidadeTrocaConversorDTO.
								converterDTOEmEntidade(fidelidadeTrocaDTO));
					}
					else
					{
						throw new DataInferiorException(FidelidadeTroca.class);
					}
				}
				else
				{
					throw new ValorInvalidoException();
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(FidelidadeTroca.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(FidelidadeTroca.class);
		}
	}

	@Override
	public void remover(long fidelidadeTrocaId) throws EntidadeInexistenteException {
		
		if(buscar(fidelidadeTrocaId) != null)
		{
			this.fidelidadeTrocaDAO.remover(gerarEntidadePorId(fidelidadeTrocaId));
		}
		else
		{
			throw new EntidadeInexistenteException(FidelidadeTroca.class);
		}
	}

	@Override
	public FidelidadeTrocaDTO atualizar(FidelidadeTrocaDTO fidelidadeTrocaDTO) 
			throws EntidadeInexistenteException, ValorInvalidoException, EntidadeAtributoIncompletoException {
		
		if(buscar(fidelidadeTrocaDTO.getIdFidelidadeTroca()) != null)
		{
			if(FidelidadeTrocaAtributoValidador.validarAtributosPreenchidosEntidade(fidelidadeTrocaDTO))
			{
			if(fidelidadeTrocaDTO.getPontos() <= 0)
			{
				return FidelidadeTrocaConversorDTO.converterEntidadeEmDTO(
						this.fidelidadeTrocaDAO.atualizar(
								FidelidadeTrocaConversorDTO.converterDTOEmEntidadeComId(fidelidadeTrocaDTO))
						);
			}
			else
			{
				throw new ValorInvalidoException();
			}
			}
			else
			{
				throw new EntidadeAtributoIncompletoException(FidelidadeTroca.class);
			}
		}
		else
		{
			throw new EntidadeInexistenteException(FidelidadeTroca.class);
		}
	}

	@Override
	public FidelidadeTrocaDTO buscar(long fidelidadeTrocaId) {
		return FidelidadeTrocaConversorDTO.converterEntidadeEmDTOComId(
				this.fidelidadeTrocaDAO.buscar(fidelidadeTrocaId));
	}

	@Override
	public List<FidelidadeTrocaDTO> buscarTodos() {
		return FidelidadeTrocaConversorDTO.converterEntidadesListEmDTOsList(
					this.fidelidadeTrocaDAO.buscarTodos()
				);
	}
	
	private FidelidadeTroca gerarEntidadePorId(long fidelidadeTrocaId)
	{
		FidelidadeTroca fidelidadeTroca = new FidelidadeTroca();
		fidelidadeTroca.setIdFidelidadeTroca(fidelidadeTrocaId);
		return fidelidadeTroca;
	}

}
